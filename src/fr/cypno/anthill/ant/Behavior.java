package fr.cypno.anthill.ant;

import fr.cypno.anthill.map.Cell;
import fr.cypno.anthill.map.Empty;
import java.util.ArrayList;
 /**
  * Classe abstraite qui gère le comportement des fourmis.
  */
public abstract class Behavior {
    private Ant ant;
    private ArrayList<Probability> probabilities;

    public Behavior(Ant ant) {
        this.ant = ant;
        this.probabilities = new ArrayList<>();
    }
    
    public void computeProbabilities() {
        // Récupérer les cellules voisines
        int size = 3;
        Cell[][] neighbours = new Cell[size][];
        for (int i = 0; i < size; i++) {
            neighbours[i] = new Cell[size];
            for (int j = 0; j < size; j++) {
                neighbours[i][j] = new Empty(56);
            }
        }
    }
}
