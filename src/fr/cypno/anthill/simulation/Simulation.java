package fr.cypno.anthill.simulation;

import fr.cypno.anthill.Resources;
import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.ant.behavior.BasicBehavior;
import fr.cypno.anthill.map.Map;
import java.io.File;
import java.util.ArrayList;

public final class Simulation {

    private ArrayList<Ant> ants;
    private Map map;

    public Simulation(int nbAnts) {
        this.ants = new ArrayList<>();
        try {
            initialize(nbAnts);
        } catch (Exception ex) {
            System.out.println("Initialisation échouée");
            System.exit(0);
        }
    }

    public void initialize(int nbAnts) throws Exception {
        this.map = new Map(System.getProperty("user.dir") + File.separator + "ressources" + File.separator + "maps" + File.separator + "map.txt");
        Resources.init(map);

        for (int i = 0; i < nbAnts; i++) {
            Ant a = new Ant(20, 20, 0);
            a.setPosition(map.getAnthill());
            a.setBehavior(new BasicBehavior(a));
            ants.add(a);
        }
    }

    public void update() {
        for (Ant a : ants) {
            a.update();
        }
    }

    public void printMap() {
        System.out.println(map.printCellMatrix(ants));
    }
}
