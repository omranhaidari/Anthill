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

    public Behavior(Ant ant) {
        this.ant = ant;
        this.probabilities = new ArrayList<>();
    }
    
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
    
    protected void computeProbabilities(Cell[][] cells) {
    }

    protected void clearProbabilities() {
        this.probabilities.clear();
    }

    public void moveTo(Cell dest) {
        this.ant.moveTo(dest);
    }
}
