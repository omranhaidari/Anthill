package fr.cypno.anthill.ant;

import fr.cypno.anthill.ant.exceptions.NotAnthillCellException;
import fr.cypno.anthill.ant.exceptions.NotFoodCellException;
import fr.cypno.anthill.map.*;
import java.util.*;

public class Ant {

    private double foodCapacity;
    private double pheromons;
    private List<Cell> cells;
    private double food;
    private Cell position;

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

    public void pullFood() throws NotFoodCellException {
        if (!(position instanceof Food)) {
            throw new NotFoodCellException();
        }
        double cellQuantity = ((Food) position).getQuantity();
        double oldQuantity = this.food;
        double currentCapacity = this.foodCapacity - oldQuantity;
        if (cellQuantity + oldQuantity >= currentCapacity) {
            this.food = this.foodCapacity;
        } else {
            this.food = cellQuantity;
        }
        ((Food) position).setQuantity(this.food - oldQuantity);
    }

    public void pushFood() throws NotAnthillCellException {
        if (!(position instanceof Anthill)) {
            throw new NotAnthillCellException();
        }
        ((Anthill) position).addFood(this.food);
        this.food = 0.0;
    }
}
