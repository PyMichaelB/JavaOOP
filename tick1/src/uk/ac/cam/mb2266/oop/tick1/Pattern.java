package uk.ac.cam.mb2266.oop.tick1;

public class Pattern {

  private String name;
  private String author;
  private int width;
  private int height;
  private int startCol;
  private int startRow;
  private String cells;
   
  public String getName() {
    return name;
  }

  public String getAuthor() {
  	return author;
  }

  public int getWidth() {
  	return width;
  }

  public int getHeight() {
  	return height;
  }

  public int getstartCol() {
  	return startCol;
  }

  public int getstartRow() {
  	return startRow;
  }

  public String getCells() {
  	return cells;
  }

	public Pattern(String format) {
	//TODO: initialise all fields of this class using contents of 
	//      'format' to determine the correct values (this code
	//      is similar tothat you used in the new ArrayLife constructor
		name = format.split(":")[0];
		author = format.split(":")[1];

		width = Integer.parseInt(format.split(":")[2]);
		height = Integer.parseInt(format.split(":")[3]);

		startCol = Integer.parseInt(format.split(":")[4]);
		startRow = Integer.parseInt(format.split(":")[5]);
		cells = format.split(":")[6];

	}

	public void initialise(boolean[][] world) {
	//TODO: update the values in the 2D array representing the state of
	//      'world' as expressed by the contents of the field 'cells'.
		for(int k = 0; k < (cells.split(" ")).length; k++) {
			for(int l = 0; l < ((cells.split(" "))[k].toCharArray()).length; l++) {
				if ((cells.split(" "))[k].toCharArray()[l] == '1') {
					world[k+startRow][l+startCol] = true;
				}		
			}
		}
	}
}
 