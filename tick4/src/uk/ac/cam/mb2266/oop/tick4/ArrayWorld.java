package uk.ac.cam.mb2266.oop.tick4;

public class ArrayWorld extends World implements Cloneable {
    private boolean[][] world;
    private boolean[] deadRow;
    //COPY CONSTRUCTOR

    public ArrayWorld(ArrayWorld a) {
        super(a);
        deadRow = a.deadRow;
        boolean[][] arrayCopy = new boolean[a.getHeight()][a.getWidth()];
        for(int i = 0; i < a.world.length; i++){
            for(int j = 0; j<a.world[i].length; j++){
                arrayCopy[i][j] = a.world[i][j];
            }
        }

        int count = 0;
        for(int i=0; i<getPattern().getHeight(); i++){
            for(int j=0; j<getPattern().getWidth(); j++){
                if(!arrayCopy[i][j]){
                    count++;
                }
            }
            if (count==getPattern().getWidth()){
                arrayCopy[i]=deadRow;
                count = 0;
            }
        }

        world = arrayCopy;
    }

    public ArrayWorld(String serial) throws PatternFormatException {
        super(serial);

        world = new boolean[getHeight()][getWidth()];
        getPattern().initialise(this);
        deadRow = new boolean[getPattern().getWidth()];

        int count = 0;
        for(int i=0; i<getPattern().getHeight(); i++){
            for(int j=0; j<getPattern().getWidth(); j++){
                if(!world[i][j]){
                    count++;
                }
            }
            if (count==getPattern().getWidth()){
                world[i]=deadRow;
                count = 0;
            }
        }
    }

    public ArrayWorld(Pattern p) throws PatternFormatException {
        super(p);
        world = new boolean[p.getHeight()][p.getWidth()];
        getPattern().initialise(this);
        deadRow = new boolean[p.getWidth()];

        int count = 0;
        for(int i=0; i<getPattern().getHeight(); i++){
            for(int j=0; j<getPattern().getWidth(); j++){
                if(!world[i][j]){
                    count++;
                }
            }
            if (count==getPattern().getWidth()){
                world[i]=deadRow;
                count = 0;
            }
        }
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

    @Override
    public ArrayWorld clone() throws CloneNotSupportedException {
        ArrayWorld cloned = (ArrayWorld) super.clone();

        boolean[][] arrayCopy = new boolean[cloned.getPattern().getHeight()][cloned.getPattern().getWidth()];
        for(int i = 0; i <cloned.world.length; i++){
            for(int j = 0; j<cloned.world[i].length; j++){
                arrayCopy[i][j] = cloned.world[i][j];
            }
        }

        int count = 0;
        for(int i=0; i<getPattern().getHeight(); i++){
            for(int j=0; j<getPattern().getWidth(); j++){
                if(!arrayCopy[i][j]){
                    count++;
                }
            }
            if (count==getPattern().getWidth()){
                arrayCopy[i]=deadRow;
                count = 0;
            }
        }

        cloned.world = arrayCopy;
        return cloned;



    }
}
