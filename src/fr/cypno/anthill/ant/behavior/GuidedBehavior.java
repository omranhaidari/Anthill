package fr.cypno.anthill.ant.behavior;

import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.ant.exceptions.NotAnthillCellException;
import fr.cypno.anthill.ant.exceptions.NotFoodCellException;
import fr.cypno.anthill.map.Anthill;
import fr.cypno.anthill.map.Cell;
import fr.cypno.anthill.map.Food;
import fr.cypno.anthill.map.Map;
import java.util.List;

public class GuidedBehavior extends AdvancedBehavior {
    protected Map map;

    public GuidedBehavior(Ant ant, Map map) {
        super(ant);
        this.map = map;
    }

    @Override
    public void moveTo(Cell dest) {
        super.moveAnt(dest);
        if (!returnHome && this.ant.getPosition() instanceof Food) {
            try {
                this.ant.pullFood();
                this.returnHome = true;
                List<Cell> path = AStar.getShortestPath(map, ant.getPosition(), map.getAnthill());
                for (int i = path.size() - 1; i >= 0; i--)
                    cells.push(path.get(i));
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
