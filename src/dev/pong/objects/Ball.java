package dev.pong.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import dev.pong.Game;

public class Ball extends Object
{

	 private final float startSpeed = 3;
	
   private float xMove, yMove;
   private float speed;
   private Game game;
   private boolean out;
   private Player player1, player2;

   public Ball(float x, float y, int width, int height, Game game)
   {
      super(x, y, width, height);

      speed = startSpeed;
      this.xMove = speed;
      this.yMove = speed;
      this.game = game;
      this.out = false;
      this.player1 = game.getPlayer1();
      this.player2 = game.getPlayer2();
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
    	  if(Math.abs(xMove) < 10)
    	  {
 	         xMove = (float)-(xMove*1.1);
    	  }
    	  else
    	  {
    		  xMove = -xMove;
    	  }
      }

      if(x <= 0)
      {
         out = true;
         player2.setScore(player2.getScore() + 1);
      }
      if(x >= game.getWidth())
      {
         out = true;
         player1.setScore(player1.getScore() + 1);
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
   
   public void setOut(boolean out)
   {
	   this.out = out;
   }

   public Player getPlayer1()
   {
      return player1;
   }

   public Player getPlayer2()
   {
      return player2;
   }

   public void setGame(Game game)
   {
	   this.game = game;
	   this.player1 = game.getPlayer1();
       this.player2 = game.getPlayer2();
       this.xMove = speed;
       this.yMove = speed;
   }
   
   public void reset(int x, int y)
   {
	   this.x = x;
	   this.y = y;
	   speed = startSpeed;
	      this.xMove = speed;
	      this.yMove = speed;
   }
   
   public float getXmove()
   {
	   return xMove;
   }
}
