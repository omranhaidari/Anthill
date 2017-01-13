package fr.cypno.anthill.map;

public class Anthill extends Cell{
    
    private double quantity;
    
    public Anthill(){
        this.quantity = 0;
    }
    
    public Anthill(double quantity){
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    
    public void addFood(double quantity) {
        setQuantity(getQuantity() + quantity);
    }
}
