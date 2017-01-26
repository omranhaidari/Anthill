package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.ant.Ant;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class AntTile implements Tile{
    private Ant ant;

    public AntTile(Ant ant) {
        this.ant = ant;
    }

    @Override
    public Node draw(int cellSize) {
        Rectangle rec = new Rectangle(
                ant.getY() * cellSize + ant.getY(),
                ant.getX() * cellSize + ant.getX(),
                cellSize, cellSize);
        rec.setFill(new ImagePattern(new Image("file:ressources/images/ant-" + ant.getDirection() + ".png")));
        return rec;
    }
}
