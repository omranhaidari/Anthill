package fr.cypno.anthill.map;

public class Food extends Cell {

    private double quantity;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Food() {
    }

    public Food(double quantity) {
        this.quantity = quantity;
    }

    public boolean isEmpty() {
        boolean flag = false;
        if (getQuantity() == 0) {
            flag = true;
        }
        return flag;
    }
}
