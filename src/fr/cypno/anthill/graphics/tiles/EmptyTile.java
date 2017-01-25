package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.map.Empty;
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
        Rectangle rec = new Rectangle(
                empty.getY() * cellSize + empty.getY(),
                empty.getX() * cellSize + empty.getX(),
                cellSize, cellSize);
        rec.setFill(Color.WHITE);
        return rec;
    }
}
