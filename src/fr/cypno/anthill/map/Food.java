package fr.cypno.anthill.map;

/**
 * Food est la classe représentant une cellule de la carte composée d'une source de nourriture
 */

public class Food extends Cell {

    // Quantité de nourriture présente sur la cellule
    private double quantity;

    /**
     * Constructeur de la classe Food à partir de coordonnées.
     * 
     * @param x Numéro de ligne
     * @param y Numéro de colonne
     */
    public Food(int x, int y) {
        this(x, y, 0, 0);
    }

    /**
     * Constructeur de la classe Food à partir de coordonnées et d'une 
     * quantité de nourriture initiale. 
     * 
     * @param x Numéro de ligne
     * @param y Numéro de colonne
     * @param quantity Quantité de nourriture initiale
     */
    public Food(int x, int y, double pheromons, double quantity) {
        super(x, y, 0);
        this.quantity = quantity;
    }

    /**
     * Méthode permettant de savoir si la cellule ne contient plus de nourriture.
     * @return true si la cellule ne contient plus de nourriture, false sinon
     */
    public boolean isEmpty() {
        boolean flag = false;
        if (getQuantity() == 0) {
            flag = true;
        }
        return flag;
    }
    
    /**
     * Méthode retournant la quantité de nourriture présente sur la cellule.
     * 
     * @return quantité de nourriture présente sur la cellule
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Méthode modifiant la quantité de nourriture présente sur la cellule.
     * 
     * @return Nouvelle valeur pour la quantité de nourriture présente sur la cellule
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * Méthode retournant une chaîne de caractères utilisée pour l'affichage de la carte
     * Cette chaîne contient un caractère de couleur, le caractère o représentant 
     * la source de nourriture puis un caractère de reset.
     * 
     * @return Chaîne de caractères utilisée pour l'affichage de la carte
     */
    @Override
    public String getChar() {
        return "\u001B[31mo\u001B[0m";
    }
}
