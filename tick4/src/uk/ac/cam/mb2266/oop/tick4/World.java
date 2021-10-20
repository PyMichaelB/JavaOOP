package uk.ac.cam.mb2266.oop.tick4;

public abstract class World {
    private int generation;
    private Pattern pattern;

    public World(World w){
        generation = w.getGenerationCount();
        pattern = w.getPattern();
    }

    public World(String patter) throws PatternFormatException {
        generation = 0;
        pattern = new Pattern(patter);
    }

    public World(Pattern p) {
        generation = 0;
        pattern = p;
    }

    public int getWidth(){ return pattern.getWidth(); }

    public int getHeight(){ return pattern.getHeight(); }

    public int getGenerationCount(){ return generation; }

    protected void incrementGenerationCount(){ generation+=1; }

    protected Pattern getPattern(){
        return pattern;
    }

    public void nextGeneration(){
        nextGenerationImpl();
        generation++;
    }

    protected abstract void nextGenerationImpl();

    public abstract boolean getCell(int c, int r);

    public abstract void setCell(int col, int row, boolean val);

    protected int countNeighbours(int x, int y){
        int number = 0;
        for (int colNow = x - 1; x+1 >= colNow; colNow++){
            for(int rowNow = y - 1; y+1 >= rowNow; rowNow++){
                if (getCell(colNow, rowNow)){
                    number+=1;
                }

            }
        }

        if (getCell(x, y)) {
            number -= 1;
        }
        return number;
    }

    protected boolean computeCell(int x, int y){
        // liveCell is true if the cell at position (col,row) in world is live
        boolean liveCell = getCell(x, y);

        // neighbours is the number of live neighbours to cell (col,row)
        int neighbours = countNeighbours(x, y);

        // we will return this value at the end of the method to indicate whether
        // cell (col,row) should be live in the next generation
        boolean nextCell = false;

        //A live cell with less than two neighbours dies (underpopulation)
        if ((neighbours < 2) & liveCell) {
            nextCell = false;
        }

        //A live cell with two or three neighbours lives (a balanced population)
        //TODO: write a if statement to check neighbours and update nextCell
        if (((1 < neighbours) & (neighbours < 4)) & liveCell) {
            nextCell = true;
        }

        //A live cell with with more than three neighbours dies (overcrowding)
        //TODO: write a if statement to check neighbours and update nextCell
        if ((3 < neighbours) & liveCell){
            nextCell = false;
        }

        //A dead cell with exactly three live neighbours comes alive
        //TODO: write a if statement to check neighbours and update nextCell
        if ((neighbours == 3) & !liveCell){
            nextCell = true;
        }

        return nextCell;
    }
}