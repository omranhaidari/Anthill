package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.map.Anthill;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AnthillTile implements Tile {
    private Anthill anthill;

    public AnthillTile(Anthill anthill) {
        this.anthill = anthill;
    }

    @Override
    public Node draw(int cellSize) {
        Group group = new Group();
        group.setTranslateX(anthill.getY() * cellSize + anthill.getY());
        group.setTranslateY(anthill.getX() * cellSize + anthill.getX());
        Rectangle rec = new Rectangle(0, 0, cellSize, cellSize);
        rec.setFill(Color.GREEN);
        Text text = new Text(0, cellSize / 2, "" + anthill.getQuantity());
        text.setFont(new Font(10));
        text.setFill(Color.BLACK);
        group.getChildren().add(rec);
        group.getChildren().add(text);
        return group;
    }
}
