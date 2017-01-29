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
     * Retourne la carte.
     * @return la carte
     */
    public Map getMap() {
        return this.map;
    }

    /**
     * Retourne la liste des fourmis.
     * @return la liste des fourmis
     */
    public ArrayList<Ant> getAnts() {
        return ants;
    }

    /**
     * Définit la fenêtre dans laquelle est lancée la simulation.
     * @param frame la fenêtre
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    /**
     * Détermine si la simulation est en pause.
     * @return vrai, si la simulation est en pause
     */
    public boolean isInPause() {
        return inPause;
    }

    /**
     * Met en pause la simulation.
     */
    public synchronized void setPause() {
        this.inPause = true;
        pauseTime = new Date().getTime();
    }

    /**
     * Enlève la pause.
     */
    public synchronized void setUnPause() {
        this.inPause = false;
        this.lastUpdate = new Date().getTime() + lastUpdate - pauseTime;
    }

    /**
     * Retourne le temps d'attente entre chaque mise à jour.
     * @return le temps en millisecondes
     */
    public long getStep() {
        return step;
    }

    /**
     * Définit le temps d'attente entre chaque mise à jour.
     * @param step le temps en millisecondes
     */
    public synchronized void setStep(long step) {
        this.step = step;
    }

    /**
     * Crée une simulation.
     * @param nbAnts le nombre de fourmis
     * @param pheromonDecrease la diminution de phéromones à chaque mise à jour
     * @param step le temps d'attente entre chaque mise à jour
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
     * Initialise les paramètres et charge la carte.
     * @param nbAnts le nombre de fourmis
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
     * Met à jour les fourmis et les cellules de la carte.
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
     * Affiche la carte sur la sortie standard.
     */
    public void printMap() {
        System.out.println(map.printCellMatrix(ants));
    }

    /**
     * Lance l'exécution du thread avec une boucle infinie.
     */
    @Override
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
