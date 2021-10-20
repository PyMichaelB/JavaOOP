package uk.ac.cam.mb2266.oop.supo3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JPanelColouring extends JFrame implements KeyListener {
    private int colour;
    private JPanel myPanel;

    private JPanelColouring(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(512,384);

        createColourPanel();
        add(myPanel);
    }

    private void createColourPanel() {
        myPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                setBackground(checkColour());
            }
        };
        myPanel.setFocusable(true);
        myPanel.addKeyListener(this);
    }

    public void keyTyped(KeyEvent evt){}

    public void keyPressed(KeyEvent evt){
        System.out.println("Hello");
        int keyCode = evt.getKeyCode();
        displayNew(keyCode);
    }

    public void keyReleased(KeyEvent evt){};

    private void displayNew(int keyCode){
        if(keyCode == KeyEvent.VK_LEFT){
            if(colour == 0){
                colour = 2;
            } else {
                colour = (colour - 1);
            }
        } else if(keyCode == KeyEvent.VK_RIGHT){
            colour = (colour + 1) % 3;
        }
        myPanel.repaint();
    }

    private Color checkColour() {
        if(colour == 0){return Color.red;}
        else if(colour == 1){return Color.blue;}
        else {return Color.green;}
    }


    public static void main(String[] args) {
        JPanelColouring gui = new JPanelColouring("Red and green");
        gui.setVisible(true);
    }
}
