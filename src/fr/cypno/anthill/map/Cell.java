package fr.cypno.anthill.map;

public abstract class Cell {
    private double pheromons;
    final double PHEROMONS_REMOVED = 1;
    private int x, y;
    
    public Cell(int x, int y){
        this(x, y, 0);
    }    
    
    public Cell(int x, int y, double pheromons){
        this.pheromons = pheromons;
        this.x = x;
        this.y = y;
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

    public String getChar() {
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

    @Override
    public String toString() {
        return String.format("Cell: (%d,%d) %f", x, y, pheromons);
    }
}
