package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.map.Obstacle;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ObstacleTile extends Tile{

    /**
     *
     * @param obstacle
     * @param cellSize
     */
    public ObstacleTile(Obstacle obstacle, int cellSize) {
        super(obstacle, cellSize);
    }

    /**
     *
     */
    @Override
    protected void computeDrawing() {
        Rectangle rec = new Rectangle(0, 0, cellSize, cellSize);
        rec.setFill(Color.BLACK);
        group.getChildren().add(rec);
    }
}
