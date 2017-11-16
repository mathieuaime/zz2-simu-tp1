package com.isima.zz2.simu.cli;

import com.isima.zz2.simu.system.Systeme;

/**
 * Created by Mathieu on 16/11/2017.
 */
public class Main {
    public static int main(String[] args) {
        int dia = 1;
        int treatmentTime = 4;
        int duree = 10000;
        int queueSize = 10;

        Systeme systeme = new Systeme(dia, treatmentTime, duree, queueSize);
        systeme.simulate();

        return 1;
    }
}
