package fr.cypno.anthill.ant;


import ant.Anthill;
import ant.Cell;
import ant.Food;
import java.util.*;

public class Ant {

    private double foodCapacity;
    private double pheromons;
    private List<Cell> cells;
    private double food;

    public double getFoodCapacity() {
        return foodCapacity;
    }

    public void setFoodCapacity(double foodCapacity) {
        this.foodCapacity = foodCapacity;
    }

    public double getPheromons() {
        return pheromons;
    }

    public void setPheromons(double pheromons) {
        this.pheromons = pheromons;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public Ant() {
        this.cells = new ArrayList<Cell>();
        this.food = 0.0;
    }

    public Ant(double foodCapacity, double pheromons) {
        this.foodCapacity = foodCapacity;
        this.pheromons = pheromons;
        this.cells = new ArrayList<Cell>();
        this.food = 0.0;
    }

    public void pullFood() {
        Cell cell = new Cell();
        Food cellFood = null;
        int i = 0;
        while (!(cell instanceof Food) || i > cells.size()) {
            cell = this.cells.get(i);
            i++;
        }
        cellFood = (Food) cell;
        double foodQuantity = cellFood.getQuantity();
        if (foodQuantity >= this.foodCapacity) {
            this.food = this.foodCapacity;
        }
        else {
            this.food = foodQuantity;
        }
        cellFood.setQuantity(foodQuantity - this.food);
    }
    
    public void pushFood() {
        Cell cell = new Cell();
        Anthill anthill = null;
        int i = 0;
        while (!(cell instanceof Anthill) || i > cells.size()) {
            cell = this.cells.get(i);
            i++;
        }
        anthill = (Anthill) cell;
        anthill.addFood(this.food);
        this.food = 0.0;
    }
}
