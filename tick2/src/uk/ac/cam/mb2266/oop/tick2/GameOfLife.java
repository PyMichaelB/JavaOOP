package uk.ac.cam.mb2266.oop.tick2;

import java.io.IOException;

public class GameOfLife {
    private World world;

    public GameOfLife(World w) {
        world = w;
    }

    public void play() throws java.io.IOException {
        int userResponse = 0;
        while (userResponse != 'q') {
            print();
            userResponse = System.in.read();
            world.nextGeneration();
        }
    }

    public void print() {
        System.out.println("- " + world.getGenerationCount());
        for (int row = 0; row < world.getHeight(); row++) {
            for (int col = 0; col < world.getWidth(); col++){
                System.out.print(world.getCell(col, row) ? "#" : "_");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {

        World w = null;

        if (args.length == 2) {
            if (args[0].equals("--packed")) {
                try {
                    w = new PackedWorld(args[1]);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (args[0].equals("--array")) {
                w = new ArrayWorld(args[1]);
            }
        } else if (args.length == 1) {
            w = new ArrayWorld(args[0]);
        }

        //ASSUMES INPUT IS  NOT NULL - OTHERWISE WE WILL GET A NULL POINTER EXCEPTION

        GameOfLife gol = new GameOfLife(w);
        gol.play();
    }
}
