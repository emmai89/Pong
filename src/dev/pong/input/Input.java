package dev.pong.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener
{

   private boolean[] keys;
   public static boolean up_1, down_1, up_2, down_2, esc;

   public Input()
   {
      keys = new boolean[256];
   }

   public void tick()
   {
      up_2 = keys[KeyEvent.VK_UP];
      down_2 = keys[KeyEvent.VK_DOWN];
      up_1 = keys[KeyEvent.VK_W];
      down_1 = keys[KeyEvent.VK_S];
      esc = keys[KeyEvent.VK_ESCAPE];

      if (esc)
      {
         System.exit(1);
      }
   }

   @Override
   public void keyTyped(KeyEvent e)
   {
      if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
      {
         keys[e.getKeyCode()] = true;
      }
   }

   @Override
   public void keyPressed(KeyEvent e)
   {
      keys[e.getKeyCode()] = true;
   }

   @Override
   public void keyReleased(KeyEvent e)
   {
      keys[e.getKeyCode()] = false;
   }

}
