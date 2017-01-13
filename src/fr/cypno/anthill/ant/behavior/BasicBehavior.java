package fr.cypno.anthill.ant.behavior;

import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.map.Cell;

public class BasicBehavior extends Behavior {
    public BasicBehavior(Ant ant) {
        super(ant);
        this.weights = new int[] { 50, 20, 10, 5, 0, 5, 10, 20 };
    }

    @Override
    protected void computeProbabilities(Cell[][] cells) {
        int direction = ant.getDirection();
        for (int i = 0; i < 8; i++) {
            probabilities.add(new Probability(weights[i], findCell(cells, (direction + i) % 8)));
        }
    }
    
    private Cell findCell(Cell[][] cells, int direction) {
        switch (direction) {
            case 0:
                return cells[0][1];
            case 1:
                return cells[0][2];
            case 2:
                return cells[1][2];
            case 3:
                return cells[2][2];
            case 4:
                return cells[2][1];
            case 5:
                return cells[2][0];
            case 6:
                return cells[1][0];
            default:
                return cells[0][0];
        }
    }
}
