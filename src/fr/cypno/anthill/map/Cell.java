package fr.cypno.anthill.map;


public abstract class Cell {
    private double pheromonQuantity;
    private int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cell(int x, int y){
        this(x, y, 0);
    }

    public Cell(int x, int y, double pheromons){
        this.pheromonQuantity = pheromons;
        this.x = x;
        this.y = y;
    }

    public double getPheromonQuantity(){
        return pheromonQuantity;
    }

    public void setPheromons(double quantity) {
        this.pheromonQuantity = quantity;
    }

    public String getChar() {
        return " ";
    }

    @Override
    public String toString() {
        return String.format("Cell: (%d,%d) %f", x, y, pheromonQuantity);
    }
    
    public void reducePheromons(double value) {
        if(this.pheromonQuantity > value)
            this.pheromonQuantity -= value;
        else 
            this.pheromonQuantity = 0.0;
    }
}
