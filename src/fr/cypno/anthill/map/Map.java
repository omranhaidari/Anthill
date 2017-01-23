package fr.cypno.anthill.map;

import fr.cypno.anthill.map.exceptions.InvalidMapFile;
import fr.cypno.anthill.ant.Ant;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {

    private int numberOfRows;
    private int numberOfColumns;
    private int numberOfSources;
    private int[] quantityPerSource;
    private Cell[][] map;

    private Anthill anthill;

    public Anthill getAnthill() {
        return anthill;
    }

    public Map(String filePath) throws InvalidMapFile, FileNotFoundException {
        buildMap(filePath);
    }

    public void buildMap(String filePath) throws InvalidMapFile, FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        if (scanner.hasNextInt()) {
            this.numberOfRows = scanner.nextInt();
        } else {
            throw new InvalidMapFile("Incorrect number of rows.");
        }
        if (scanner.hasNextInt()) {
            this.numberOfColumns = scanner.nextInt();
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
        this.map = new Cell[this.numberOfRows][this.numberOfColumns];
        char value;
        int sourceCount = 0;
        int anthillCount = 0;
        for (int i = 0; i < this.numberOfRows; i++) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int j = 0; j < this.numberOfColumns; j++) {

                    if (line.length() > j) {
                        value = line.charAt(j);
                    } else {
                        throw new InvalidMapFile("Line " + (i + 1) + " : length (" + line.length() + ") differs  from expected length (" + this.numberOfColumns + ").");
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
                throw new InvalidMapFile("Not enough rows (" + i + "), expected : " + this.numberOfRows + ".");
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
        s += "cols: " + this.numberOfColumns + "\n";
        s += "rows: " + this.numberOfRows + "\n";
        s += "sources: " + this.numberOfSources + "\n";
        s += Map.printCellMatrix(this.map);
        return s;
    }

    public static String printCellMatrix(Cell[][] matrix) {
        return printCellMatrix(matrix, null);
    }

    public String printCellMatrix(ArrayList<Ant> ants) {
        return printCellMatrix(this.map, ants);
    }

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
                    s += "" + nb;
                else
                    s += matrix[i][j].getChar();
            }
            s += "\n";
        }
        return s;
    }

    public Cell[][] getMatrix(int c1, int l1, int c2, int l2) {
        Cell[][] matrix = new Cell[c2 - c1 + 1][];
        for (int c = c1; c <= c2; c++) {
            matrix[c - c1] = new Cell[l2 - l1 + 1];
            for (int l = l1; l <= l2; l++) {
                if (c < 0 || c >= map.length || l < 0 || l >= map[c].length)
                    matrix[c -c1][l - l1] = null;
                else
                    matrix[c -c1][l - l1] = map[c][l];
            }
        }
        return matrix;
    }

    private boolean isClosed() {
        for (int i = 0; i < this.numberOfRows; i++)
            if (!(map[i][0] instanceof Obstacle && map[i][this.numberOfColumns - 1] instanceof Obstacle))
                return false;
        for (int i = 0; i < this.numberOfColumns; i++)
            if (!(map[0][i] instanceof Obstacle && map[this.numberOfRows - 1][i] instanceof Obstacle))
                return false;
        return true;
    }
}
