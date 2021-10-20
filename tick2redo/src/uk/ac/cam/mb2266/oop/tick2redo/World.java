package uk.ac.cam.mb2266.oop.tick2redo;

public abstract class World {
    private int generation;
    private Pattern pattern;

    //Constructor
    World(String pattern) {
        this.generation = 0;
        this.pattern = new Pattern(pattern);
    }

    public abstract boolean getCell(int c, int r);

    public int getWidth9
}
