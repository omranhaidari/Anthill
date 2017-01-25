package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.map.Obstacle;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ObstacleTile implements Tile{
    private Obstacle obstacle;

    public ObstacleTile(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    @Override
    public Node draw(int cellSize) {
        Rectangle rec = new Rectangle(
                obstacle.getY() * cellSize + obstacle.getY(),
                obstacle.getX() * cellSize + obstacle.getX(),
                cellSize, cellSize);
        rec.setFill(Color.BLACK);
        return rec;
    }
}
