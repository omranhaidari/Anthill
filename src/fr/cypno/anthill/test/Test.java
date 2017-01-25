package fr.cypno.anthill.test;

import fr.cypno.anthill.simulation.Simulation;

public class Test {

    public static void main(String[] args) {
        Simulation sim = new Simulation(1);
        double mapQuantity = sim.getMap().foodMapQuantity(sim.getMap().getMap());
        for (int i = 0; i <= 1000; i++) {
            if(sim.getMap().getAnthill().isFull(mapQuantity)) {
                
                break;
            }
            sim.update(0.5);
            sim.printMap();
            System.out.println("-------------\nLoop: " + i);
            System.out.println("Quantité nourriture dans Anthill : " + sim.getMap().getAnthill().getQuantity());
            System.out.println("Quantité nourriture hors Anthill : " + sim.getMap().foodMapQuantity(sim.getMap().getMap()));
            //Thread.sleep(500);
        }
        System.out.println("-------------\nSimulation finie ! \n-------------");
    }
}
