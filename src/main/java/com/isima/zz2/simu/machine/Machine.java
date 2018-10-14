package com.isima.zz2.simu.machine;

import com.isima.zz2.simu.model.Piece;
import com.isima.zz2.simu.queue.Queue;
import com.isima.zz2.simu.queue.exception.QueueEmptyException;
import lombok.Getter;

/**
 * Machine.
 * <p>
 * Created by Mathieu on 16/11/2017.
 */
public class Machine {
    private final int treatmentTime;

    @Getter
    private boolean activate;
    @Getter
    private int dpe;
    @Getter
    private Piece piece;

    public Machine(int treatmentTime) {
        this.activate = false;
        this.dpe = Integer.MAX_VALUE;
        this.piece = null;
        this.treatmentTime = treatmentTime;
    }

    void push(int date, Piece piece) {
        piece.setServorDate(date);
        this.piece = piece;
        activate = true;
        dpe = date + treatmentTime;
    }

    public void action(int date, Queue<Piece> queue, Output output) {
        if (activate) {
            piece.setExitDate(date);
            output.accept(piece);
        }

        try {
            Piece p = queue.pop();
            push(date, p);
        } catch (QueueEmptyException e) {
            piece = null;
            activate = false;
            dpe = Integer.MAX_VALUE;
        }
    }
}
