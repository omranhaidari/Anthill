package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.map.Anthill;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AnthillTile implements Tile {
    private Anthill anthill;

    public AnthillTile(Anthill anthill) {
        this.anthill = anthill;
    }

    @Override
    public Node draw(int cellSize) {
        Rectangle rec = new Rectangle(
                anthill.getY() * cellSize + anthill.getY(),
                anthill.getX() * cellSize + anthill.getX(),
                cellSize, cellSize);
        rec.setFill(Color.GREEN);
        return rec;
    }
}
