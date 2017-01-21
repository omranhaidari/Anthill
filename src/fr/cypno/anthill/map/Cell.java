package fr.cypno.anthill.map;

public abstract class Cell {
    private double pheromons;
    final double PHEROMONS_REMOVED = 1; //Pheromons in less every 
    
    public Cell(){
        this.pheromons = 0;
    }    
    
    public Cell(double pheromons){
        this.pheromons = pheromons;
    }
    
    public Cell() {
        this.pheromons = 0;
    }
    
    public double quantityPheromons(){
        return pheromons;
    }
    public void reducePheronomons(){
        pheromons -= PHEROMONS_REMOVED;
        if(pheromons < 0)
            pheromons = 0;
    }
    
    public void setPheromons(double quantity) {
        this.pheromons = quantity;
    }

    @Override
    public String toString() {
        String cellType = "-1";
        if(this instanceof Empty)
            cellType = " ";
        if(this instanceof Food)
            cellType = "o";
        if(this instanceof Anthill)
            cellType = "x";
        if(this instanceof Obstacle)
            cellType = "#";
        return cellType;    
    }
}
