package fr.cypno.anthill.ant.exceptions;

/**
 * NotFoodCellException est la classe représentant une exception générée 
 * dans le cas où une fourmi chercherait à récupérer de la nourriture depuis une 
 * cellule qui n'est pas une source de nourriture.
 */

public class NotFoodCellException extends Exception {

    /**
     * Constructeur de la classe NotFoodCellException passant au constructeur
     * de la ca classe mère un message énoncant l'erreur.
     */
    public NotFoodCellException() {
        super("Cette case n'est pas une Source");
    }

}
