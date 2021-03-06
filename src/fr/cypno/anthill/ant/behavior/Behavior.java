package fr.cypno.anthill.ant.behavior;

import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.map.Cell;
import fr.cypno.anthill.map.Map;
import java.util.ArrayList;
import java.util.Random;

 /**
  * Classe abstraite qui gère le comportement des fourmis.
  */

public abstract class Behavior {

    protected Ant ant;
    protected ArrayList<Probability> probabilities;
    protected int[] weights;

    /**
     * Constructeur de la classe Behavior à partir d'une fourmi
     * @param ant Fourmi
     */
    public Behavior(Ant ant) {
        this.ant = ant;
        this.probabilities = new ArrayList<>();
    }
    
    /**
     * Méthode qui retourne une destination déterminée selon les probabilités
     * définies au préalable. Si les cellules adjacentes ne sont pas praticables,
     * null est renvoyé.
     * 
     * @param map Matrice de cellules
     * @return destination ou null si aucune cellule adjacente n'est accessible
     */
    public Cell computeDestination(Map map) {
        int x = ant.getPosition().getX();
        int y = ant.getPosition().getY();
        Cell[][] cells = map.getMatrix(x-1, y-1, x+1, y+1);
        clearProbabilities();
        computeProbabilities(cells);
        int probaMax = 0;
        for (Probability p : probabilities)
            probaMax += p.getProbability();
        int r = new Random().nextInt(probaMax);
        for (Probability p : probabilities) {
            r -= p.getProbability();
            if (r <= 0)
                return p.getDestination();
        }
        return null;
    }

    /**
     *
     * @param cells
     */
    protected void computeProbabilities(Cell[][] cells) {
    }

    /**
     *
     */
    protected final void clearProbabilities() {
        this.probabilities.clear();
    }

    /**
     *
     * @param dest
     */
    public void moveTo(Cell dest) {
    }

    /**
     *
     * @param dest
     */
    public final void moveAnt(Cell dest) {
        this.ant.moveTo(dest);
    }
}
