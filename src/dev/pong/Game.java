package dev.pong;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import dev.pong.display.Display;
import dev.pong.display.UI;
import dev.pong.input.Input;
import dev.pong.objects.Ball;
import dev.pong.objects.Player;

public class Game implements Runnable
{
   private UI ui;

   private Display display;
   private int width, height;
   public String title;

   private boolean running = false;
   private Thread thread;

   private BufferStrategy bs;
   private Graphics g;

// objects
   Player player1, player2, AI;
   Ball ball;

   // Input
   private Input input;

   private boolean reset;

   public Game(String title, int width, int height)
   {
      this.width = width;
      this.height = height;
      this.title = title;
      this.input = new Input();
   }

   private void init()
   {
      display = new Display(title, width, height);
      display.getFrame().addKeyListener(input);

      ball = new Ball(width/2, height/2, 10, 10, this);
      player1 = new Player(10, 300, 20, 100, 1, ball);
      /*AI = new Player(10, 300, 20, 100, 0, ball);*/
      player2 = new Player(670, 300, 20, 100, 0, ball);
      ball.setGame(this);

      ui = new UI(ball);
      reset = true;
      new Timer();
   }

   private void tick()
   {
      input.tick();

      ball.tick();
      if(ball.getOut())
      {
         ball.reset(width/2, height/2);
         ball.setOut(false);
         reset = true;
      }
      player1.tick();
      player2.tick();

   }

   private void render()
   {
      bs = display.getCanvas().getBufferStrategy();
      if(bs == null)
      {
         display.getCanvas().createBufferStrategy(3);
         return;
      }
      g = bs.getDrawGraphics();
      // clear screen
      g.clearRect(0, 0, width, height);
      // Draw here!
      ball.render(g);
      player1.render(g);
      player2.render(g);
      ui.render(g);

      if(reset)
      {
         reset = false;
         /* timer.scheduleWithFixedDelay(new TimerTask() {
             public void run() {
                try
                {
                   TimeUnit.SECONDS.sleep( 5 );
                   System.out.println("sleep");

                }
                catch( InterruptedException e )
                {
                   e.printStackTrace();
                }
             }

            }, 0, 100);*/

      }
      // End dreawing!
      bs.show();
      g.dispose();
   }

   public void run()
   {
      init();

      int fps = 60;
      double timePerTick = 1000000000 / fps;
      double delta = 0;
      long now;
      long lastTime = System.nanoTime();
      long timer = 0;
      int ticks = 0;

      while(running)
      {
         now = System.nanoTime();
         delta += (now - lastTime) / timePerTick;
         timer += now - lastTime;
         lastTime = now;

         if(delta >= 1)
         {
            tick();
            render();
            delta--;
            ticks++;
         }

         if(timer >= 1000000000)
         {
            System.out.println("Ticks and Frames: " +ticks);
            ticks = 0;
            timer = 0;
         }
      }

      stop();
   }

   public int getWidth()
   {
      return width;
   }

   public int getHeight()
   {
      return height;
   }

   public synchronized void start()
   {
      if (running)
      {
         return;
      }
      running = true;
      thread = new Thread(this);
      thread.start();
   }

   public synchronized void stop()
   {
      if(!running)
      {
         return;
      }
      running = false;
      try
      {
         thread.join();
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
   }

   public Player getPlayer1()
   {
      return player1;
   }

   public Player getPlayer2()
   {
      return player2;
   }

}
