package fr.cypno.anthill.ant.behavior;

import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.map.Anthill;
import fr.cypno.anthill.map.Cell;
import fr.cypno.anthill.map.Food;
import fr.cypno.anthill.map.Obstacle;


public class AdvancedBehavior extends BasicBehavior{
    
    public AdvancedBehavior(Ant ant) {
        super(ant);
    }
    
    @Override
    protected void computeProbabilities(Cell[][] cells) {
        if (ant.getPosition() instanceof Anthill) {
            for (int i = 0; i < 8; i++) {
                Cell cell = findCell(cells, i);
                probabilities.add(new Probability(1 + (int) cell.getPheromonQuantity(), cell));
            }
        }
        else {
            int direction = ant.getDirection();
            for (int i = 0; i < 8; i++) {
                Cell cell = findCell(cells, (direction + i) % 8);
                if (cell instanceof Food) {
                    probabilities.clear();
                    probabilities.add(new Probability(1, cell));
                    return;
                }
                if (!(cell instanceof Obstacle))
                    probabilities.add(new Probability(weights[i] + weights[i] * (int) cell.getPheromonQuantity(), cell));
            }
        }
    }
}
