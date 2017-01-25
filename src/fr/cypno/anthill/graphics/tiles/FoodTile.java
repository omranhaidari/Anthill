/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.map.Food;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author captaincat
 */
public class FoodTile implements Tile{
    private Food food;

    public FoodTile(Food food) {
        this.food = food;
    }

    @Override
    public Node draw(int cellSize) {
        Group group = new Group();
        group.setTranslateX(food.getY() * cellSize + food.getY());
        group.setTranslateY(food.getX() * cellSize + food.getX());
        Rectangle rec = new Rectangle(0, 0, cellSize, cellSize);
        rec.setFill(Color.BLUE);
        Text text = new Text(0, cellSize / 2, "" + food.getQuantity());
        text.setFont(new Font(10));
        text.setFill(Color.BLACK);
        group.getChildren().add(rec);
        group.getChildren().add(text);
        return group;
    }
}