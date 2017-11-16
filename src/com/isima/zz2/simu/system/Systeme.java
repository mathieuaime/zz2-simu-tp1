package com.isima.zz2.simu.system;

import com.isima.zz2.simu.machine.Input;
import com.isima.zz2.simu.machine.Machine;
import com.isima.zz2.simu.machine.Output;
import com.isima.zz2.simu.model.Piece;
import com.isima.zz2.simu.queue.Queue;

/**
 * Created by Mathieu on 16/11/2017.
 */
public class Systeme {
    private int duree;

    private Input input;
    private Queue<Piece> queue;
    private Machine machine;
    private Output output;

    public Systeme(int dia, int treatmentTime, int duree, int queueSize) {
        this.duree = duree;

        this.input = new Input(dia);
        this.queue = new Queue<>(queueSize);
        this.machine = new Machine(treatmentTime);
        this.output = new Output();
    }

    private ACTION nextAction() {
        return input.getDpe() < machine.getDpe() ? ACTION.INPUT : ACTION.MACHINE;
    }

    private void displayStat() {
        System.out.println();
        System.out.println("\nQueue : " +  queue.toString()
                + "\nPiece dans la machine : " + (machine.getPiece() != null ? machine.getPiece().getNumber() : "Pas de pièces")
                + "\nTemps moyen de séjour dans le système : " + output.avgSystemTime()
                + "\nTemps moyen de séjour dans la file : " + output.avgQueueTime()
                + "\nTemps moyen de séjour dans la machine : " + output.avgServorTime()
                + "\nNombre de pièces créées : " + input.getCptr()
                + "\nNombre de pièces acceptées : " + output.getNumberAccepted()
                + "\nNombre de pièces refusées : " + output.getNumberReject());
    }

    public void simulate() {
        int date = 0;

        while (date < duree) {
            switch (nextAction()) {
                case INPUT:
                    date = input.getDpe();
                    input.action(date, queue, machine, output);
                    break;
                case MACHINE:
                    date = machine.getDpe();
                    machine.action(date, queue, output);
                    break;
                default:
                    break;
            }

            if (date % 100 == 0) {

                displayStat();
            }
        }
    }
}
