package com.isima.zz2.simu.tp1.machine;

import com.isima.zz2.simu.tp1.model.Piece;
import com.isima.zz2.simu.tp1.queue.Queue;
import com.isima.zz2.simu.tp1.queue.exception.QueueEmptyException;

/**
 * Created by Mathieu on 16/11/2017.
 */
public class Machine {
    private boolean state;
    private int dpe;
    private int treatmentTime;
    private Piece piece;

    public Machine(int treatmentTime) {
        this.state = false;
        this.dpe = 999999;
        this.piece = null;
        this.treatmentTime = treatmentTime;
    }

    public int getDpe() {
        return dpe;
    }

    public boolean isState() {
        return state;
    }

    public void activate() {
        state = true;
    }

    public void desactivate() {
        state = false;
    }

    public void push(int date, Piece p) {
        p.setServorDate(date);
        this.piece = piece;
        state = true;
        dpe = date + treatmentTime;
    }

    public void action(int date, Queue<Piece> queue, Output output) {
        if (state) {
            piece.setExitDate(date);
            output.accept(piece);
            this.piece = null;
        }

        try {
            Piece p = queue.pop();
            push(date, p);
        } catch (QueueEmptyException e) {
            state = false;
            dpe = 9999999;
        }
    }
}
