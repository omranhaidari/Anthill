package fr.cypno.anthill.map;

public class Anthill extends Cell{
    
    private double foodQuantity;
    
    public Anthill(int x, int y) {
        this(x, y, 0);
    }
    
    public Anthill(int x, int y, double quantity){
        super(x ,y);
        this.foodQuantity = quantity;
    }

    public double getQuantity() {
        return foodQuantity;
    }

    public void setQuantity(double quantity) {
        this.foodQuantity = quantity;
    }
    
    public void addFood(double quantity) {
        setQuantity(getQuantity() + quantity);
    }

    @Override
    public String getChar() {
        return "\u001B[34mx\u001B[0m";
    }
    
    public boolean isFull(double mapQuantity) {
        if(mapQuantity == this.foodQuantity)
            return true;
        else
            return false;
    }
}
