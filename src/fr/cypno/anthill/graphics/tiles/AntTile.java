package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.ant.Ant;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class AntTile extends Tile{

    /**
     *
     * @param ant
     * @param cellSize
     */
    public AntTile(Ant ant, int cellSize) {
        super(ant, cellSize);
    }

    /**
     *
     */
    @Override
    protected void computeDrawing() {
        Rectangle rec = new Rectangle(0, 0, cellSize, cellSize);
        rec.setFill(new ImagePattern(new Image("file:ressources/images/ant-" + ((Ant) positionnable).getDirection() + ".png")));
        group.getChildren().add(rec);
    }
}
