package com.isima.zz2.simu.machine;

import com.isima.zz2.simu.model.Piece;
import com.isima.zz2.simu.queue.Queue;
import com.isima.zz2.simu.queue.exception.QueueEmptyException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit tests for Machine.
 * <p>
 * Created by Mathieu on 14/10/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class MachineTest {

    private static final int TREATMENT_TIME = 4;

    @Mock
    private Output output;

    @Mock
    private Queue<Piece> queue;

    @Mock
    private Piece piece;

    @InjectMocks
    private Machine machine = new Machine(TREATMENT_TIME);

    @Before
    public void setUp() throws Exception {
        machine = new Machine(TREATMENT_TIME);
    }

    @Test
    public void push() throws Exception {
        int date = 1;

        machine.push(date, piece);

        Assert.assertEquals(true, machine.isActivate());
        Assert.assertEquals(piece, machine.getPiece());
        Assert.assertEquals(date + TREATMENT_TIME, machine.getDpe());

        Mockito.verify(piece).setServorDate(date);
        Mockito.verifyNoMoreInteractions(output, queue, piece);
    }

    @Test
    public void whenTheMachineIsActivateAndTheQueueNotEmptyItShouldOutputThePieceAndPushTheNextOne() throws Exception {
        int date = 1;
        int dpe = 1;
        Piece p = new Piece();
        FieldUtils.writeField(machine, "activate", true, true);
        FieldUtils.writeField(machine, "piece", p, true);
        FieldUtils.writeField(machine, "dpe", dpe, true);

        Mockito.when(queue.pop()).thenReturn(piece);

        machine.action(date, queue, output);

        Assert.assertEquals(true, machine.isActivate());
        Assert.assertEquals(piece, machine.getPiece());
        Assert.assertEquals(date + TREATMENT_TIME, machine.getDpe());

        Mockito.verify(output).accept(p);
        Mockito.verify(queue).pop();
        Mockito.verify(piece).setServorDate(date);
        Mockito.verifyNoMoreInteractions(output, queue, piece);
    }

    @Test
    public void whenTheQueueIsEmptyItShouldDeactivateTheMachine() throws Exception {
        int date = 1;
        int dpe = 1;
        Piece p = new Piece();
        FieldUtils.writeField(machine, "activate", true, true);
        FieldUtils.writeField(machine, "piece", p, true);
        FieldUtils.writeField(machine, "dpe", dpe, true);

        Mockito.when(queue.pop()).thenThrow(QueueEmptyException.class);

        machine.action(date, queue, output);

        Assert.assertEquals(false, machine.isActivate());
        Assert.assertEquals(null, machine.getPiece());
        Assert.assertEquals(Integer.MAX_VALUE, machine.getDpe());

        Mockito.verify(output).accept(p);
        Mockito.verify(queue).pop();
        Mockito.verifyNoMoreInteractions(output, queue, piece);
    }

}