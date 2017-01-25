package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.map.Empty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EmptyTile implements Tile {
    private Empty empty;

    public EmptyTile(Empty empty) {
        this.empty = empty;
    }

    @Override
    public Node draw(int cellSize) {
        Group group = new Group();
        group.setTranslateX(empty.getY() * cellSize + empty.getY());
        group.setTranslateY(empty.getX() * cellSize + empty.getX());
        Rectangle rec = new Rectangle(0, 0, cellSize, cellSize);
        rec.setFill(Color.WHITE);
        Rectangle rec2 = new Rectangle(0, 0, cellSize, cellSize);
        rec2.setFill(Color.rgb(255, 0, 0, Math.min(empty.getPheromonQuantity() / 200, 1)));
        group.getChildren().add(rec);
        group.getChildren().add(rec2);
        return group;
    }
}
