package uk.ac.cam.mb2266.oop.tick3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class GameOfLife {
    private World world;
    private PatternStore store;

    public GameOfLife(PatternStore store) {
            this.store = store;
    }

    public void play() throws IOException, PatternFormatException {

        String response="";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please select a pattern to play (l to list):");
        while (!response.equals("q")) {
            response = in.readLine();
            System.out.println(response);
            if (response.equals("f")) {
                if (world == null) {
                    System.out.println("Please select a pattern to play (l to list):");
                }
                else {
                    world.nextGeneration();
                    print();
                }
            }
            else if (response.equals("l")) {
                List<Pattern> names = store.getPatternsNameSorted();
                int i = 0;
                for (Pattern p : names) {
                    System.out.println(i+" "+p.getName()+"  ("+p.getAuthor()+")");
                    i++;
                }
            }
            else if (response.startsWith("p")) {
                List<Pattern> names = store.getPatternsNameSorted();
                int pat = Integer.parseInt(response.split(" ")[1]);
                Pattern p = names.get(pat);
                if (p.getWidth() * p.getHeight() > 64) {
                    world = new ArrayWorld(p);
                } else {
                    world = new PackedWorld(p);
                }
                // TODO: Get the associated pattern
                // TODO: Initialise world using PackedWorld or ArrayWorld based
                // on pattern world size
                print();
            }
        }
    }

    public static void main(String args[]) throws IOException, PatternFormatException {
        if (args.length!=1) {
            System.out.println("Usage: java GameOfLife <path/url to store>");
            return;
        }

        try {
            PatternStore ps = new PatternStore(args[0]);
            GameOfLife gol = new GameOfLife(ps);
            gol.play();
        }
        catch (IOException ioe) {
            System.out.println("Failed to load pattern store");
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

    /*public static void main(String[] args) throws IOException {

        World w = null;
        try {
            if (args.length == 2) {

                if (args[0].equals("--packed")) {
                    w = new PackedWorld(args[1]);
                } else if (args[0].equals("--array")) {
                    w = new ArrayWorld(args[1]);
                }

            } else if (args.length == 1) {
                w = new ArrayWorld(args[0]);
            }

        } catch (PatternFormatException ex){
            System.out.println(ex.getMessage());
        }

        if (w != null) {
            GameOfLife gol = new GameOfLife(w);
            gol.play();
        }
    }*/
}
