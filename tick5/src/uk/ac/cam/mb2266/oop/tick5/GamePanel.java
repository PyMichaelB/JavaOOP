package uk.ac.cam.mb2266.oop.tick5;

import java.awt.Color;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private World world = null;

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        // Paint the background white
        int dim = 0;
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (world != null) {
            g.setColor(Color.black);

            /*Finding right dimension such that cells remain square and board fills at
            least horizontal or vertical direction*/
            if(this.getHeight()/world.getHeight() < this.getWidth()/world.getWidth()){ //Condition for filling in one direction
                dim = this.getHeight()/world.getHeight();
            } else {
                dim = this.getWidth()/world.getWidth();
            }

            //Colour live cells black
            for(int i = 0; i<world.getHeight(); i++){
                for(int j = 0; j<world.getWidth(); j++){
                    if(world.getCell(i, j)){
                        g.fillRect(j*dim, i*dim, dim, dim);
                    }
                }
            }

            //Add gridlines
            g.setColor(Color.LIGHT_GRAY);
            for (int i = 0; i<=world.getWidth(); i++) {
                g.drawLine(i*dim, 0,i*dim, dim*(world.getHeight()));
            }

            for (int i = 0; i<=world.getHeight(); i++) {
                g.drawLine(0, i*dim, dim*(world.getWidth()), i*dim);
            }

            g.setColor(Color.BLACK);
            g.drawString("Generation "+(world.getGenerationCount()),10, (getHeight()- 10));
        }

    }

    public void display(World w) {
        world = w;
        repaint();
    }
}