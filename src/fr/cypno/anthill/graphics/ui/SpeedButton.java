package fr.cypno.anthill.graphics.ui;

import fr.cypno.anthill.graphics.Frame;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class SpeedButton extends Button {
    public SpeedButton(int x, int y, int sizeX, int sizeY) {
        super(x, y, sizeX, sizeY);
    }

    @Override
    protected void computeDrawing() {
        Rectangle rec = new Rectangle(0, 0, sizeX, sizeY);
        rec.setFill(new ImagePattern(new Image("file:ressources/images/button-speed.png")));
        group.getChildren().add(rec);
    }

    @Override
    protected void clickAction(Frame frame) {
        frame.speedUp();
    }
}
