package fr.cypno.anthill.ant;

import fr.cypno.anthill.ant.exceptions.*;
import fr.cypno.anthill.map.*;
import java.util.*;

public class Ant {

    private double foodCapacity;
    private double pheromons;
    private List<Cell> cells;
    private double food;
    private Cell position;
    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    
    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

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

    public Ant(double foodCapacity, double pheromons, Direction d) {
        this.foodCapacity = foodCapacity;
        this.pheromons = pheromons;
        this.cells = new ArrayList<Cell>();
        this.food = 0.0;
        this.direction = d;
    }
    
    /**
     * Methode permettant de recuperer la nourriture sur la cellule courante
     * La fourmi prend de la nourriture jusqu'à se qu'elle n'est plus de place (foodCapacity)
     * Cette methode diminue la quantite de nourriture de la cellule (en fonction de la quantite prise)
     * @throws NotFoodCellException 
     */
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
            this.food += cellQuantity;
        }
        ((Food)position).setQuantity(cellQuantity - this.food + oldQuantity);
    }

    /**
     * Methode permettant de deposer la nourriture sur la cellule courante (fourmilière)
     * @throws NotAnthillCellException 
     */
    public void pushFood() throws NotAnthillCellException {
        if (!(position instanceof Anthill)) {
            throw new NotAnthillCellException();
        }
        ((Anthill) position).addFood(this.food);
        this.food = 0.0;
    }
}
