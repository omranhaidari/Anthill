package fr.cypno.anthill.map;

public class Food extends Cell {

    private double quantity;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Food(int x, int y) {
        this(x, y, 0, 0);
    }

    public Food(int x, int y, double pheromons, double quantity) {
        super(x, y, 0);
        this.quantity = quantity;
    }

    public boolean isEmpty() {
        boolean flag = false;
        if (getQuantity() == 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public String getChar() {
        return "\u001B[31mo\u001B[0m";
    }
}
