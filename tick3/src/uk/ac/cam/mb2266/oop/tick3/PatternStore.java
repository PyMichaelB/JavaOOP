package uk.ac.cam.mb2266.oop.tick3;

import java.io.*;
import java.net.*;
import java.util.*;

public class PatternStore {

    private List<Pattern> patterns = new LinkedList<>();
    private Map<String,List<Pattern>> mapAuths = new HashMap<>();
    private Map<String,Pattern> mapName = new HashMap<>();

    public PatternStore(String source) throws IOException {
        if (source.startsWith("http://") || source.startsWith("https://")) {
            loadFromURL(source);
        }
        else {
            loadFromDisk(source);
        }
    }

    public PatternStore(Reader source) throws IOException {
        load(source);
    }

    private void load(Reader r) throws IOException {
        BufferedReader b = new BufferedReader(r);
        String line;
        while ( (line = b.readLine()) != null) {
            try {
                Pattern p = new Pattern(line);
                patterns.add(p);

                if (mapAuths.get(p.getAuthor()) != null){
                    List<Pattern> current = mapAuths.get(p.getAuthor());
                    current.add(p);
                    mapAuths.put(p.getAuthor(), current);
                } else {
                    List<Pattern> firstAuthor = new ArrayList<>();
                    firstAuthor.add(p);
                    mapAuths.put(p.getAuthor(), firstAuthor);
                }

                mapName.put(p.getName(), p);
            } catch (PatternFormatException e) {
                System.out.println("Malformed pattern: " + line + ".");
            }
        }
    }

    private void loadFromURL(String url) throws IOException {
        URL destination = new URL(url);
        URLConnection conn = destination.openConnection();
        Reader r = new InputStreamReader(conn.getInputStream());
        load(r);
    }

    private void loadFromDisk(String filename) throws IOException {
        Reader r = new FileReader(filename);
        load(r);
    }

    public static void main(String args[]) throws IOException, PatternNotFound {
        PatternStore p = new PatternStore(args[0]);
    }

    //WORKING
    public List<Pattern> getPatternsNameSorted() {
        Collections.sort(patterns, new Comparator<Pattern>() {
            public int compare(Pattern p1, Pattern p2) {
                return (p1.getName().toLowerCase().compareTo(p2.getName().toLowerCase()));
            }
        });
        List<Pattern> copy = new LinkedList<>(patterns);
        return copy;
    }

    //WORKING
    public List<Pattern> getPatternsAuthorSorted() {
        Collections.sort(patterns, new Comparator<Pattern>() {
            public int compare(Pattern p1, Pattern p2) {
                if (p1.getAuthor().compareTo(p2.getAuthor()) == 0){
                    return (p1.getName().compareTo(p2.getName()));
                } else {
                    return (p1.getAuthor()).compareTo(p2.getAuthor());
                }
            }
        });
        List<Pattern> copy = new LinkedList<>(patterns);
        return copy;
    }

    //WORKING
    public List<Pattern> getPatternsByAuthor(String author) throws PatternNotFound {
        if (mapAuths.containsKey(author)) {
            List<Pattern> copy = new ArrayList<>(mapAuths.get(author));
            Collections.sort(copy);
            return copy;
        } else {
            throw new PatternNotFound("Author does not appear");
        }
    }

    //WORKING
    public Pattern getPatternByName(String name) throws PatternNotFound {
        if (mapName.containsKey(name)) {
            return mapName.get(name);
        } else {
            throw new PatternNotFound("Name does not appear");
        }
    }

    //WORKING
    public List<String> getPatternAuthors() {
        List<String> auths = new ArrayList<>();
        for(String auth: mapAuths.keySet()){
            auths.add(auth);
        }
        Collections.sort(auths);
        List<String> copy = new ArrayList<>(auths);
        return copy;
    }

    //WORKING
    public List<String> getPatternNames() {
        List<String> names = new ArrayList<>();

        names.addAll(mapName.keySet());

        List<String> copy = new ArrayList<>(names);
        Collections.sort(copy, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return (s1.toLowerCase().compareTo(s2.toLowerCase()));
            }
        });

        return copy;
    }


}


