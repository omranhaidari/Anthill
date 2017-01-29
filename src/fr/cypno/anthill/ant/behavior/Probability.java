package fr.cypno.anthill.ant.behavior;

import fr.cypno.anthill.map.Cell;

public class Probability {
    private int probability;
    private Cell destination;

    /**
     * Constructeur de la classe Probability à partir d'une probabilité et d'une
     * destination.
     * 
     * @param probability Probabilité
     * @param destination Destination
     */
    public Probability(int probability, Cell destination) {
        this.probability = probability;
        this.destination = destination;
    }

    /**
     * Méthode permettant d'obtenir la probabilité
     * @return probabilité
     */
    public int getProbability() {
        return probability;
    }

    /**
     *  Méthode permettant d'obtenir la destination
     * @return destination
     */
    public Cell getDestination() {
        return destination;
    }
    
    /**
     * Méthode retournant d'obtenir la destination
     * @return destination
     */
    @Override
    public String toString() {
        return "" + probability;
    }
}
