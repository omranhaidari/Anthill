package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.map.Food;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FoodTile extends Tile{

    /**
     *
     * @param food
     * @param cellSize
     */
    public FoodTile(Food food, int cellSize) {
        super(food, cellSize);
    }

    /**
     *
     */
    @Override
    protected void computeDrawing() {
        Rectangle rec = new Rectangle(0, 0, cellSize, cellSize);
        rec.setFill(Color.BLUE);
        Text text = new Text(0, cellSize / 2, "" + ((Food) positionnable).getQuantity());
        text.setFont(new Font(10));
        text.setFill(Color.BLACK);
        group.getChildren().add(rec);
        group.getChildren().add(text);
    }
}