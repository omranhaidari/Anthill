package fr.cypno.anthill.map;

public abstract class Cell {
    private double pheromons;
    final double PHEROMONS_REMOVED = 1; //Pheromons in less every 
    
    public Cell(double pheromons){
        this.pheromons = pheromons;
    }
    
    public double quantityPheromons(){
        return pheromons;
    }
    public void reducePheronomons(){
        pheromons -= PHEROMONS_REMOVED;
        if(pheromons < 0)
            pheromons = 0;
    }
}
