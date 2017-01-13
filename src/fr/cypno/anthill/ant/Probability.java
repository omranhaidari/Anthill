package fr.cypno.anthill.ant;

import fr.cypno.anthill.map.Cell;

public class Probability {
    private int probability;
    private Cell destination;

    public int getProbability() {
        return probability;
    }

    public Cell getDestination() {
        return destination;
    }

    public Probability(int probability, Cell destination) {
        this.probability = probability;
        this.destination = destination;
    }
}
