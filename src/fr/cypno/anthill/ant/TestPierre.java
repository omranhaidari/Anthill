package fr.cypno.anthill.ant;

import fr.cypno.anthill.ant.behavior.BasicBehavior;

public class TestPierre {
    public static void main(String[] args) {
        BasicBehavior b = new BasicBehavior(new Ant(15, 15, 4));
        b.computeDestination();
    }
}
