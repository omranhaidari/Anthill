/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cypno.anthill.graphics.tiles;

import fr.cypno.anthill.map.Food;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
        Rectangle rec = new Rectangle(
                food.getY() * cellSize + food.getY(),
                food.getX() * cellSize + food.getX(),
                cellSize, cellSize);
        rec.setFill(Color.BLUE);
        return rec;
    }
}