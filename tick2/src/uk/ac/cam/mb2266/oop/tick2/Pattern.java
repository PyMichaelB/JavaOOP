package uk.ac.cam.mb2266.oop.tick2;

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

	public void initialise(World world) {

		for(int k = 0; k < (getCells().split(" ")).length; k++) {
			for(int l = 0; l < ((getCells().split(" "))[k].toCharArray()).length; l++) {
				if ((getCells().split(" "))[k].toCharArray()[l] == '1') {
					world.setCell(l+getstartCol(), k+getstartRow(), true);
				}		
			}
		}
	}
}
 