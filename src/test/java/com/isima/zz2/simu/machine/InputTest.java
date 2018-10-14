package com.isima.zz2.simu.machine;

import com.isima.zz2.simu.model.Piece;
import com.isima.zz2.simu.queue.Queue;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit tests for Input.
 * <p>
 * Created by Mathieu on 14/10/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class InputTest {

    private static final int DIA = 4;

    @Mock
    private Queue<Piece> queue;

    @Mock
    private Machine machine;

    @Mock
    private Output output;

    @Test
    public void givenANewInputPieceWhenTheQueueIsFullThenItShouldRejected() throws Exception {
        Input input = new Input(DIA);
        int date = 1;

        Mockito.when(queue.isFull()).thenReturn(true);

        input.action(date, queue, machine, output);

        Mockito.verify(queue).isFull();
        Mockito.verify(output).reject(Mockito.any(Piece.class));
        Mockito.verifyNoMoreInteractions(queue, machine, output);
        Assert.assertEquals(date + DIA, input.getDpe());
        Assert.assertEquals(1, input.getCptr());
    }

    @Test
    public void givenANewInputPieceWhenTheQueueIsNotFullAndTheMachineIsActiveThenItShouldBaAddedToTheQueue() throws Exception {
        Input input = new Input(DIA);
        int date = 1;

        Mockito.when(queue.isFull()).thenReturn(false);
        Mockito.when(machine.isActivate()).thenReturn(true);

        input.action(date, queue, machine, output);

        Mockito.verify(queue).isFull();
        Mockito.verify(machine).isActivate();
        Mockito.verify(queue).push(Mockito.any(Piece.class));
        Mockito.verifyNoMoreInteractions(queue, machine, output);
        Assert.assertEquals(date + DIA, input.getDpe());
        Assert.assertEquals(1, input.getCptr());
    }

    @Test
    public void givenANewInputPieceWhenTheQueueIsNotFullAndTheMachineIsInactiveThenItShouldBeAddedToTheMachine() throws Exception {
        Input input = new Input(DIA);
        int date = 1;

        Mockito.when(queue.isFull()).thenReturn(false);
        Mockito.when(machine.isActivate()).thenReturn(false);

        input.action(date, queue, machine, output);

        Mockito.verify(queue).isFull();
        Mockito.verify(machine).isActivate();
        Mockito.verify(machine).push(Mockito.eq(date), Mockito.any(Piece.class));
        Mockito.verifyNoMoreInteractions(queue, machine, output);
        Assert.assertEquals(date + DIA, input.getDpe());
        Assert.assertEquals(1, input.getCptr());
    }

}