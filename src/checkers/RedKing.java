package checkers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class RedKing extends JPanel {

    RedKing() {
        setSize(90, 90);
        setLocation(5, 5);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D G = (Graphics2D) g;

        G.setStroke(new BasicStroke(4.0F));
        G.setColor(Color.red);
        G.fillOval(3, 3, 84, 84);
        G.setColor(Color.black);
        G.drawOval(3, 3, 84, 84);

        G.setStroke(new BasicStroke(2.0F));
        int[] x = {30, 60, 70, 52, 45, 38, 20};
        int[] y = {60, 60, 30, 45, 30, 45, 30};
        G.setColor(Color.orange);
        G.fillPolygon(x, y, 7);
        G.setColor(Color.black);
        G.drawPolygon(x, y, 7);
    }
}
