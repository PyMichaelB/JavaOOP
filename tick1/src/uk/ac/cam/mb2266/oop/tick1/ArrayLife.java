package uk.ac.cam.mb2266.oop.tick1;

public class ArrayLife {
	private int width;
	private int height;
	private boolean[][] world;
	private Pattern pattern;

	public ArrayLife(String format) {
		pattern = new Pattern(format);
		width = pattern.getWidth();
		height = pattern.getHeight();
		world = new boolean[height][width];
		pattern.initialise(world);
	}

	public static void main(String[] args) throws Exception {
	  ArrayLife al = new ArrayLife(args[0]);
	  al.play();
	}
	
	public boolean getCell(int col, int row) {
	  if (row < 0 || row >= height) {
	    return false;
	  }
	  if (col < 0 || col >= width) {
	    return false;
	  }
	  return world[row][col];
	}
	
	public void setCell(int col, int row, boolean value){
		world[row][col] = value;
	}
	
	public void print () {
		System.out.println("-");
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++){
				System.out.print(getCell(col, row) ? "#" : "_");
			}
			System.out.println();
		}	
	}
	
	private int countNeighbours(int col, int row){
		int number = 0;
		for (int colNow = col - 1; col+1 >= colNow; colNow++){
			for(int rowNow = row - 1; row+1 >= rowNow; rowNow++){
				if (getCell(colNow, rowNow)){
					number+=1;
				}
				
			}
		}
		if (getCell(col, row)) {
			number -= 1;
		} 
		return number;
	}
		
	private boolean computeCell(int col,int row){

		// liveCell is true if the cell at position (col,row) in world is live
		boolean liveCell = getCell(col, row);
		
	    // neighbours is the number of live neighbours to cell (col,row)
	    int neighbours = countNeighbours(col, row);

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
	
	public void nextGeneration() {
  		boolean[][] nextGeneration = new boolean[world.length][];
		for (int y = 0; y < world.length; ++y) {
		    nextGeneration[y] = new boolean[world[y].length];
		    for (int x = 0; x < world[y].length; ++x) {
		      boolean nextCell = computeCell(x, y);
		      nextGeneration[y][x]=nextCell;
		    }
		}
		world = nextGeneration;
	}
	
    public void play() throws java.io.IOException {
		int userResponse = 0;
	    while (userResponse != 'q') {
		    print();
		    userResponse = System.in.read();
		    nextGeneration();
	    }
    }
}
