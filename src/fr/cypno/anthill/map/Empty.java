package fr.cypno.anthill.map;

public class Empty extends Cell{
    
    public Empty(int x, int y) {
        this(x ,y, 0);
    }
    
    public Empty(int x, int y, double pheromons) {
        super(x, y, pheromons);
    }
}