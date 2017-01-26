package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.map.Anthill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AnthillTile extends Tile {
    public AnthillTile(Anthill anthill, int cellSize) {
        super(anthill, cellSize);
    }

    @Override
    protected void computeDrawing() {
        Rectangle rec = new Rectangle(0, 0, cellSize, cellSize);
        rec.setFill(Color.GREEN);
        Text text = new Text(0, cellSize / 2, "" + ((Anthill) positionnable).getQuantity());
        text.setFont(new Font(10));
        text.setFill(Color.BLACK);
        group.getChildren().add(rec);
        group.getChildren().add(text);
    }
}
