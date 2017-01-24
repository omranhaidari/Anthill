package fr.cypno.anthill.test;

import fr.cypno.anthill.simulation.Simulation;

public class Test {

    public static void main(String[] args) {
        Simulation sim = new Simulation(1);
        for (int i = 0; i < 1000; i++) {
            sim.update(0.5);
            System.out.println("-------------\nLoop: " + i);
            sim.printMap();
        }
    }
}
