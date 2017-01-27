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

/**
 *
 * @author captaincat
 */
public class SlowButton extends Button {
    public SlowButton(int x, int y, int sizeX, int sizeY) {
        super(x, y, sizeX, sizeY);
    }

    @Override
    protected void computeDrawing() {
        Rectangle rec = new Rectangle(0, 0, sizeX, sizeY);
        rec.setFill(new ImagePattern(new Image("file:ressources/images/button-slow.png")));
        group.getChildren().add(rec);
    }

    @Override
    protected void clickAction(Frame frame) {
        frame.slowDown();
    }
}
