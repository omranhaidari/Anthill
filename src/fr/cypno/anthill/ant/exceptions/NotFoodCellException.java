package fr.cypno.anthill.ant.exceptions;

public class NotFoodCellException extends Exception {

    public NotFoodCellException() {
        super("Cette case n'est pas une Source");
    }

}
