package fr.cypno.anthill.map;

import fr.cypno.anthill.map.exceptions.InvalidMapFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
    
    private int numberOfRows;
    private int numberOfColumns;
    private int numberOfSources;
    private int[] quantityPerSource;
    private Cell[][] map;
    
    public Map(String filePath) throws InvalidMapFile{
        try {
            Scanner scanner = new Scanner(new File(filePath));
            if(scanner.hasNextInt())
                this.numberOfRows = scanner.nextInt();
            else
                throw new InvalidMapFile("Incorrect number of rows.");
            if(scanner.hasNextInt())
                this.numberOfColumns = scanner.nextInt();
            else
                throw new InvalidMapFile("Incorrect number of columns.");
            if(scanner.hasNextInt())
                this.numberOfSources = scanner.nextInt();
            else
                throw new InvalidMapFile("Incorrect number of sources.");
            this.quantityPerSource = new int[this.numberOfSources];
            for(int i = 0; i < this.numberOfSources; i++){
                if(scanner.hasNextInt())
                    this.quantityPerSource[i] = scanner.nextInt();
                else
                    throw new InvalidMapFile("Invalid quantity in source " + (i + 1) + ".");
            }
            
            scanner.nextLine(); // Vide la ligne
            this.map = new Cell[this.numberOfRows][this.numberOfColumns];
            char value;
            int sourceCount = 0;
            int anthillCount = 0;
            for(int i = 0; i < this.numberOfRows; i++){
                if(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    for(int j = 0; j < this.numberOfColumns; j++){

                        if(line.length() > j)
                            value = line.charAt(j);
                        else
                            throw new InvalidMapFile("Line " + (i + 1) + " : length (" + line.length() +  ") differs  from expected length (" + this.numberOfColumns + ")." );
                        
                        switch(value){
                            case ' ':
                                this.map[i][j] = new Empty();
                                break;
                            case 'o':
                                if(sourceCount < this.numberOfSources)
                                    this.map[i][j] = new Food(this.quantityPerSource[sourceCount++]);
                                else
                                    throw new InvalidMapFile("Too many sources.");
                                break;
                            case 'x':
                                this.map[i][j] = new Anthill();
                                if(++anthillCount > 1)
                                    throw new InvalidMapFile("Too many anthills.");
                                break;
                            case '#':
                                this.map[i][j] = new Obstacle();
                                break;
                            default:
                                throw new InvalidMapFile("Invalid element in the matrix : [" + i + "," + j + "] : " + value + ".");
                        }
                    }
                }
                else
                    throw new InvalidMapFile("Not enough rows (" + i + "), expected : " + this.numberOfRows + ".");
            }
            scanner.close();
            if(sourceCount == 0)
                throw new InvalidMapFile("No source found.");
            else if(sourceCount != this.numberOfSources)
                throw new InvalidMapFile("Number of sources doesn't match : expected " + this.numberOfSources + ", found " + sourceCount + ".");
            if(!this.isClosed())
                throw new InvalidMapFile("Unclosed map.");
            if(anthillCount == 0)
                throw new InvalidMapFile("No anthill found.");
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
    }
    
    public void afficher(){
            System.out.println(this.numberOfColumns);
            System.out.println(this.numberOfRows);
            System.out.println(this.numberOfSources);
            for(int i = 0; i < this.numberOfSources; i++){
                System.out.println(this.quantityPerSource[i]);
            }
               
            for(int i = 0; i < this.map.length; i++){
                for(int j = 0; j < this.map[i].length; j++){
                    
                  System.out.print(this.map[i][j]);  
                }
                System.out.println();  
            }
    }
    
    private boolean isClosed(){
        for(int i = 0; i < this.numberOfRows; i++)
            if(!(map[i][0] instanceof Obstacle && map[i][this.numberOfColumns-1] instanceof Obstacle))
                return false;
        
        for(int i = 0; i < this.numberOfColumns; i++)
            if(!(map[0][i] instanceof Obstacle && map[this.numberOfRows-1][i] instanceof Obstacle))
                return false;
        
        return true;
    }
    
}
