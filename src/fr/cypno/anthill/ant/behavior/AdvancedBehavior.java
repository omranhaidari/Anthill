package fr.cypno.anthill.ant.behavior;

import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.map.Cell;
import fr.cypno.anthill.map.Food;
import fr.cypno.anthill.map.Obstacle;


public class AdvancedBehavior extends BasicBehavior{
    
    public AdvancedBehavior(Ant ant) {
        super(ant);
    }
    
    @Override
    protected void computeProbabilities(Cell[][] cells) {
        super.computeSuperProbabilities(cells);
        int direction = ant.getDirection();
        for (int i = 0; i < 8; i++) {
            Cell cell = findCell(cells, (direction + i) % 8);
            if(cell instanceof Obstacle) {
                probabilities.add(new Probability(weights[4] + (int) cell.getPheromonQuantity(), cell));
            }
            else if(cell instanceof Food) {
                probabilities.add(new Probability(weights[8] + (int) cell.getPheromonQuantity(), cell));
            }
            else
                probabilities.add(new Probability(weights[i] + (int) cell.getPheromonQuantity(), cell));
        }
    }
    
}
