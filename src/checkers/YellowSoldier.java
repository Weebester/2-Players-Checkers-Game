package checkers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class YellowSoldier extends JPanel {

    YellowSoldier() {
        setSize(90, 90);
        setLocation(5, 5);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D G = (Graphics2D) g;

        G.setStroke(new BasicStroke(4.0F));
        G.setColor(Color.YELLOW);
        G.fillOval(3, 3, 84, 84);
        G.setColor(Color.black);
        G.drawOval(3, 3, 84, 84);

        G.setStroke(new BasicStroke(2.0F));
        int[] x = {45, 40, 40, 50, 50};
        int[] y = {20, 30, 60, 60, 30};
        G.setColor(Color.lightGray);
        G.fillPolygon(x, y, 5);
        G.setColor(Color.black);
        G.drawPolygon(x, y, 5);
        G.drawLine(45, 20, 45, 60);
        G.setColor(Color.orange);
        G.fillRect(35, 60, 20, 5);
        G.fillRect(40, 65, 10, 10);
        G.setColor(Color.black);
        G.drawRect(35, 60, 20, 5);
        G.drawRect(40, 65, 10, 10);
    }
}
