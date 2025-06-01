package checkers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main_Menu extends JFrame implements ActionListener {

    boolean Flag = false;
    JButton Start = new JButton("START A NEW GAME");
    GameBoard CurrentGame;
    static JLabel L = new JLabel("");

    Main_Menu() {
        setTitle("Checkers");
        setSize(400, 300);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(3);
        setLayout(null);
        add(this.Start);
        getContentPane().setBackground(Color.darkGray);
        L.setOpaque(false);
        L.setFont(new Font("Serif", 1, 40));
        L.setSize(400, 100);
        L.setHorizontalAlignment(0);
        add(L);
        L.setLocation(0, 150);
        this.Start.setSize(200, 75);
        this.Start.setLocation(100, 75);
        this.Start.setFocusPainted(false);
        this.Start.setBackground(Color.WHITE);
        this.Start.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.Start) {
            if (this.Flag) {
                this.CurrentGame.CLOSE();
            }
            this.CurrentGame = null;
            this.CurrentGame = new GameBoard();
            this.Flag = true;
            this.CurrentGame.ChangeTurn();
        }
    }
}
