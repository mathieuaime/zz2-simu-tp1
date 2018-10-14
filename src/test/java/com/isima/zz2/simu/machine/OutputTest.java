package com.isima.zz2.simu.machine;

import com.isima.zz2.simu.model.Piece;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Output.
 * <p>
 * Created by Mathieu on 14/10/2018.
 */
public class OutputTest {
    private static final double DELTA = 0.01;

    private Output output;

    @Before
    public void setUp() throws Exception {
        output = new Output();
    }

    @Test
    public void whenNoPieceTheAverageTimeShouldBeNull() {
        Assert.assertEquals(0d, output.avgQueueTime(), DELTA);
        Assert.assertEquals(0d, output.avgServorTime(), DELTA);
        Assert.assertEquals(0d, output.avgSystemTime(), DELTA);
        Assert.assertEquals(0, output.getNumberAccepted());
        Assert.assertEquals(0, output.getNumberReject());
    }

    @Test
    public void whenTwoPieceAreAcceptedTheAverageTimeShouldBeNotNull() {
        Piece p1 = new Piece();
        p1.setEntryDate(1);
        p1.setServorDate(2);
        p1.setExitDate(3);

        Piece p2 = new Piece();
        p2.setEntryDate(4);
        p2.setServorDate(5);
        p2.setExitDate(6);

        System.out.println(p1.queueTime());
        System.out.println(p2.queueTime());

        output.accept(p1);
        output.accept(p2);

        Assert.assertEquals(1d, output.avgQueueTime(), DELTA);
        Assert.assertEquals(1d, output.avgServorTime(), DELTA);
        Assert.assertEquals(2d, output.avgSystemTime(), DELTA);
        Assert.assertEquals(2, output.getNumberAccepted());
        Assert.assertEquals(0, output.getNumberReject());
    }

    @Test
    public void whenTwoPieceAreRejectedTheAverageTimeShouldBeNull() {
        Piece p1 = new Piece();
        p1.setEntryDate(1);
        p1.setServorDate(2);
        p1.setExitDate(3);

        Piece p2 = new Piece();
        p2.setEntryDate(4);
        p2.setServorDate(5);
        p2.setExitDate(6);

        output.reject(p1);
        output.reject(p2);

        Assert.assertEquals(0d, output.avgQueueTime(), DELTA);
        Assert.assertEquals(0d, output.avgServorTime(), DELTA);
        Assert.assertEquals(0d, output.avgSystemTime(), DELTA);
        Assert.assertEquals(0, output.getNumberAccepted());
        Assert.assertEquals(2, output.getNumberReject());
    }

}