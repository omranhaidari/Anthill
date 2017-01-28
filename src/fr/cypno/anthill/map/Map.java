package fr.cypno.anthill.map;

import fr.cypno.anthill.map.exceptions.InvalidMapFile;
import fr.cypno.anthill.ant.Ant;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {

    private int height;
    private int width;
    private int numberOfSources;
    private int[] quantityPerSource;
    private Cell[][] map;
    private Anthill anthill;
    public int cellNb;

    /**
     * Constructeur de la classe Map.
     * 
     * @param filePath Chemin du fichier de la carte
     * @throws InvalidMapFile
     * @throws FileNotFoundException
     */
    public Map(String filePath) throws InvalidMapFile, FileNotFoundException {
        buildMap(filePath);
    }

    /**
     * Méthode permettant de construire la matrice de cellules à partir du fichier
     * dont le chemin est filePath.
     * 
     * @param filePath Chemin du fichier de la carte
     * @throws InvalidMapFile
     * @throws FileNotFoundException
     */
    public void buildMap(String filePath) throws InvalidMapFile, FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        if (scanner.hasNextInt()) {
            this.height = scanner.nextInt();
        } else {
            throw new InvalidMapFile("Incorrect number of rows.");
        }
        if (scanner.hasNextInt()) {
            this.width = scanner.nextInt();
        } else {
            throw new InvalidMapFile("Incorrect number of columns.");
        }
        if (scanner.hasNextInt()) {
            this.numberOfSources = scanner.nextInt();
        } else {
            throw new InvalidMapFile("Incorrect number of sources.");
        }
        this.quantityPerSource = new int[this.numberOfSources];
        for (int i = 0; i < this.numberOfSources; i++) {
            if (scanner.hasNextInt()) {
                this.quantityPerSource[i] = scanner.nextInt();
            } else {
                throw new InvalidMapFile("Invalid quantity in source " + (i + 1) + ".");
            }
        }
        scanner.nextLine(); // Vide la ligne
        this.map = new Cell[this.height][this.width];
        char value;
        int sourceCount = 0;
        int anthillCount = 0;
        for (int i = 0; i < this.height; i++) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int j = 0; j < this.width; j++) {

                    if (line.length() > j) {
                        value = line.charAt(j);
                    } else {
                        throw new InvalidMapFile("Line " + (i + 1) + " : length (" + line.length() + ") differs  from expected length (" + this.width + ").");
                    }

                    switch (value) {
                        case ' ':
                            this.map[i][j] = new Empty(i, j);
                            break;
                        case 'o':
                            if (sourceCount < this.numberOfSources) {
                                this.map[i][j] = new Food(i, j, 0, this.quantityPerSource[sourceCount++]);
                            } else {
                                throw new InvalidMapFile("Too many sources.");
                            }
                            break;
                        case 'x':
                            this.anthill = new Anthill(i, j);
                            this.map[i][j] = this.anthill;
                            if (++anthillCount > 1) {
                                throw new InvalidMapFile("Too many anthills.");
                            }
                            break;
                        case '#':
                            this.map[i][j] = new Obstacle(i, j);
                            break;
                        default:
                            throw new InvalidMapFile("Invalid element in the matrix : [" + i + "," + j + "] : " + value + ".");
                    }
                }
            } else {
                throw new InvalidMapFile("Not enough rows (" + i + "), expected : " + this.height + ".");
            }
        }
        scanner.close();
        if (sourceCount == 0) {
            throw new InvalidMapFile("No source found.");
        } else if (sourceCount != this.numberOfSources) {
            throw new InvalidMapFile("Number of sources doesn't match : expected " + this.numberOfSources + ", found " + sourceCount + ".");
        }
        if (!this.isClosed()) {
            throw new InvalidMapFile("Unclosed map.");
        }
        if (anthillCount == 0) {
            throw new InvalidMapFile("No anthill found.");
        }
    }

    @Override
    public String toString() {
        String s = "";
        s += "cols: " + this.width + "\n";
        s += "rows: " + this.height + "\n";
        s += "sources: " + this.numberOfSources + "\n";
        s += Map.printCellMatrix(this.map);
        return s;
    }

    /**
     * Méthode retournant une chaîne de caratères correspondant à l'affichage de 
     * la carte à partir d'une matrice de cellules.
     * 
     * @param matrix Matrice de cellules
     * @return Chaîne de caratères correspondant à l'affichage de la carte à 
     * partir d'une matrice de cellules
     */
    public static String printCellMatrix(Cell[][] matrix) {
        return printCellMatrix(matrix, null);
    }

    /**
     * Méthode retournant une chaîne de caratères correspondant à l'affichage de 
     * la carte à partir d'une liste de fourmis.
     * 
     * @param ants Liste de fourmis
     * @return chaîne de caratères correspondant à l'affichage de la carte à 
     * partir d'une liste de fourmis
     */
    public String printCellMatrix(ArrayList<Ant> ants) {
        return printCellMatrix(this.map, ants);
    }

    /**
     * Méthode retournant une chaîne de caratères correspondant à l'affichage de 
     * la carte à partir d'une matrice de cellules et une liste de fourmis.
     * 
     * @param matrix Matrice de cellules
     * @param ants Liste de fourmis
     * @return chaîne de caratères correspondant à l'affichage de la carte à 
     * partir d'une matrice de cellules et une liste de fourmis
     */
    public static String printCellMatrix(Cell[][] matrix, ArrayList<Ant> ants) {
        String s = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int nb = 0;
                if (ants != null)
                    for (Ant a : ants)
                        if (a.getPosition().getX() == i && a.getPosition().getY() == j)
                            nb++;
                if (nb != 0)
                    s += "\u001B[31m" + nb + "\u001B[0m";
                else
                    s += matrix[i][j].getChar();
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Méthode retournant une sous-matrice située entre les cellules (l1,c1)
     * et (l2,c2).
     * 
     * @param l1 Ligne de la première cellule
     * @param c1 Colonne de la première cellule
     * @param l2 Ligne de la deuxième cellule
     * @param c2 Colonne de la deuxième cellule
     * @return matric sous-matrice située entre les cellules (l1,c1)
     * et (l2,c2)
     */
    public Cell[][] getMatrix(int l1, int c1, int l2, int c2) {
        Cell[][] matrix = new Cell[l2 - l1 + 1][];
        for (int l = l1; l <= l2; l++) {
            matrix[l - l1] = new Cell[c2 - c1 + 1];
            for (int c = c1; c <= c2; c++) {
                if (l < 0 || l >= map.length || c < 0 || c >= map[l].length)
                    matrix[l - l1][c - c1] = null;
                else
                    matrix[l - l1][c - c1] = map[l][c];
            }
        }
        return matrix;
    }

    /**
     *  Méthode qui vérifie que la carte est bien fermée par des obstacles.
     * 
     * @return true si la carte est fermée, false sinon
     */
    private boolean isClosed() {
        for (int i = 0; i < this.height; i++)
            if (!(map[i][0] instanceof Obstacle && map[i][this.width - 1] instanceof Obstacle))
                return false;
        for (int i = 0; i < this.width; i++)
            if (!(map[0][i] instanceof Obstacle && map[this.height - 1][i] instanceof Obstacle))
                return false;
        return true;
    }
    
    /**
     * Méthode retournant la quantité totale de nourriture présente sur la
     * carte.
     * 
     * @param map
     * @return
     */
    public double foodMapQuantity(Cell[][] map) {
        double mapFoodQuantity = 0.0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] instanceof Food) {
                    mapFoodQuantity += ((Food)map[i][j]).getQuantity();
                }
            }
        }
        return mapFoodQuantity;
    }
    
    
    /**
     * Methode retournant la hauteur de la matrice de cellules.
     * 
     * @return height Hauteur de la matrice de cellules
     */
    public int getHeight() {
        return height;
    }

    /**
     * Methode modifiant la hauteur de la matrice de cellules.
     * 
     * @param height Nouvelle hauteur de la matrice de cellules
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Methode retournant la largeur de la matrice de cellules.
     * 
     * @return  width Largeur de la matrice de cellules
     */
    public int getWidth() {
        return width;
    }

    /**
     * Methode modifiant la largeur de la matrice de cellules.
     * 
     * @param width Nouvelle largeur de la matrice de cellules
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Methode retournant le nombre de sources contenues dans la matrice de 
     * cellules.
     * 
     * @return numberOfSources Nombre de sources contenues dans la matrice de 
     * cellules
     */
    public int getNumberOfSources() {
        return numberOfSources;
    }

    /**
     * Methode modifiant le nombre de sources contenues dans la matrice de 
     * cellules.
     * 
     * @param numberOfSources Nouveau nombre de sources contenues dans la
     * matrice de cellules
     */
    public void setNumberOfSources(int numberOfSources) {
        this.numberOfSources = numberOfSources;
    }

    /**
     * Methode retournant un tableau contenant les quantités de nourriture des
     * sources.
     * 
     * @return quantityPerSource Tableau contenant les quantités de nourriture
     * des sources
     */
    public int[] getQuantityPerSource() {
        return quantityPerSource;
    }

    /**
     * Methode modifiant un tableau contenant les quantités de nourriture des 
     * sources.
     * 
     * @param quantityPerSource Tableau contenant les nouvelles quantités de
     * nourriture des sources
     */
    public void setQuantityPerSource(int[] quantityPerSource) {
        this.quantityPerSource = quantityPerSource;
    }

    /**
     * Méthode retournant le nombre de cellules contenues dans la matrice.
     * 
     * @return cellNb Nombre de cellules contenues dans la matrice
     */
    public int getCellNb() {
        return cellNb;
    }

    /**
     * Méthode modifiant le nombre de cellules contenues dans la matrice.
     * 
     * @param cellNb Nouveau nombre de cellules contenues dans la matrice
     */
    public void setCellNb(int cellNb) {
        this.cellNb = cellNb;
    }
    
    /**
     * Méthode retournant la matrice de cellules.
     * 
     * @return map Matrice de cellules
     */
    public Cell[][] getMap() {
        return map;
    }
    
    /**
     * Méthode retournant la fourmilière contenue dans la matrice de cellules.
     * 
     * @return anthill Fourmilière contenue dans la matrice de cellules
     */
    public Anthill getAnthill() {
        return anthill;
    }
    
    /**
     * Méthode retournant la cellule située sur la ligne l et la colonne c de
     * la matrice de cellules
     * 
     * @param l Ligne de la cellule
     * @param c Colonne de la cellule
     * @return cellule située sur la ligne l et la colonne c de la matrice de
     * cellules
     */
    public Cell getCell(int l, int c){
        return this.map[l][c];
    }
}
