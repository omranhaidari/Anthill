package fr.cypno.anthill.map;

public class Obstacle extends Cell{
    
    public Obstacle(int x, int y) {
        this(x, y, 0);
    }

    public Obstacle(int x, int y, double pheromons) {
        super(x, y, pheromons);
    }

    @Override
    public String getChar() {
        return "#";
    }
}
