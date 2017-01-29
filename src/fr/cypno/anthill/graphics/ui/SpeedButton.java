package fr.cypno.anthill.graphics.ui;

import fr.cypno.anthill.graphics.Frame;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * SpeedButton est la classe représentant un bouton permettant d'accélerer la simulation
 */

public class SpeedButton extends Button {

    /**
     * Constructeur de la classe SpeedButton à partir des coordonnées de son coin
     * supérieur gauche et de ses dimensions.
     * 
     * @param x Abscisse du coin supérieur gauche du bouton
     * @param y Ordonnée du coin supérieur gauche du bouton
     * @param sizeX Largeur du bouton
     * @param sizeY Hauteur du bouton
     */
    public SpeedButton(int x, int y, int sizeX, int sizeY) {
        super(x, y, sizeX, sizeY);
    }

    /**
     *
     */
    @Override
    protected void computeDrawing() {
        Rectangle rec = new Rectangle(0, 0, sizeX, sizeY);
        rec.setFill(new ImagePattern(new Image("file:ressources/images/button-speed.png")));
        group.getChildren().add(rec);
    }

    /**
     *
     * @param frame
     */
    @Override
    protected void clickAction(Frame frame) {
        frame.speedUp();
    }
}
