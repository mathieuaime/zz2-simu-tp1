package com.isima.zz2.simu.system;

import com.isima.zz2.simu.machine.Input;
import com.isima.zz2.simu.machine.Machine;
import com.isima.zz2.simu.machine.Output;
import com.isima.zz2.simu.model.Piece;
import com.isima.zz2.simu.queue.Queue;
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
 * Unit Tests for Systeme.
 * Created by Mathieu on 14/10/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class SystemeTest {

    private static final int QUEUE_SIZE = 10;
    private static final int DUREE = 2;
    private static final int TREATMENT_TIME = 5;
    private static final int DIA = 1;

    @Mock
    private Input input;

    @Mock
    private Output output;

    @Mock
    private Queue<Piece> queue;

    @Mock
    private Machine machine;

    @InjectMocks
    private Systeme systeme = new Systeme(DIA, TREATMENT_TIME, DUREE, QUEUE_SIZE);

    @Before
    public void setUp() throws Exception {
        systeme = new Systeme(DIA, TREATMENT_TIME, DUREE, QUEUE_SIZE);
        FieldUtils.writeField(systeme, "input", input, true);
        FieldUtils.writeField(systeme, "output", output, true);
        FieldUtils.writeField(systeme, "queue", queue, true);
        FieldUtils.writeField(systeme, "machine", machine, true);
    }

    @Test
    public void whenTheInputHasTheLowerDPEItShouldAddAPieceToTheSystem() throws Exception {
        int date = 1;

        Mockito.when(input.getDpe()).thenReturn(1);
        Mockito.when(machine.getDpe()).thenReturn(2);

        int newDate = systeme.executeAction(date);

        Assert.assertEquals(1, newDate);
        Mockito.verify(input, Mockito.times(2)).getDpe();
        Mockito.verify(machine).getDpe();
        Mockito.verify(input).action(1, queue, machine, output);
        Mockito.verifyNoMoreInteractions(input, output, queue, machine);
    }

    @Test
    public void whenTheMachineHasTheLowerDPEItShouldTreatAPiece() throws Exception {
        int date = 1;

        Mockito.when(input.getDpe()).thenReturn(2);
        Mockito.when(machine.getDpe()).thenReturn(1);

        int newDate = systeme.executeAction(date);

        Assert.assertEquals(1, newDate);
        Mockito.verify(input).getDpe();
        Mockito.verify(machine, Mockito.times(2)).getDpe();
        Mockito.verify(machine).action(1, queue, output);
        Mockito.verifyNoMoreInteractions(input, output, queue, machine);
    }
}