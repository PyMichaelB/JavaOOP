package uk.ac.cam.mb2266.oop.tick4;

public class PackedWorld extends World implements Cloneable{

    private long world;
    //COPY CONSTRUCTOR

    public PackedWorld(PackedWorld p) {
        super(p);
        world = p.world;
    }

    public PackedWorld(String format) throws PatternFormatException {
        super(format);
        if (getHeight()*getWidth()>64) {
            throw new PatternFormatException("More than 64 bits - invalid format");
        }
        getPattern().initialise(this);
    }

    public PackedWorld(Pattern p) throws PatternFormatException {
        super(p);
        getPattern().initialise(this);
    }

    @Override
    public void nextGenerationImpl() {
        //DO THIS NEXT
        long newworld = 0L;
            for (int col = 0; col < getWidth(); col++) {
                for (int row = 0; row < getHeight(); row++){
                    boolean nextCell = computeCell(col, row);

                    if (nextCell) {
                        newworld = (((newworld >> col+row*getWidth()) | 1L) << col+row*getWidth()) | newworld;
                    }
                    else {
                        if (((newworld >> col+row*getWidth()) & 1L) > 0L) {
                            newworld = newworld ^ (1L << col+row*getWidth());
                        }
                    }
                }
            }
            world = newworld;
    }

    @Override
    public boolean getCell(int c, int r) {
        if (r < 0 || r >= getHeight()) {
            return false;
        } else if (c < 0 || c >= getWidth()) {
            return false;
        } else {
            return (((world >> c+r*getWidth()) & 1L) == 1);
        }
    }


    @Override
    public void setCell(int col, int row, boolean val) {
        if (val) {
            // TODO: complete this using bitwise operators
            // update the value "packed" with the bit at "position" set to 1
            world = (((world >> col+row*getWidth()) | 1L) << col+row*getWidth()) | world;

        }
        else {
            // TODO: complete this using bitwise operators
            // update the value "packed" with the bit a "position" set to 0
            if (((world >> col+row*getWidth()) & 1L) > 0L) {
                world = world ^ (1L << col+row*getWidth());
            }
        }
    }
    @Override
    public PackedWorld clone() throws CloneNotSupportedException {
        return (PackedWorld) super.clone();
    }
}
