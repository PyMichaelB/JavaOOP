package uk.ac.cam.mb2266.oop.supo3;

public class Pattern implements Comparable<Pattern> {

  private String name;
  private String author;
  private int width;
  private int height;
  private int startCol;
  private int startRow;
  private String cells;

  @Override
  public int compareTo(Pattern o){
  	return (this.getName()).compareTo(o.getName());
  }

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

public Pattern(String format) throws PatternFormatException {
  	if (format.equals("")){
  		throw new PatternFormatException("Please specify a pattern");
	} else {
  		int len = format.split(":").length;
		if (len != 7) {
			throw new PatternFormatException("Invalid pattern format: Incorrect number of fields in pattern (found " + len +").");
		} else {
			String[] ls = format.split(":");
			try {width = Integer.parseInt(ls[2]);
			} catch (NumberFormatException n) {
				throw new PatternFormatException("Invalid pattern format: Could not interpret the width field as a number ('" + ls[2] +"' given).");
			}
			try {height = Integer.parseInt(ls[3]);
			} catch (NumberFormatException n) {
				throw new PatternFormatException("Invalid pattern format: Could not interpret the height field as a number ('" + ls[3] +"' given).");
			}
			try {startCol = Integer.parseInt(ls[4]);
			} catch (NumberFormatException n) {
				throw new PatternFormatException("Invalid pattern format: Could not interpret the startX field as a number ('" + ls[4] +"' given).");
			}
			try {startRow = Integer.parseInt(ls[5]);
			} catch (NumberFormatException n) {
				throw new PatternFormatException("Invalid pattern format: Could not interpret the startY field as a number ('" + ls[5] +"' given).");
			}

			name = ls[0];
			author = ls[1];
			cells = ls[6];

		}
	}
}

public void initialise(World world) throws PatternFormatException {

		for(int k = 0; k < (getCells().split(" ")).length; k++) {

			for(int l = 0; l < ((getCells().split(" "))[k].toCharArray()).length; l++) {

				if ((getCells().split(" "))[k].toCharArray()[l] == '1') {
					world.setCell(l+getstartCol(), k+getstartRow(), true);

				} else if ((getCells().split(" "))[k].toCharArray()[l] == '0'){
					world.setCell(l+getstartCol(),k+getstartRow(), false);

				} else {
					throw new PatternFormatException("Invalid pattern format: Malformed pattern '" + cells + "'.");
				}

			}

		}
	}
}

 