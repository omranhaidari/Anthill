package fr.cypno.anthill.ant.behavior;

import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.ant.exceptions.NotAnthillCellException;
import fr.cypno.anthill.ant.exceptions.NotFoodCellException;
import fr.cypno.anthill.map.Anthill;
import fr.cypno.anthill.map.Cell;
import fr.cypno.anthill.map.Food;
import fr.cypno.anthill.map.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicBehavior extends Behavior {

    /**
     *
     */
    protected boolean returnHome;

    /**
     *
     */
    protected Stack<Cell> cells;

    /**
     *
     * @param ant
     */
    public BasicBehavior(Ant ant) {
        super(ant);
        this.returnHome = false;
        this.cells = new Stack<>();
        this.weights = new int[]{50, 20, 10, 5, 0, 5, 10, 20, 100};
    }

    /**
     *
     * @param map
     * @return
     */
    @Override
    public final Cell computeDestination(Map map) {
        if (!returnHome) {
            return super.computeDestination(map);
        } else {
            return this.cells.pop();
        }
    }

    /**
     *
     * @param cells
     */
    @Override
    protected void computeProbabilities(Cell[][] cells) {
        int direction = ant.getDirection();
        for (int i = 0; i < 8; i++) {
            Cell cell = findCell(cells, (direction + i) % 8);
            probabilities.add(new Probability(weights[i] + (int) cell.getPheromonQuantity(), cell));
        }
    }

    /**
     *
     * @param cells
     * @param direction
     * @return
     */
    protected final Cell findCell(Cell[][] cells, int direction) {
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

    /**
     *
     * @param dest
     */
    @Override
    public void moveTo(Cell dest) {
        if (!returnHome) {
            this.cells.push(ant.getPosition());
        }
        super.moveAnt(dest);
        if (!returnHome && this.ant.getPosition() instanceof Food) {
            try {
                this.ant.pullFood();
                this.returnHome = true;
            } catch (NotFoodCellException ex) {
                ex.printStackTrace();
            }
        }
        if (returnHome) {
            if (this.ant.getPosition() instanceof Anthill) {
                try {
                    this.ant.pushFood();
                    this.returnHome = false;
                    this.cells.clear();
                } catch (NotAnthillCellException ex) {
                    ex.printStackTrace();
                }
            }
            else 
                this.ant.dropPheromons();
        }
    }
}
