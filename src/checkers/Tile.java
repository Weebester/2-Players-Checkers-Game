/*    */ package checkers;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.LayoutManager;
/*    */ import javax.swing.JButton;
/*    */ 
/*    */ public class Tile
/*    */   extends JButton
/*    */ {
/*    */   int X;
/*    */   int Y;
/*    */   boolean EoP = false;
/*    */   boolean RoY = true;
/*    */   boolean KoS = false;
/* 15 */   RedKing RK = new RedKing();
/* 16 */   YellowKing YK = new YellowKing();
/* 17 */   RedSoldier RS = new RedSoldier();
/* 18 */   YellowSoldier YS = new YellowSoldier();
/*    */   Tile(int x, int y, Color c) {
/* 20 */     this.X = x;
/* 21 */     this.Y = y;
/* 22 */     setLocation(this.X, this.Y);
/* 23 */     setLayout((LayoutManager)null);
/* 24 */     setBackground(c);
/* 25 */     setSize(100, 100);
/*    */   }
/*    */ }


/* Location:              D:\Games\MyCheckers\Checkers.jar!\checkers\Tile.class
 * Java compiler version: 19 (63.0)
 * JD-Core Version:       1.1.3
 */