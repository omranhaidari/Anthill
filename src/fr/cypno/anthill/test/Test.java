package fr.cypno.anthill.test;

import fr.cypno.anthill.ant.behavior.AStar;
import fr.cypno.anthill.map.Cell;
import fr.cypno.anthill.simulation.Simulation;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Simulation sim = new Simulation(1);/*
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
        }
        System.out.println("-------------\nSimulation finie ! \n-------------");*/
        sim.printMap();
        Cell start = sim.getMap().getCell(1, 1);
        Cell goal = sim.getMap().getCell(8, 11);
       ///for(int i = 0; i < sim.getMap().getMap().length; i++);
        //System.out.println("x = " + goal.getX() + ", y = " + goal.getY());
        List<Cell> path = AStar.getShortestPath(sim.getMap(), start, goal);
        /*
        Cell c = sim.getMap().getCell(2, 1);
        c.computeHeuristicCost(goal);
        System.out.println(c.getHeuristicCost());*/

        for (int i = 0; i < path.size(); i++) {
            System.out.print("  -> (" + path.get(i).getX() + ", " + path.get(i).getY() + ")");
        }
    }
}
