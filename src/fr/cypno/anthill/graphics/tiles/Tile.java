package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.graphics.Drawable;
import fr.cypno.anthill.graphics.Positionnable;
import javafx.scene.Group;
import javafx.scene.Node;

public abstract class Tile implements Drawable {

    /**
     *
     */
    protected Positionnable positionnable;

    /**
     *
     */
    protected int cellSize;

    /**
     *
     */
    protected Group group;

    /**
     *
     * @param positionnable
     * @param cellSize
     */
    public Tile(Positionnable positionnable, int cellSize) {
        this.positionnable = positionnable;
        this.cellSize = cellSize;
    }

    /**
     *
     * @return
     */
    @Override
    public final Node draw() {
        group = new Group();
        group.setTranslateX(positionnable.getY() * cellSize + positionnable.getY());
        group.setTranslateY(positionnable.getX() * cellSize + positionnable.getX());
        computeDrawing();
        return group;
    }

    /**
     *
     */
    protected void computeDrawing() {
    }
}
