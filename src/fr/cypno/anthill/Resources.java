package fr.cypno.anthill;

import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.map.Map;
import fr.cypno.anthill.simulation.Simulation;
import java.util.ArrayList;

public class Resources {
    private static Simulation simulation = null;

    public static Map getMap() {
        return simulation.getMap();
    }

    public static ArrayList<Ant> getAnts() {
        return simulation.getAnts();
    }

    public static Simulation getSimulation() {
        return simulation;
    }

    public static void init(int nbAnts) {
        Resources.simulation = new Simulation(nbAnts);
    }
}
