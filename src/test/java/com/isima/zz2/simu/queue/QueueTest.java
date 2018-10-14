package com.isima.zz2.simu.queue;

import com.isima.zz2.simu.model.Piece;
import com.isima.zz2.simu.queue.exception.QueueEmptyException;
import com.isima.zz2.simu.queue.exception.QueueFullException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for queue.
 * <p>
 * Created by Mathieu on 14/10/2018.
 */
public class QueueTest {

    private static final int QUEUE_SIZE = 4;
    private Queue<Piece> queue;

    @Before
    public void setUp() throws Exception {
        queue = new Queue<>(QUEUE_SIZE);
    }

    @Test
    public void whenTheQueueIsEmptyAPieceCanBePushed() throws Exception {
        Piece piece = new Piece();
        queue.push(piece);
        Assert.assertFalse(queue.isFull());
    }

    @Test(expected = QueueFullException.class)
    public void whenTheQueueIsFullAPieceCanNotBePushed() throws Exception {
        Piece piece = new Piece();
        queue.push(piece);
        queue.push(piece);
        queue.push(piece);
        queue.push(piece);

        Assert.assertTrue(queue.isFull());
        queue.push(piece);
    }

    @Test
    public void whenTheQueueIsNotEmptyAPieceCanBePoped() throws Exception {
        Piece piece = new Piece();
        queue.push(piece);

        Piece pop = queue.pop();

        Assert.assertEquals(piece, pop);
        Assert.assertTrue(queue.isEmpty());
        Assert.assertFalse(queue.isFull());
    }

    @Test(expected = QueueEmptyException.class)
    public void whenTheQueueIsEmptyAPieceCanNotBePoped() throws Exception {
        queue.pop();
    }
}