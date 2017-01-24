package fr.cypno.anthill.test;

import fr.cypno.anthill.simulation.Simulation;

public class Test {

    public static void main(String[] args) {
        Simulation sim = new Simulation(1);
        for (int i = 0; i < 200; i++) {
            sim.update();
            sim.printMap();
        }
    }
}
