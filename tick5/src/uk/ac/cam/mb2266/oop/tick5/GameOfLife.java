package uk.ac.cam.mb2266.oop.tick5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class GameOfLife {
    private World world;
    private PatternStore store;
    private ArrayList<World> cachedWorlds = new ArrayList<>();

    private World copyWorld(boolean useCloning) {
        if (!useCloning){
            if (world instanceof PackedWorld){
                return new PackedWorld((PackedWorld) world);
            } else if (world instanceof ArrayWorld){
                return new ArrayWorld((ArrayWorld) world);
            }
        } else {
            try {
                if (world instanceof PackedWorld) {
                    return ((PackedWorld) world).clone();
                } else if (world instanceof ArrayWorld) {
                    return ((ArrayWorld) world).clone();
                }
            } catch (CloneNotSupportedException e){
                throw new RuntimeException("This should never happen as uk.ac.cam.mb2266.oop.tick4.PackedWorld and uk.ac.cam.mb2266.oop.tick4.ArrayWorld both implement Cloneable");
            }
        }
        return null;
    }

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
                    if (world.getGenerationCount()+1>=cachedWorlds.size()){
                        World copyWorld = copyWorld(true);
                        copyWorld.nextGeneration();
                        cachedWorlds.add(copyWorld);
                        world = copyWorld;
                        print();
                    } else {
                        world = cachedWorlds.get(world.getGenerationCount()+1);
                        print();
                    }
                }
            }

            else if (response.equals("b")) {
                if (world.getGenerationCount() == 0) {
                    world = cachedWorlds.get(0);
                    print();
                } else {
                    world = cachedWorlds.get(world.getGenerationCount()-1);
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
                World firstCached = copyWorld(true);
                cachedWorlds.add(firstCached);
                world = firstCached;
                print();
            }

        }
    }

    public static void main(String args[]) throws PatternFormatException {
        if (args.length!=1) {
            System.out.println("Usage: java uk.ac.cam.mb2266.oop.tick4.GameOfLife <path/url to store>");
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

    public void print(){
        System.out.println("- " + world.getGenerationCount());
        for (int row = 0; row < world.getHeight(); row++) {
            for (int col = 0; col < world.getWidth(); col++){
                System.out.print(world.getCell(col, row) ? "#" : "_");
            }
            System.out.println();
        }
    }

}
