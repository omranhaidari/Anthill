package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.ant.Ant;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AntTile implements Tile{
    private Ant ant;

    public AntTile(Ant ant) {
        this.ant = ant;
    }

    @Override
    public Node draw(int cellSize) {
        Text text = new Text(
                ant.getY() * cellSize + ant.getY(),
                ant.getX() * cellSize + ant.getX() + cellSize,
                "1");
        text.setFont(new Font(cellSize));
        text.setFill(Color.CYAN);
        return text;
    }
}
