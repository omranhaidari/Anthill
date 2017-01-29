/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cypno.anthill.graphics.ui;

import fr.cypno.anthill.graphics.Frame;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class SlowButton extends Button {

    /**
     * Constructeur de la classe SlowButton à partir des coordonnées de son coin
     * supérieur gauche et de ses dimensions.
     * 
     * @param x Abscisse du coin supérieur gauche du bouton
     * @param y Ordonnée du coin supérieur gauche du bouton
     * @param sizeX Largeur du bouton
     * @param sizeY Hauteur du bouton
     */
    public SlowButton(int x, int y, int sizeX, int sizeY) {
        super(x, y, sizeX, sizeY);
    }

    /**
     *
     */
    @Override
    protected void computeDrawing() {
        Rectangle rec = new Rectangle(0, 0, sizeX, sizeY);
        rec.setFill(new ImagePattern(new Image("file:ressources/images/button-slow.png")));
        group.getChildren().add(rec);
    }

    /**
     *
     * @param frame
     */
    @Override
    protected void clickAction(Frame frame) {
        frame.slowDown();
    }
}
