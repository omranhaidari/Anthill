package fr.cypno.anthill.map;

/**
 * Cell est la classe représentant une cellule de la carte composée d'une fourmilière
 */

public class Anthill extends Cell{
    // Quantité de nourriture présente dans la fourmilière
    private double foodQuantity;
    
    /**
     * Constructeur de la classe Anthill à partir de coordonnées.
     * 
     * @param x Numéro de ligne
     * @param y Numéro de colonne
     */
    public Anthill(int x, int y) {
        this(x, y, 0);
    }
    
    /**
     * Constructeur de la classe Anthill à partir de coordonnées et d'une 
     * quantité de nourriture initiale. 
     * 
     * @param x Numéro de ligne
     * @param y Numéro de colonne
     * @param quantity Quantité de nourriture initiale
     */
    public Anthill(int x, int y, double quantity){
        super(x ,y);
        this.foodQuantity = quantity;
    }

    /**
     * F
     * 
     * @return quantité de nourriture présente dans la fourmilière
     */
    public double getQuantity() {
        return foodQuantity;
    }

    /**
     * Méthode modifiant la quantité de nourriture présente dans la fourmilière.
     * 
     * @param quantity Nouvelle valeur pour la quantité de nourriture présente dans la fourmilière
     */
    public void setQuantity(double quantity) {
        this.foodQuantity = quantity;
    }
    
    /**
     * Méthode ajoutant une quantité de nourriture quantity au stock de la fourmilière.
     * 
     * @param quantity quantité de nourriture à ajouter au stock de la fourmilière
     */
    public void addFood(double quantity) {
        setQuantity(getQuantity() + quantity);
    }

    /**
     * Méthode retournant une chaîne de caractères utilisée pour l'affichage de la carte
     * Cette chaîne contient un caractère de couleur, le caractère x représentant 
     * la fourmilière puis un caractère de reset.
     * 
     * @return Chaîne de caractères utilisée pour l'affichage de la carte
     */
    @Override
    public String getChar() {
        return "\u001B[34mx\u001B[0m";
    }
    
    /**
     * Méthode permettant de savoir si la fourmilière contient toute la nourriture
     * initialement présente dans les sources au début de la simulation.
     * 
     * @param mapQuantity  Quantité totale de nourriture présente dans les sources
     * @return true si la fourmilière contient toute la nourriture initialement 
     * présente dans les sources au début de la simulation, false sinon
     */
    public boolean isFull(double mapQuantity) {
        if(mapQuantity == this.foodQuantity)
            return true;
        else
            return false;
    }
}
