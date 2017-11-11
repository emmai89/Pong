package dev.pong.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dev.pong.objects.Ball;
import dev.pong.objects.Player;

public class UI
{
   private Player player1, player2;

   public UI(Ball ball)
   {
      this.player1 = ball.getPlayer1();
      this.player2 = ball.getPlayer2();
   }

   public void tick()
   {

   }

   public void render(Graphics g)
   {
      int score1 = player1.getScore();
      int score2 = player2.getScore();

      g.setColor(Color.pink);
      g.setFont(new Font("ComicSans", Font.PLAIN, 30));
      scoreDisplay(score1, g, 200);
      scoreDisplay(score2, g, 500);

   }

   public void scoreDisplay(int score, Graphics g, int x)
   {
      g.drawString("" +score, x, 50);
   }

}
