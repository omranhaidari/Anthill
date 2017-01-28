package fr.cypno.anthill.map;

public abstract class Cell {

    protected static final int MOVEMENTCOST = 1;
    private double pheromonQuantity;
    private int x, y;
    private Cell previousCell;
    private double movementCost;
    private double heuristicCost;

    /**
     * Méthode retournant
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Constructeur de la classe Celle.
     * @param x
     * @param y
     */
    public Cell(int x, int y){
        this(x, y, 0);
    }

    /**
     *
     * @param x
     * @param y
     * @param pheromons
     */
    public Cell(int x, int y, double pheromons){
        this.pheromonQuantity = pheromons;
        this.x = x;
        this.y = y;
        this.movementCost = 0;
        this.heuristicCost = 0;
    }

    /**
     * Méthode retournant la quantité de phéromones présente sur la cellule.
     * @return pheromonQuantity Quantité de phéromones présente sur la cellule
     */
    public double getPheromonQuantity(){
        return pheromonQuantity;
    }

    /**
     * Méthode modifiant la quantité de phéromones présente sur la cellule.
     * @param quantity
     */
    public void setPheromons(double quantity) {
        this.pheromonQuantity = quantity;
    }

    /**
     *
     * @return
     */
    public String getChar() {
        return " ";
    }

    @Override
    public String toString() {
        return String.format("Cell: (%d,%d) %f", x, y, pheromonQuantity);
    }
    
    /**
     * Méthode réduisant de value la quantité de phéromones présente sur la
     * cellule si la cellule contient suffisamment de phéromones.
     * 
     * @param value Valeur à soustraire à la quantité de phéromones sur la 
     * cellule
     */
    public void reducePheromons(double value) {
        if(this.pheromonQuantity > value)
            this.pheromonQuantity -= value;
        else 
            this.pheromonQuantity = 0.0;
    }
    
    /**
     * Méthode retournant la cellule parente de la cellule courrante.
     * Cette méthode est utilisée pour le calcul du plus court chemin.
     * 
     * @return this.previousCell Cellule parente de la cellule courrante
     */
    public Cell getPreviousCell() {
        return this.previousCell;
    }

    /**
     * Méthode modifiant la cellule parente de la cellule courrante.
     * Cette méthode est utilisée pour le calcul du plus court chemin.
     * 
     * @param previousCell Nouvelle cellule parente de la cellule courrante
     */
    public void setPreviousCell(Cell previousCell) {
        this.previousCell = previousCell;
    }
    
    /**
     * Méthode retournant le coût de déplacement.
     * Cette méthode est utilisée pour le calcul du plus court chemin.
     * 
     * @return movementCost Coût de déplacement
     */
    public double getMovementCost() {
        return movementCost;
    }

    /**
     * Méthode retournant le coût heuristique.
     * Cette méthode est utilisée pour le calcul du plus court chemin.
     * 
     * @return heuristicCost Coût heuristique
     */
    public double getHeuristicCost() {
        return heuristicCost;
    }

    /**
     * Méthode modifiant le coût heuristique.
     * Cette méthode est utilisée pour le calcul du plus court chemin.
     * 
     * @param heuristicCost Nouvelle valeur pour le coût heuristique
     */
    public void setHeuristicCost(double heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    /**
     * Méthode effectuant une évaluation heuristique correspondant au calcul
     * de la distance euclidienne entre la cellule courrante et la cellule 
     * d'arrivée.
     * Cette méthode est utilisée pour le calcul du plus court chemin.
     * 
     * @param goal
     */
    public void computeHeuristicCost(Cell goal) {	
        
        double dx = goal.getX() - this.x;
        double dy = goal.getY() - this.y;
        double cost = (double) (Math.sqrt((dx*dx)+(dy*dy)));
        setHeuristicCost(cost);
    }
    
    /**
     * Méthode retournant le coût total (coût de déplacement jusqu'à la cellule
     * courrante + coût heuristique jusqu'à la cellule d'arrivée.
     * Cette méthode est utilisée pour le calcul du plus court chemin.
     * 
     * @return Coût total
     */
    public double getTotalCost() {
        return this.movementCost + this.heuristicCost;
    }

    /**
     * Méthode modifiant la valeur du coût de déplacement.
     * Cette méthode est utilisée pour le calcul du plus court chemin.
     * 
     * @param movementCost Nouvelle valeur du coût de déplacement
     */
    public void setMovementCost(double movementCost) {
        this.movementCost = movementCost;
    }

    /**
     * Méthode modifiant la valeur du coût de déplacement à partir de la
     * cellule parente.
     * Cette méthode est utilisée pour le calcul du plus court chemin.
     * 
     * @param previousCell Cellule parente
     */
    public void setMovementCost(Cell previousCell) {
            setMovementCost(previousCell, MOVEMENTCOST);
    }

    /**
     * Méthode modifiant la valeur du coût de déplacement à partir de la 
     * cellule parente et un coût de déplacement unitaire.
     * Cette méthode est utilisée pour le calcul du plus court chemin.
     * 
     * @param previousCell Cellule parente
     * @param basicCost Coût de déplacement unitaire
     */
    public void setMovementCost(Cell previousCell, int basicCost) {
        setMovementCost(previousCell.getMovementCost() + basicCost);
    }
    
    /**
     * Méthode calculant et retournant le coût de déplacement.
     * Cette méthode est utilisée pour le calcul du plus court chemin.
     * 
     * @param previousCell Cellule parente
     * @return Coût de déplacement
     */
    public double computeMovementCost(Cell previousCell) {
        return (previousCell.getMovementCost() + MOVEMENTCOST);
    }
}
