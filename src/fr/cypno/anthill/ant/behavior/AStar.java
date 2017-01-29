package fr.cypno.anthill.ant.behavior;

import fr.cypno.anthill.map.Cell;
import fr.cypno.anthill.map.Map;
import fr.cypno.anthill.map.Obstacle;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe qui permet de rechercher un plus court chemin entre un point de départ
 * et un point d'arrivée. 
 * La recherche du plus court chemin se base sur l'algorithme A*
 */
public class AStar {
    
    /**
     * Méthode de recherche du plus court chemin entre la cellule de départ et 
     * la cellule d'arrivée sur la matrice de cellules courrante.
     * 
     * Cette méthode utilise l'algorithme A*. Elle retourne une LinkedList 
     * contenant le les cellules qui constituent le plus court chemin du point
     * de départ au point d'arrivée.
     * 
     * Si la cellule d'arrivée est un obstacle ou que'elle est inaccessible, il 
     * n'existe pas de chemin et une LinkedList vide est retournée.
     * 
     * Cette méthode prend en compte la taille et la hauteur de la carte.
     * 
     * @param map Matrice de cellules
     * @param start Cellule de départ
     * @param goal Cellule d'arrivée
     * @return LinkedList contenant le plus court chemin s'il existe, null sinon
     */
    public static List<Cell> getShortestPath(Map map, Cell start, Cell goal) {
        // La cellule d'arrivée est un obstacle, il n'y a donc aucun chemin possible
        if(goal instanceof Obstacle)
            return null;
        // Liste contenant les cellules non visitées mais adjacentes aux cellules déjà visitées
        List<Cell> openList = new LinkedList<Cell>();
        // Liste contenant les cellules déjà visitées et traitées
        List<Cell> closedList = new LinkedList<Cell>();
        openList.add(start); 

       
        Cell current;
        while (!openList.isEmpty()) {
            // Récupération de la cellule contenue dans l'open list avec le coût le plus faible
            current = getCellWithLowestCost(openList);
            // Cellule d'arrivée trouvée
            if ((current.getX() == goal.getX()) && (current.getY() == goal.getY())) { 
                return calcPath(start, goal);
            }
            closedList.add(current); 
            openList.remove(current);
            
            // Récupération des cellules voisines incluant les cellules en diagonale
            List<Cell> adjacentCells = getNeighbors(map, current, closedList);
            // Traitement effectué pour toutes les cellules adjacentes
            for (int i = 0; i < adjacentCells.size(); i++) {
                Cell currentAdjacentCell = adjacentCells.get(i);
                if (!openList.contains(currentAdjacentCell)) { 
                    // La cellule courrante devient le parent de la cellule adjacente courrante
                    currentAdjacentCell.setPreviousCell(current); 
                    // Calcul du coût heuristique (distance euclidienne dans notre cas) de la cellule adjacente à la celle d'arrivée
                    currentAdjacentCell.computeHeuristicCost(goal); 
                    // Calcul du coût de déplacement de la cellule de départ à la cellule courrante
                    currentAdjacentCell.setMovementCost(current); 
                    openList.add(currentAdjacentCell); 
                } else { 
                    // Le coût de déplacement jusqu'à la cellule courrante est inférieur au coût précédent 
                    if (currentAdjacentCell.getMovementCost() > currentAdjacentCell.computeMovementCost(current)) { 
                        // La cellule courrante devient le parent de la cellule adjacente courrante
                        currentAdjacentCell.setPreviousCell(current); 
                        // Calcul du coût de déplacement de la cellule de départ à la cellule courrante
                        currentAdjacentCell.setMovementCost(current); 
                    }
                }
            }
        }
        // Aucun chemin n'existe
        return null;
    }
    /**
     * Méthode calculant le plus court chemin entre une cellule de départ et 
     * d'arrivée en remontant de la cellule d'arrivée jusqu'à la cellule de 
     * départ grâce aux cellules parentes définies dans la méthode getShortestPath()
     * 
     * @param start Cellule de départ
     * @param goal Cellule d'arrivée
     * @return LinkedList contenant le plus court chemin s'il existe, null sinon
     */
    private static List<Cell> calcPath(Cell start, Cell goal) {
        
        LinkedList<Cell> path = new LinkedList<Cell>();

        Cell current = goal;
        boolean done = false;
        while (!done) {
            path.addFirst(current);
            current = (Cell) current.getPreviousCell();

            if (current.equals(start)) {
                done = true;
            }
        }
        return path;
    }
    
    /**
     * Méthode retournant la cellule de l'open list avec le coût le plus faible
     * (coût de déplacement + coût heuristique)
     *
     * @param openList Liste contenant les cellules non visitées mais adjacentes aux cellules déjà visitées
     * @return Cell cellule de l'open list avec le coût le plus faible
     */
    private static Cell getCellWithLowestCost(List<Cell> openList) {
        
        Cell candidate = openList.get(0);
        for (int i = 1; i < openList.size(); i++) {
            if (openList.get(i).getTotalCost() < candidate.getTotalCost()) {
                candidate = openList.get(i);
            }
        }
        return candidate;
    }
    
    /**
     * Méthode retournant une liste de cellules adjacentes à la cellule 
     * courrante et praticables.
     *
     * @param map Matrice de cellules
     * @param cell Cellule courrante
     * @param closedList Liste contenant les cellules déjà visitées et traitées
     * @return LinkedList contenant le plus court chemin s'il existe, null sinon
     */
    private static List<Cell> getNeighbors(Map map, Cell cell, List<Cell> closedList) {
        
        int l = cell.getX();
        int c = cell.getY();
        List<Cell> neighbors = new LinkedList<Cell>();

        Cell neighbor;
        if (l > 0) {
            neighbor = map.getCell(l - 1 , c);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        if (l < map.getHeight() - 1) {
            neighbor = map.getCell(l + 1 , c);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        if (c > 0) {
            neighbor = map.getCell(l , c - 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        if (c < map.getWidth() - 1) {
            neighbor = map.getCell(l, c + 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        
        if (l < map.getHeight() - 1 && c < map.getWidth() - 1) {
            neighbor = map.getCell(l + 1, c + 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        if (l > 0 && c > 0) {
            neighbor = map.getCell(l - 1, c - 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        if (l > 0 && c < map.getWidth() - 1) {
            neighbor = map.getCell(l - 1, c + 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        if (l < map.getHeight() - 1 && c > 0) {
            neighbor = map.getCell(l + 1, c - 1);
            if (!(neighbor instanceof Obstacle) && !closedList.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    } 
}
