package fr.cypno.anthill.ant;

import fr.cypno.anthill.ant.behavior.Behavior;
import fr.cypno.anthill.ant.exceptions.*;
import fr.cypno.anthill.map.*;

public class Ant {
    private double foodCapacity;
    private double pheromons;
    private double food;
    private Cell position;
    private int direction;
    private Behavior behavior;

    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
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

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public Ant(double foodCapacity, double pheromons, int direction) {
        this.foodCapacity = foodCapacity;
        this.pheromons = pheromons;
        this.food = 0;
        this.direction = direction;
        this.behavior = null;
    }

    public void moveTo(Cell dest) {
        if (dest.getX() < position.getX() && dest.getY() == position.getY()) {
            direction = 0;
        }
        if (dest.getX() < position.getX() && dest.getY() > position.getY()) {
            direction = 1;
        }
        if (dest.getX() == position.getX() && dest.getY() > position.getY()) {
            direction = 2;
        }
        if (dest.getX() > position.getX() && dest.getY() > position.getY()) {
            direction = 3;
        }
        if (dest.getX() > position.getX() && dest.getY() == position.getY()) {
            direction = 4;
        }
        if (dest.getX() > position.getX() && dest.getY() < position.getY()) {
            direction = 5;
        }
        if (dest.getX() == position.getX() && dest.getY() < position.getY()) {
            direction = 6;
        }
        if (dest.getX() < position.getX() && dest.getY() < position.getY()) {
            direction = 7;
        }
        //System.out.println(position + " -> " + dest);
        position = dest;
    }

    /**
     * Méthode permettant de recuperer la nourriture sur la cellule courante La
     * fourmi prend de la nourriture jusqu'à se qu'elle n'est plus de place
     * (foodCapacity) Cette methode diminue la quantite de nourriture de la
     * cellule (en fonction de la quantite prise)
     *
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
        ((Food) position).setQuantity(cellQuantity - this.food + oldQuantity);
    }

    /**
     * Methode permettant de deposer la nourriture sur la cellule courante
     * (fourmilière)
     *
     * @throws NotAnthillCellException
     */
    public void pushFood() throws NotAnthillCellException {
        if (!(position instanceof Anthill)) {
            throw new NotAnthillCellException();
        }
        ((Anthill) position).addFood(this.food);
        this.food = 0.0;
    }

    public void dropPheromons() {
        this.position.setPheromons(this.position.getPheromonQuantity() + this.pheromons);
    }

    public void update() {
        Cell dest = this.behavior.computeDestination();
        if (dest != null && !(dest instanceof Obstacle)) {
            this.getBehavior().moveTo(dest);
        }
    }
}
