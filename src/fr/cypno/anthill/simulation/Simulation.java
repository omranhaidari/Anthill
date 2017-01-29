package fr.cypno.anthill.simulation;

import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.ant.behavior.*;
import fr.cypno.anthill.graphics.Frame;
import fr.cypno.anthill.map.Cell;
import fr.cypno.anthill.map.Empty;
import fr.cypno.anthill.map.Food;
import fr.cypno.anthill.map.Map;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public final class Simulation implements Runnable {

    private ArrayList<Ant> ants;
    private Map map;
    private Frame frame;
    private double pheromonDecrease;
    private long step;
    private long lastUpdate;
    private long pauseTime;
    private boolean inPause;

    /**
     *
     * @return
     */
    public Map getMap() {
        return this.map;
    }

    /**
     *
     * @return
     */
    public ArrayList<Ant> getAnts() {
        return ants;
    }

    /**
     *
     * @param frame
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    /**
     *
     * @return
     */
    public boolean isInPause() {
        return inPause;
    }

    /**
     *
     */
    public synchronized void setPause() {
        this.inPause = true;
        pauseTime = new Date().getTime();
    }

    /**
     *
     */
    public synchronized void setUnPause() {
        this.inPause = false;
        this.lastUpdate = new Date().getTime() + lastUpdate - pauseTime;
    }

    /**
     *
     * @return
     */
    public long getStep() {
        return step;
    }

    /**
     *
     * @param step
     */
    public synchronized void setStep(long step) {
        this.step = step;
    }

    /**
     *
     * @param nbAnts
     * @param pheromonDecrease
     * @param step
     */
    public Simulation(int nbAnts, double pheromonDecrease, long step) {
        this.ants = new ArrayList<>();
        this.pheromonDecrease = pheromonDecrease;
        this.step = step;
        this.lastUpdate = new Date().getTime();
        this.pauseTime = 0;
        this.inPause = false;
        this.frame = null;
        try {
            initialize(nbAnts);
        } catch (Exception ex) {
            System.out.println("Initialisation échouée");
            System.exit(0);
        }
    }

    /**
     *
     * @param nbAnts
     * @throws Exception
     */
    public void initialize(int nbAnts) throws Exception {
        this.map = new Map(System.getProperty("user.dir") + File.separator + "ressources" + File.separator + "maps" + File.separator + "map.txt");
        for (int i = 0; i < nbAnts; i++) {
            Ant a = new Ant(map, 20, 20, 0);
            a.setPosition(map.getAnthill());
            a.setBehavior(new GuidedBehavior(a, map));
            ants.add(a);
        }
    }

    /**
     *
     */
    public void update() {
        for (Ant a : ants) {
            a.update();
        }
        Cell[][] cells = map.getMap();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].reducePheromons(pheromonDecrease);
                if(cells[i][j] instanceof Food) {
                    if(((Food)cells[i][j]).isEmpty()) 
                        cells[i][j] = new Empty(i,j,cells[i][j].getPheromonQuantity());
                }
            }
        }
    }

    /**
     *
     */
    public void printMap() {
        System.out.println(map.printCellMatrix(ants));
    }

    public void run() {
        while (true) {
            System.out.print("");
            if (!inPause) {
                long update = new Date().getTime();
                if (update - lastUpdate > getStep()) {
                    lastUpdate = update;
                    update();
                    frame.notifyFrame();
                }
            }
        }
    }
}
