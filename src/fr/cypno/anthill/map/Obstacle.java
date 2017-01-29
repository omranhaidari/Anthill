package fr.cypno.anthill.map;

/**
 * Obstacle est la classe représentant une cellule composée d'un obstacle
 */

public class Obstacle extends Cell{
    
   /**
     * Constructeur de la classe Obstacle à partir de coordonnées avec une quantité 
     * de phéromones nulle.
     * 
     * @param x  Numéro de ligne
     * @param y  Numéro de colonne
     */
    public Obstacle(int x, int y) {
        this(x, y, 0);
    }

    /**
     * Constructeur de la classe Obstacle à partir de coordonnées et d'une 
     * quantité de phéromones initiale. 
     * 
     * @param x Numéro de ligne
     * @param y Numéro de colonne
     * @param pheromons Quantité de phéromones initiale
     */
    public Obstacle(int x, int y, double pheromons) {
        super(x, y, pheromons);
    }

    /**
     * Méthode retournant une chaîne de caractères utilisée pour l'affichage de la carte.
     * 
     * @return Chaîne de caractères utilisée pour l'affichage de la carte
     */
    @Override
    public String getChar() {
        return "#";
    }
}
