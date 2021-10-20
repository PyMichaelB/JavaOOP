package uk.ac.cam.mb2266.oop.tick5;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GUILife extends JFrame implements ListSelectionListener {
    private World world;
    private PatternStore store;
    private ArrayList<World> cachedWorlds = new ArrayList<>();
    private GamePanel current;
    private Timer timer;
    private boolean playing;

    private JButton playButton;

    public GUILife(PatternStore ps) {
        super("Game of Life");
        store=ps;

        current = (GamePanel) createGamePanel();
        playButton = new JButton("Play");
        playButton.addActionListener(e -> {
            runOrPause();
            current.display(world);
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024,768);
        add(createPatternsPanel(), BorderLayout.WEST);
        add(createControlPanel(), BorderLayout.SOUTH);
        add(current, BorderLayout.CENTER);
    }

    private void addBorder(JComponent component, String title) {
        Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border tb = BorderFactory.createTitledBorder(etch,title);
        component.setBorder(tb);
    }

    private JPanel createGamePanel() {
        GamePanel gamePanel = new GamePanel();
        addBorder(gamePanel,"Game Panel");
        return gamePanel;
    }

    private JPanel createPatternsPanel() {

        JPanel patt = new JPanel();
        addBorder(patt,"Patterns");
        DefaultListModel demo = new DefaultListModel();
        for(Pattern p: store.getPatternsNameSorted()){demo.addElement(p);}
        JList<Pattern> pats = new JList<>(demo);

        pats.addListSelectionListener(this);

        JScrollPane pane = new JScrollPane(pats);
        GridLayout patLayout = new GridLayout(0,1);
        patt.setLayout(patLayout);
        patt.add(pane);

        return patt;
    }

    private JPanel createControlPanel() {
        JPanel ctrl =  new JPanel();
        addBorder(ctrl,"Controls");

        JButton back = new JButton("< Back");
        JButton forward = new JButton("Forward >");

        back.addActionListener(e -> {
            if(!playing){
                moveBack();
                current.display(world);
            } else {
                runOrPause();
            }
        });

        forward.addActionListener(e -> {
            if(!playing){
                moveForward();
                current.display(world);
            } else {
                runOrPause();
            }
        });

        GridLayout controls = new GridLayout(1,0);
        ctrl.setLayout(controls);

        ctrl.add(back);
        ctrl.add(playButton);
        ctrl.add(forward);

        return ctrl;
    }

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

    //DISPLAY ISSUES
    private void moveBack(){
        System.out.println("Back");
        if (world.getGenerationCount() == 0) {
            world = cachedWorlds.get(0);
        } else {
            world = cachedWorlds.get(world.getGenerationCount()-1);
        }
        current.display(world);
    }

    //DISPLAY ISSUES
    private void moveForward(){
        System.out.println("Forward");
        if (world == null) {
            System.out.println("Please select a pattern to play (l to list):");
        } else {
            if (world.getGenerationCount()+1>=cachedWorlds.size()){
                World copyWorld = copyWorld(true);
                copyWorld.nextGeneration();
                cachedWorlds.add(copyWorld);
                world = copyWorld;
            } else {
                world = cachedWorlds.get(world.getGenerationCount()+1);
            }
        }
        current.display(world);
    }

    private void runOrPause() {
        if (playing) {
            timer.cancel();
            playing=false;
            playButton.setText("Play");
        }
        else {
            playing=true;
            playButton.setText("Stop");
            timer = new Timer(true);
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    moveForward();
                }
            }, 0, 500);
        }
    }

    public static void main(String[] args) throws IOException {
        GUILife gui = new GUILife(new PatternStore("https://www.cl.cam.ac.uk/teaching/1819/OOProg/ticks/life.txt"));
        gui.setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!playing){
            JList<Pattern> list = (JList<Pattern>) e.getSource();
            Pattern p = list.getSelectedValue();

            try {
                if (p.getWidth() * p.getHeight() > 64) {
                    world = new ArrayWorld(p);
                } else {
                    world = new PackedWorld(p);
                }
            } catch (PatternFormatException ex) {
                System.out.println(ex.getMessage());
            }

            cachedWorlds = new ArrayList<>();
            World firstCached = copyWorld(true);
            cachedWorlds.add(firstCached);
            world = firstCached;
            current.display(world);
        } else {
            runOrPause();
        }
    }
}
