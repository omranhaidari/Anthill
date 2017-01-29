package fr.cypno.anthill.ant.exceptions;

/**
 * NotAnthillCellException est la classe représentant une exception générée 
 * dans le cas où une fourmi chercherait à déposer de la nourriture sur une 
 * cellule qui n'est pas la fourmilière.
 */

public class NotAnthillCellException extends Exception {

    /**
     * Constructeur de la classe NotAnthillCellException passant au constructeur
     * de la ca classe mère un message énoncant l'erreur.
     */
    public NotAnthillCellException() {
        super("Cette case n'est pas une Fourmilière");

    }

}
