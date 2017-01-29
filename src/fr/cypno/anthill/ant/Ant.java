package fr.cypno.anthill.ant;

import fr.cypno.anthill.ant.behavior.Behavior;
import fr.cypno.anthill.ant.exceptions.*;
import fr.cypno.anthill.graphics.Positionnable;
import fr.cypno.anthill.map.*;

/**
 * Ant est la classe représentant une fourmi
 */

public class Ant implements Positionnable {
    // Quantité de nourriture que la fourmi peut transporter
    private double foodCapacity;
    // Quantité de phéromones relâchée par la fourmi sur chaque cellule
    private double pheromons;
    // Quantité de nourriture transportée par la fourmi
    private double food;
    // Cellule sur laquelle se situe la fourmi
    private Cell position;
    // Direction de la fourmi
    private int direction;
    // Comportement de la fourmi
    private Behavior behavior;
    // Matrice de cellules
    private Map map;

    

    /**
     * Constructeur de la classe Ant à partir d'une matrice de cellules, une 
     * capacié de nourriture, une quantité de phéromones relâchée par la fourmi 
     * sur chaque cellule et une direction. 
     * 
     * @param map Matrice de cellules
     * @param foodCapacity  Quantité de nourriture que la fourmi peut transporter
     * @param pheromons Quantité de phéromones relâchée par la fourmi sur chaque cellule
     * @param direction Direction de la fourmi
     */
    public Ant(Map map, double foodCapacity, double pheromons, int direction) {
        this.map = map;
        this.foodCapacity = foodCapacity;
        this.pheromons = pheromons;
        this.food = 0;
        this.direction = direction;
        this.behavior = null;
    }

    /**
     * Méthode permettant de déplacer la fourmi sur une des 8 cases adjacentes
     * 
     * @param dest Destination de la fourmi
     */
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
     * Methode permettant de déposer la nourriture sur la cellule courante
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

    /**
     * Méthode permettant à la fourmi de relâcher des phéromones sur la cellule
     * courrante.
     */
    public void dropPheromons() {
        this.position.setPheromons(this.position.getPheromonQuantity() + this.pheromons);
    }

    /**
     * Méthode permettant de déplacer la fourmi en vérifiant que la destination
     * est une cellule praticable.
     */
    public void update() {
        Cell dest = this.behavior.computeDestination(map);
        if (dest != null && !(dest instanceof Obstacle)) {
            this.getBehavior().moveTo(dest);
        }
    }
    
    /**
     * Méthode retournant le comportement de la fourmi.
     * 
     * @return Comportement de la fourmi
     */
    public Behavior getBehavior() {
        return behavior;
    }

    /**
     * Méthode modifiant le comportement de la fourmi.
     * 
     * @param behavior Nouveau omportement de la fourmi
     */
    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    /**
     * Méthode retournant la direction de la fourmi.
     * 
     * @return direction de la fourmi
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Méthode modifiant la direction de la fourmi.
     * 
     * @param direction nouvelle direction de la fourmi
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Méthode retournant la cellule sur laquelle se trouve la fourmi.
     * 
     * @return cellule sur laquelle se trouve la fourmi
     */
    public Cell getPosition() {
        return position;
    }

    /**
     * Méthode modifiant la cellule sur laquelle se trouve la fourmi.
     * 
     * @param position Nouvelle cellule sur laquelle se trouve la fourmi.
     */
    public void setPosition(Cell position) {
        this.position = position;
    }

    /**
     * Méthode retournant la quantité de nourriture que la fourmi peut transporter.
     * 
     * @return quantité de nourriture que la fourmi peut transporter
     */
    public double getFoodCapacity() {
        return foodCapacity;
    }

    /**
     * Méthode modifiant la quantité de nourriture que la fourmi peut transporter.
     * 
     * @param foodCapacity nouvelle valeur pour la quantité de nourriture que la
     * fourmi peut transporter
     */
    public void setFoodCapacity(double foodCapacity) {
        this.foodCapacity = foodCapacity;
    }

    /**
     * Methode retournant la quantité de phéromones relâchée par la fourmi sur
     * chaque cellule visitée.
     * 
     * @return quantité de phéromones relâchée par la fourmi sur chaque cellule
     * visitée
     */
    public double getPheromons() {
        return pheromons;
    }

    /**
     * Methode modifiant la quantité de phéromones relâchée par la fourmi sur
     * chaque cellule visitée.
     * 
     * @return quantité de phéromones relâchée par la fourmi sur chaque cellule
     * visitée
     */
    public void setPheromons(double pheromons) {
        this.pheromons = pheromons;
    }

    /**
     * Méthode retournant la quantité de nourriture transportée par la fourmi.
     * 
     * @return quantité de nourriture transportée par la fourmi
     */
    public double getFood() {
        return food;
    }

    /**
     * Méthode modifiant la quantité de nourriture transportée par la fourmi.
     * 
     * @return Nouvelle quantité de nourriture transportée par la fourmi
     */
    public void setFood(double food) {
        this.food = food;
    }

    /**
     * Méthode renvoyant le numéro de la ligne sur laquelle se situe la fourmi.
     * 
     * @return numéro de la ligne sur laquelle se situe la fourmi
     */
    @Override
    public int getX() {
        return position.getX();
    }

    /**
     * Méthode renvoyant le numéro de la colonne sur laquelle se situe la fourmi.
     * 
     * @return numéro de la colonne sur laquelle se situe la fourmi
     */
    @Override
    public int getY() {
        return position.getY();
    }
}
