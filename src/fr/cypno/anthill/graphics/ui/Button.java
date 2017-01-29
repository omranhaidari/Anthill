package fr.cypno.anthill.graphics.ui;

import fr.cypno.anthill.graphics.Drawable;
import fr.cypno.anthill.graphics.Frame;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Button est la classe représentant un bouton
 */

public abstract class Button implements Drawable {

    protected Group group;
    protected int x, y, sizeX, sizeY;

    /**
     * Constructeur de la classe Button à partir des coordonnées de son coin
     * supérieur gauche et de ses dimensions.
     * 
     * @param x Abscisse du coin supérieur gauche du bouton
     * @param y Ordonnée du coin supérieur gauche du bouton
     * @param sizeX Largeur du bouton
     * @param sizeY Hauteur du bouton
     */
    public Button(int x, int y, int sizeX, int sizeY) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     *
     * @return
     */
    @Override
    public final Node draw() {
        group = new Group();
        group.setTranslateX(x);
        group.setTranslateY(y);
        computeDrawing();
        return group;
    }

    /**
     *
     */
    protected void computeDrawing() {
        Rectangle rec = new Rectangle(0, 0, sizeX, sizeY);
        rec.setFill(Color.WHITE);
        group.getChildren().add(rec);
    }

    /**
     *
     * @param x
     * @param y
     * @param frame
     */
    public final void click(double x, double y, Frame frame) {
        if (x > this.x && x < this.x + this.sizeX &&
                y > this.y && y < this.y + this.sizeY)
            clickAction(frame);
    }

    /**
     *
     * @param frame
     */
    protected void clickAction(Frame frame) {
    }
}
