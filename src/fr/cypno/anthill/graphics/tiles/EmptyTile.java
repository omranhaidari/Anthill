package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.map.Empty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class EmptyTile extends Tile {

    /**
     *
     * @param empty
     * @param cellSize
     */
    public EmptyTile(Empty empty, int cellSize) {
        super(empty, cellSize);
    }

    /**
     *
     */
    @Override
    protected void computeDrawing() {
        Rectangle rec = new Rectangle(0, 0, cellSize, cellSize);
        rec.setFill(Color.WHITE);
        Rectangle rec2 = new Rectangle(0, 0, cellSize, cellSize);
        rec2.setFill(Color.rgb(255, 0, 0, Math.min(((Empty) positionnable).getPheromonQuantity() / 100, 1)));
        group.getChildren().add(rec);
        group.getChildren().add(rec2);
    }
}
