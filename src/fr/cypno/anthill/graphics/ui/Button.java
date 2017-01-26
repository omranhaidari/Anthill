package fr.cypno.anthill.graphics.ui;

import fr.cypno.anthill.graphics.Drawable;
import fr.cypno.anthill.graphics.Frame;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Button implements Drawable {
    protected Group group;
    protected int x, y, sizeX, sizeY;

    public Button(int x, int y, int sizeX, int sizeY) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public final Node draw() {
        group = new Group();
        group.setTranslateX(x);
        group.setTranslateY(y);
        computeDrawing();
        return group;
    }

    protected void computeDrawing() {
        Rectangle rec = new Rectangle(0, 0, sizeX, sizeY);
        rec.setFill(Color.WHITE);
        group.getChildren().add(rec);
    }

    public final void click(double x, double y, Frame frame) {
        if (x > this.x && x < this.x + this.sizeX &&
                y > this.y && y < this.y + this.sizeY)
            clickAction(frame);
    }

    protected void clickAction(Frame frame) {
    }
}
