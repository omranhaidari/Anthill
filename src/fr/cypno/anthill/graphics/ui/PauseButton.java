package fr.cypno.anthill.graphics.ui;

import fr.cypno.anthill.graphics.Frame;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PauseButton extends Button {
    protected boolean inPause;

    public PauseButton(int x, int y, int sizeX, int sizeY) {
        super(x, y, sizeX, sizeY);
        this.inPause = false;
    }

    @Override
    protected void computeDrawing() {
        Rectangle rec = new Rectangle(0, 0, sizeX, sizeY);
        if (!inPause)
            rec.setFill(new ImagePattern(new Image("file:ressources/images/button-pause.png")));
        else
            rec.setFill(new ImagePattern(new Image("file:ressources/images/button-play.png")));
        group.getChildren().add(rec);
    }

    @Override
    protected void clickAction(Frame frame) {
        inPause = !inPause;
        frame.togglePause();
    }
}
