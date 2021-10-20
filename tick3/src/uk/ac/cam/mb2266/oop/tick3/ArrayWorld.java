package uk.ac.cam.mb2266.oop.tick3;

public class ArrayWorld extends World {
    private boolean[][] world;

    public ArrayWorld(String serial) throws PatternFormatException {
        super(serial);

        // TODO: initialise world
        world = new boolean[getHeight()][getWidth()];
        getPattern().initialise(this);
    }

    public ArrayWorld(Pattern p) throws PatternFormatException {
        super(p);
        world = new boolean[p.getHeight()][p.getWidth()];
        getPattern().initialise(this);
    }

    @Override
    public void nextGenerationImpl() {
        boolean[][] nextGeneration = new boolean[world.length][];
        for (int y = 0; y < world.length; ++y) {
            nextGeneration[y] = new boolean[world[y].length];
            for (int x = 0; x < world[y].length; ++x) {
                boolean nextCell = computeCell(x, y);
                nextGeneration[y][x] = nextCell;
            }
        }
        world = nextGeneration;
    }

    @Override
    public boolean getCell(int c, int r) {
        if (r < 0 || r >= getHeight()) {
            return false;
        }
        if (c < 0 || c >= getWidth()) {
            return false;
        }
        return world[r][c];
    }

    @Override
    public void setCell(int col, int row, boolean val) {
        if ((row >= 0 && row < getHeight()) && (col >= 0 || col < getWidth())) {
            world[row][col] = val;
        }
    }
}
