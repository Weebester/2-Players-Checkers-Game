package checkers;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class GameBoard extends JFrame implements ActionListener {

    Border Active = BorderFactory.createLineBorder(Color.GREEN, 4);
    Tile[][] Tiles = new Tile[8][8];
    Boolean TurnFlag = false;
    boolean MovePhase = false;
    int X = 7;
    int Y = 0;
    boolean RoY = false;
    boolean KoS = false;
    boolean KillStreak = false;
    int prex = 7;
    int prey = 0;
    boolean preroy = false;
    boolean prekos = false;
    Tile tempC = null;

    GameBoard() {
        setTitle("Checkers");
        setSize(815, 838);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout( null);
        setVisible(true);
        intialize();
        Main_Menu.L.setText(" ");
    }

    Color SwitchColor(Color c) {
        if (c == Color.white) {
            c = Color.darkGray;
        } else {
            c = Color.white;
        }
        return c;
    }

    void DeactivateAll() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.Tiles[i][j].setEnabled(false);
                this.Tiles[i][j].setBorder((Border) null);
            }
        }
    }

    void ActivateRed() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((this.Tiles[i][j]).EoP) {
                    if ((this.Tiles[i][j]).RoY) {
                        this.Tiles[i][j].setEnabled(true);
                        this.Tiles[i][j].setBorder(this.Active);
                    } else {
                        this.Tiles[i][j].setEnabled(false);
                        this.Tiles[i][j].setBorder((Border) null);
                    }
                }
            }
        }
    }

    void ActivateYellow() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((this.Tiles[i][j]).EoP) {
                    if ((this.Tiles[i][j]).RoY) {
                        this.Tiles[i][j].setEnabled(false);
                        this.Tiles[i][j].setBorder((Border) null);
                    } else {
                        this.Tiles[i][j].setEnabled(true);
                        this.Tiles[i][j].setBorder(this.Active);
                    }
                }
            }
        }
    }

    boolean Judgement() {
        boolean EGF = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((this.Tiles[i][j]).EoP) {
                    boolean KF = false, MF = false;
                    if (this.TurnFlag) {
                        if ((this.Tiles[i][j]).RoY) {
                            KF = KillCheck(i, j);
                            MF = MoveCheck(i, j);

                            EGF = (EGF || KF || MF);
                        }

                    } else if (!(this.Tiles[i][j]).RoY) {
                        KF = KillCheck(i, j);
                        MF = MoveCheck(i, j);

                        EGF = (EGF || KF || MF);
                    }
                }
            }
        }

        DeactivateAll();
        return EGF;
    }

    void ChangeTurn() {
        if (!Judgement()) {
            DeactivateAll();
            if (!this.TurnFlag) {
                Main_Menu.L.setForeground(Color.red);
                Main_Menu.L.setText("Red Wins");
            } else {
                Main_Menu.L.setForeground(Color.yellow);
                Main_Menu.L.setText("Yellow Wins");
            }

            Checkers.M.repaint();
        } else {
            if (this.TurnFlag) {
                ActivateRed();
            } else {
                ActivateYellow();
            }
            this.TurnFlag = !this.TurnFlag;
            Crown();
        }
    }

    void intialize() {
        boolean FlagR = true, FlagB = false;
        Color c = Color.white;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.Tiles[i][j] = new Tile(i * 100, j * 100, c);
                add(this.Tiles[i][j]);
                this.Tiles[i][j].addActionListener(this);
                this.Tiles[i][j].setEnabled(false);
                c = SwitchColor(c);
                if (j < 3) {
                    if (FlagR) {
                        this.Tiles[i][j].add((this.Tiles[i][j]).RS);
                        (this.Tiles[i][j]).EoP = true;
                        (this.Tiles[i][j]).RoY = true;
                        FlagR = false;
                    } else {
                        FlagR = true;
                    }
                }
                if (j > 4) {
                    if (FlagB) {
                        this.Tiles[i][j].add((this.Tiles[i][j]).YS);
                        (this.Tiles[i][j]).EoP = true;
                        (this.Tiles[i][j]).RoY = false;
                        FlagB = false;
                    } else {
                        FlagB = true;
                    }
                }
            }
            c = SwitchColor(c);
        }
    }

    boolean YellowMoveCheck(int x, int y) {
        this.Tiles[x][y].setEnabled(true);
        this.Tiles[x][y].setBorder(this.Active);
        boolean MF = false;
        if (x - 1 > -1 && y - 1 > -1
                && !(this.Tiles[x - 1][y - 1]).EoP) {
            this.Tiles[x - 1][y - 1].setEnabled(true);
            this.Tiles[x - 1][y - 1].setBorder(this.Active);
            MF = true;
        }

        if (x + 1 < 8 && y - 1 > -1
                && !(this.Tiles[x + 1][y - 1]).EoP) {
            this.Tiles[x + 1][y - 1].setEnabled(true);
            this.Tiles[x + 1][y - 1].setBorder(this.Active);
            MF = true;
        }

        return MF;
    }

    boolean RedMoveCheck(int x, int y) {
        this.Tiles[x][y].setEnabled(true);
        this.Tiles[x][y].setBorder(this.Active);
        boolean MF = false;
        if (x + 1 < 8 && y + 1 < 8
                && !(this.Tiles[x + 1][y + 1]).EoP) {
            this.Tiles[x + 1][y + 1].setEnabled(true);
            this.Tiles[x + 1][y + 1].setBorder(this.Active);
            MF = true;
        }

        if (x - 1 > -1 && y + 1 < 8
                && !(this.Tiles[x - 1][y + 1]).EoP) {
            this.Tiles[x - 1][y + 1].setEnabled(true);
            this.Tiles[x - 1][y + 1].setBorder(this.Active);
            MF = true;
        }

        return MF;
    }

    boolean MoveCheck(int x, int y) {
        boolean MF = false;
        if (!(this.Tiles[x][y]).KoS) {
            if ((this.Tiles[x][y]).RoY) {
                MF = RedMoveCheck(x, y);
            } else {
                MF = YellowMoveCheck(x, y);
            }
        } else {
            boolean R = RedMoveCheck(x, y);
            boolean Y = YellowMoveCheck(x, y);
            MF = (R || Y);
        }
        return MF;
    }

    void Crown() {
        for (int i = 0; i < 8; i++) {
            if ((this.Tiles[i][0]).EoP
                    && !(this.Tiles[i][0]).RoY
                    && !(this.Tiles[i][0]).KoS) {
                (this.Tiles[i][0]).KoS = true;
                this.Tiles[i][0].remove((this.Tiles[i][0]).YS);
                this.Tiles[i][0].add((this.Tiles[i][0]).YK);
            }

            if ((this.Tiles[i][7]).EoP
                    && (this.Tiles[i][7]).RoY
                    && !(this.Tiles[i][7]).KoS) {
                (this.Tiles[i][7]).KoS = true;
                this.Tiles[i][7].remove((this.Tiles[i][7]).RS);
                this.Tiles[i][7].add((this.Tiles[i][7]).RK);
            }
        }
    }

    boolean RedKillCheck(int x, int y) {
        boolean KF = false;
        if (x + 2 < 8 && y + 2 < 8
                && (this.Tiles[x + 1][y + 1]).EoP
                && !(this.Tiles[x + 1][y + 1]).RoY
                && !(this.Tiles[x + 2][y + 2]).EoP) {
            this.Tiles[x + 2][y + 2].setEnabled(true);
            this.Tiles[x + 2][y + 2].setBorder(this.Active);
            KF = true;
        }

        if (x - 2 > -1 && y + 2 < 8
                && (this.Tiles[x - 1][y + 1]).EoP
                && !(this.Tiles[x - 1][y + 1]).RoY
                && !(this.Tiles[x - 2][y + 2]).EoP) {
            this.Tiles[x - 2][y + 2].setEnabled(true);
            this.Tiles[x - 2][y + 2].setBorder(this.Active);
            KF = true;
        }

        return KF;
    }

    boolean YellowKillCheck(int x, int y) {
        boolean KF = false;
        if (x - 2 > -1 && y - 2 > -1
                && (this.Tiles[x - 1][y - 1]).EoP
                && (this.Tiles[x - 1][y - 1]).RoY
                && !(this.Tiles[x - 2][y - 2]).EoP) {
            this.Tiles[x - 2][y - 2].setEnabled(true);
            this.Tiles[x - 2][y - 2].setBorder(this.Active);
            KF = true;
        }

        if (x + 2 < 8 && y - 2 > -1
                && (this.Tiles[x + 1][y - 1]).EoP
                && (this.Tiles[x + 1][y - 1]).RoY
                && !(this.Tiles[x + 2][y - 2]).EoP) {
            this.Tiles[x + 2][y - 2].setEnabled(true);
            this.Tiles[x + 2][y - 2].setBorder(this.Active);
            KF = true;
        }

        return KF;
    }

    Boolean KingKillCheck(int x, int y) {
        boolean KF = false;
        if (x + 2 < 8 && y + 2 < 8
                && (this.Tiles[x + 1][y + 1]).EoP
                && (this.Tiles[x + 1][y + 1]).RoY != (this.Tiles[x][y]).RoY
                && !(this.Tiles[x + 2][y + 2]).EoP) {
            this.Tiles[x + 2][y + 2].setEnabled(true);
            this.Tiles[x + 2][y + 2].setBorder(this.Active);
            KF = true;
        }

        if (x - 2 > -1 && y + 2 < 8
                && (this.Tiles[x - 1][y + 1]).EoP
                && (this.Tiles[x - 1][y + 1]).RoY != (this.Tiles[x][y]).RoY
                && !(this.Tiles[x - 2][y + 2]).EoP) {
            this.Tiles[x - 2][y + 2].setEnabled(true);
            this.Tiles[x - 2][y + 2].setBorder(this.Active);
            KF = true;
        }

        if (x - 2 > -1 && y - 2 > -1
                && (this.Tiles[x - 1][y - 1]).EoP
                && (this.Tiles[x - 1][y - 1]).RoY != (this.Tiles[x][y]).RoY
                && !(this.Tiles[x - 2][y - 2]).EoP) {
            this.Tiles[x - 2][y - 2].setEnabled(true);
            this.Tiles[x - 2][y - 2].setBorder(this.Active);
            KF = true;
        }

        if (x + 2 < 8 && y - 2 > -1
                && (this.Tiles[x + 1][y - 1]).EoP
                && (this.Tiles[x + 1][y - 1]).RoY != (this.Tiles[x][y]).RoY
                && !(this.Tiles[x + 2][y - 2]).EoP) {
            this.Tiles[x + 2][y - 2].setEnabled(true);
            this.Tiles[x + 2][y - 2].setBorder(this.Active);
            KF = true;
        }

        return KF;
    }

    boolean KillCheck(int x, int y) {
        boolean KF = false;
        if (!(this.Tiles[x][y]).KoS) {
            if ((this.Tiles[x][y]).RoY) {
                KF = RedKillCheck(x, y);
            } else {
                KF = YellowKillCheck(x, y);
            }
        } else {
            KF = KingKillCheck(x, y);
        }
        return KF;
    }

    void Kill(int x, int y) {
        if ((this.Tiles[x][y]).RoY) {
            if ((this.Tiles[x][y]).KoS) {
                this.Tiles[x][y].remove((this.Tiles[x][y]).RK);
            } else {
                this.Tiles[x][y].remove((this.Tiles[x][y]).RS);
            }

        } else if ((this.Tiles[x][y]).KoS) {
            this.Tiles[x][y].remove((this.Tiles[x][y]).YK);
        } else {
            this.Tiles[x][y].remove((this.Tiles[x][y]).YS);
        }

        (this.Tiles[x][y]).EoP = false;
    }

    void Move(int prex, int prey, int X, int Y) {
        (this.Tiles[prex][prey]).EoP = false;
        (this.Tiles[X][Y]).EoP = true;
        (this.Tiles[X][Y]).RoY = (this.Tiles[prex][prey]).RoY;
        (this.Tiles[X][Y]).KoS = (this.Tiles[prex][prey]).KoS;

        if ((this.Tiles[prex][prey]).RoY) {
            if ((this.Tiles[prex][prey]).KoS) {
                this.Tiles[prex][prey].remove((this.Tiles[prex][prey]).RK);
                this.Tiles[X][Y].add((this.Tiles[X][Y]).RK);
            } else {
                this.Tiles[prex][prey].remove((this.Tiles[prex][prey]).RS);
                this.Tiles[X][Y].add((this.Tiles[X][Y]).RS);
            }

        } else if ((this.Tiles[prex][prey]).KoS) {
            this.Tiles[prex][prey].remove((this.Tiles[prex][prey]).YK);
            this.Tiles[X][Y].add((this.Tiles[X][Y]).YK);
        } else {
            this.Tiles[prex][prey].remove((this.Tiles[prex][prey]).YS);
            this.Tiles[X][Y].add((this.Tiles[X][Y]).YS);
        }
    }

    void CLOSE() {
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.prex = this.X;
        this.prey = this.Y;
        this.preroy = this.RoY;
        this.prekos = this.KoS;
        Tile temp = (Tile) e.getSource();
        this.X = temp.X / 100;
        this.Y = temp.Y / 100;
        this.RoY = temp.RoY;
        this.KoS = temp.KoS;
        if (!this.KillStreak) {
            DeactivateAll();

            if (this.MovePhase) {
                if (e.getSource() != this.tempC) {

                    Move(this.prex, this.prey, this.X, this.Y);

                    if (Math.abs(this.X - this.prex) == 1) {
                        this.MovePhase = false;
                        ChangeTurn();
                        this.tempC = null;
                    } else {
                        Kill((this.X + this.prex) / 2, (this.Y + this.prey) / 2);
                        this.KillStreak = KillCheck(this.X, this.Y);
                        this.MovePhase = false;
                        this.tempC = null;
                        if (!this.KillStreak) {
                            ChangeTurn();
                        }
                    }

                } else {

                    if (this.RoY) {
                        ActivateRed();
                    } else {
                        ActivateYellow();
                    }
                    this.X = 7;
                    this.Y = 0;
                    this.RoY = false;
                    this.tempC = null;
                    this.MovePhase = false;
                }
            } else {
                MoveCheck(this.X, this.Y);
                KillCheck(this.X, this.Y);
                this.tempC = (Tile) e.getSource();
                this.MovePhase = true;
            }

            repaint();
        } else {

            Move(this.prex, this.prey, this.X, this.Y);

            Kill((this.X + this.prex) / 2, (this.Y + this.prey) / 2);
            this.KillStreak = KillCheck(this.X, this.Y);
            if (!this.KillStreak) {
                ChangeTurn();
            }
        }

        repaint();
    }
}
