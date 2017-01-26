/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cypno.anthill.ant.behavior;

import fr.cypno.anthill.map.Cell;
import fr.cypno.anthill.map.Map;
import fr.cypno.anthill.map.Obstacle;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Corentin
 */
public class AStar {
    
    private List<Cell> openList;
    private List<Cell> closedList;
    private Cell start, goal;
    private Map map;
    private boolean pathFound;
    
    public AStar(Map map, Cell start, Cell goal){
        this.map = map;
        this.start = start;
        this.goal = goal; 
        this.pathFound = false;
    }
    
    public List<Cell> getShortestPath() {
        
        this.openList = new LinkedList<Cell>();
        this.closedList = new LinkedList<Cell>();
        this.openList.add(this.start); 

       
        Cell current;
        while (!pathFound) {
            current = getCellWithProbableLowestCost(); 
            this.closedList.add(current); 
            this.openList.remove(current);

            if ((current.getX() == this.goal.getX()) && (current.getY() == this.goal.getY())) { 
                return calcPath(this.start, current);
            }

            List<Cell> adjacentCells = getNeighbors(current);
            for (int i = 0; i < adjacentCells.size(); i++) {
                Cell currentAdjacentCell = adjacentCells.get(i);
                if (!openList.contains(currentAdjacentCell)) { 
                    currentAdjacentCell.setPrevious(current); // set current node as previous for this node
                    currentAdjacentCell.sethCosts(this.goal); // set h costs of this node (estimated costs to goal)
                    currentAdjacentCell.setgCosts(current); // set g costs of this node (costs from start to this node)
                    openList.add(currentAdjacentCell); // add node to openList
                } else { // node is in openList
                    if (currentAdjacentCell.getgCosts() > currentAdjacentCell.calculategCosts(current)) { // costs from current node are cheaper than previous costs
                        currentAdjacentCell.setPrevious(current); // set current node as previous for this node
                        currentAdjacentCell.setgCosts(current); // set g costs of this node (costs from start to this node)
                    }
                }
            }

            if (openList.isEmpty()) {
                return new LinkedList<Cell>(); 
            }
        }
        return null;
    }
    
    private List<Cell> calcPath() {
        
        LinkedList<Cell> path = new LinkedList<Cell>();

        Cell current = goal;
        boolean done = false;
        while (!done) {
            path.addFirst(current);
            current = (Cell) current.getPrevious();

            if (current.equals(start)) {
                done = true;
            }
        }
        return path;
    }
    
    private Cell getCellWithProbableLowestCost() {
        
        Cell candidate = openList.get(0);
        for (int i = 0; i < openList.size(); i++) {
            if (getCost(openList.get(i), this.goal) < getCost(candidate, this.goal)) {
                candidate = openList.get(i);
            }
        }
        return candidate;
    }
    
    public float getCost(Cell current, Cell goal) {	
        
            float dx = goal.getX() - current.getX();
            float dy = goal.getY() - current.getY();
            float cost = (float) (Math.sqrt((dx*dx)+(dy*dy)));

            return cost;
	}
    
    private List<Cell> getNeighbors(Cell cell) {
        
        int x = cell.getX();
        int y = cell.getY();
        List<Cell> neighbors = new LinkedList<Cell>();

        Cell neighbor;
        if (x > 0) {
            neighbor = this.map.getCell(x - 1 , y);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        if (x < this.map.getWidth()) {
            neighbor = this.map.getCell(x + 1 , y);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        if (y > 0) {
            neighbor = this.map.getCell(x , y - 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        if (y < this.map.getHeight()) {
            neighbor = this.map.getCell(x, y + 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        
        if (x < this.map.getWidth() && y < this.map.getHeight()) {
            neighbor = this.map.getCell(x + 1, y + 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        if (x > 0 && y > 0) {
            neighbor = this.map.getCell(x - 1, y - 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        if (x > 0 && y < this.map.getHeight()) {
            neighbor = this.map.getCell(x - 1, y + 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        if (x < this.map.getWidth() && y > 0) {
            neighbor = this.map.getCell(x + 1, y - 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    } 
}
