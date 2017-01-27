package fr.cypno.anthill.map;

public abstract class Cell {
    protected static final int MOVEMENTCOST = 1;
    private double pheromonQuantity;
    private int x, y;
    private Cell previousCell;
    private double movementCost;
    private double heuristicCost;

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
        this.movementCost = 0;
        this.heuristicCost = 0;
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
    
    /**
     * Returns the node set as previous node on the current path (used for A* algorithm)
     */
    public Cell getPreviousCell() {
        return this.previousCell;
    }

    /**
     * @param previousCell the cell to set as previous node on the current path (used for A* algorithm)
     */
    public void setPreviousCell(Cell previousCell) {
        this.previousCell = previousCell;
    }
    
    public double getMovementCost() {
        return movementCost;
    }

    public double getHeuristicCost() {
        return heuristicCost;
    }

    public void setHeuristicCost(double heuristicCost) {
        this.heuristicCost = heuristicCost;
    }
    
    // distance euclidienne
    public void computeHeuristicCost(Cell goal) {	
        
        double dx = goal.getX() - this.x;
        double dy = goal.getY() - this.y;
        double cost = (double) (Math.sqrt((dx*dx)+(dy*dy)));
        setHeuristicCost(cost);
    }
    
    public double getTotalCost() {
        return this.movementCost + this.heuristicCost;
    }

    public void setMovementCost(double movementCost) {
        this.movementCost = movementCost;
    }
    public void setMovementCost(Cell previousCell) {
            setMovementCost(previousCell, MOVEMENTCOST);
    }

    public void setMovementCost(Cell previousCell, int basicCost) {
        setMovementCost(previousCell.getMovementCost() + basicCost);
    }
    
    public double computeMovementCost(Cell previousCell) {
        return (previousCell.getMovementCost() + MOVEMENTCOST);
    }
}
