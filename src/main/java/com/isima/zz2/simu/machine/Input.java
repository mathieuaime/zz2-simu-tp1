package com.isima.zz2.simu.machine;

import com.isima.zz2.simu.model.Piece;
import com.isima.zz2.simu.queue.Queue;
import lombok.Getter;

/**
 * Input.
 * <p>
 * Created by Mathieu on 16/11/2017.
 */
public class Input {
    private final int dia;

    @Getter
    private int dpe;
    @Getter
    private int cptr;

    public Input(int dia) {
        this.dia = dia;
        this.dpe = 0;
        this.cptr = 0;
    }

    public void action(int date, Queue<Piece> queue, Machine machine, Output output) {
        ++cptr;
        Piece p = new Piece();
        p.setEntryDate(date);

        if (queue.isFull()) {
            p.setExitDate(date);
            output.reject(p);
        } else if (machine.isActivate()) {
            queue.push(p);
        } else {
            machine.push(date, p);
        }

        dpe = date + dia;
    }
}
