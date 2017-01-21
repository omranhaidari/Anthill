package fr.cypno.anthill.map;

public class Anthill extends Cell{
    
    private double foodQuantity;
    
    public Anthill() {
        
    }
    
    public Anthill(){
        this.quantity = 0;
    }
    
    public Anthill(double quantity){
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
}
