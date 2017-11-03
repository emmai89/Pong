package dev.pong.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import dev.pong.Game;

public class Ball extends Object
{

   private float xMove, yMove;
   private float speed;
   private Game game;
   private boolean out;

   public Ball(float x, float y, int width, int height, Game game)
   {
      super(x, y, width, height);

      speed = 3;
      this.xMove = speed;
      this.yMove = speed;
      this.game = game;
      this.out = false;
   }

   @Override
   public void tick()
   {
      move();
   }

   private void move()
   {
      if(y <= 0)
      {
         yMove = -yMove;
      }
      if(y >= (game.getHeight() - height))
      {
         yMove = -yMove;
      }
      if(collision())
      {
         xMove = (float) -(xMove*1.1);
      }

      if(x <= 0 || x >= game.getWidth())
      {
         out = true;
      }

      y += yMove;
      x += xMove;

   }

   private boolean collision()
   {
      boolean collide = false;
      if(getBounds().intersects(game.getPlayer1().getBounds()) ||
         getBounds().intersects(game.getPlayer2().getBounds()))
      {
         collide = true;
      }
      return collide;
   }

   @Override
   public void render(Graphics g)
   {
      g.setColor(Color.RED);
      g.fillRect((int)x, (int)y, width, height);

      if(x <= 0)
      {
         g.drawString("player 2 wins!!", 300, 300);
      }
      else if( x >= 700)
      {
         g.drawString("player 1 wins!!", 300, 300);
      }
   }

   @Override
   public Rectangle getBounds()
   {
      return new Rectangle((int)x, (int)y, width, height);
   }

   public boolean getOut()
   {
      return out;
   }

}
