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
        
        if(this.goal instanceof Obstacle)
            return null;
        this.openList = new LinkedList<Cell>();
        this.closedList = new LinkedList<Cell>();
        this.openList.add(this.start); 

       
        Cell current;
        int j = 0;
        while (!openList.isEmpty()) {
            current = getCellWithLowestCost();
            System.out.println();
            System.out.print("Open : ");
            for (int i = 0; i < this.openList.size(); i++) {
            System.out.print("(" + this.openList.get(i).getX() + ", " + this.openList.get(i).getY() + ") cost : " + this.openList.get(i).getTotalCost() + " h : " + + this.openList.get(i).getHeuristicCost());
            }
            /*System.out.print("Close : ");
            for (int i = 0; i < this.closedList.size(); i++) {
            System.out.print("(" + this.closedList.get(i).getX() + ", " + this.closedList.get(i).getY() + ")");
            }*/
            System.out.println();
            System.out.println("=(" + current.getX() + "," + current.getY() + ")");
            if ((current.getX() == this.goal.getX()) && (current.getY() == this.goal.getY())) { 
                return calcPath();
            }
            this.closedList.add(current); 
            this.openList.remove(current);

            List<Cell> adjacentCells = getNeighbors(current);
            for (int i = 0; i < adjacentCells.size(); i++) {
                Cell currentAdjacentCell = adjacentCells.get(i);
                if (!openList.contains(currentAdjacentCell)) { 
                    currentAdjacentCell.setPreviousCell(current); 
                    currentAdjacentCell.computeHeuristicCost(this.goal); 
                    currentAdjacentCell.setMovementCost(current); 
                    openList.add(currentAdjacentCell); 
                } else { 
                    if (currentAdjacentCell.getMovementCost() > currentAdjacentCell.computeMovementCost(current)) { 
                        currentAdjacentCell.setPreviousCell(current); 
                        currentAdjacentCell.setMovementCost(current); 
                    }
                }
            }
            j++;
        }
        return null;
    }
    
    private List<Cell> calcPath() {
        
        LinkedList<Cell> path = new LinkedList<Cell>();

        Cell current = this.goal;
        boolean done = false;
        while (!done) {
            path.addFirst(current);
            current = (Cell) current.getPreviousCell();

            if (current.equals(this.start)) {
                done = true;
            }
        }
        return path;
    }
    
    private Cell getCellWithLowestCost() {
        
        Cell candidate = openList.get(0);
        for (int i = 1; i < openList.size(); i++) {
            if (openList.get(i).getTotalCost() < candidate.getTotalCost()) {
                candidate = openList.get(i);
            }
        }
        return candidate;
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

        if (x < this.map.getWidth() - 1) {
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

        if (y < this.map.getHeight() - 1) {
            neighbor = this.map.getCell(x, y + 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        
        if (x < this.map.getWidth() - 1 && y < this.map.getHeight() - 1) {
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

        if (x > 0 && y < this.map.getHeight() - 1) {
            neighbor = this.map.getCell(x - 1, y + 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        if (x < this.map.getWidth() - 1 && y > 0) {
            neighbor = this.map.getCell(x + 1, y - 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        
        for (int i = 0; i < neighbors.size(); i++) {
            System.out.print("(" + neighbors.get(i).getX() + ", " + neighbors.get(i).getY() + ") - ");
        } 
        
        return neighbors;
    } 
}
