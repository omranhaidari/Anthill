package fr.cypno.anthill.ant.behavior;

import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.map.Cell;
import fr.cypno.anthill.map.Empty;
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
    
    public Cell computeDestination() {
        // Récupérer les cellules voisines
        int size = 3;
        Cell[][] cells = new Cell[size][];
        for (int i = 0; i < size; i++) {
            cells[i] = new Cell[size];
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Empty(56);
            }
        }
        computeProbabilities(cells);
        int probaMax = 0;
        for (Probability p : probabilities)
            probaMax += p.getProbability();
        int r = new Random().nextInt(probaMax);
        System.out.println(r); 
        for (Probability p : probabilities) {
            r -= p.getProbability();
            if (r <= 0)
                return p.getDestination();
        }
        return null;
    }
    
    protected void computeProbabilities(Cell[][] cells) {
        
    }
}
