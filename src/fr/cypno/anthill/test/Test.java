package fr.cypno.anthill.test;

import fr.cypno.anthill.simulation.Simulation;

public class Test {

    public static void main(String[] args) {
        Simulation sim = new Simulation(10);
        for (int i = 0; i < 10; i++) {
            sim.update();
            sim.printMap();
        }
    }
}
