package dev.pong.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Object
{
   protected int width; // size
   protected int height;
   protected float x; // position
   protected float y;
   protected float speed;

   public Object(float x, float y, int width, int height)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

   public abstract void tick();

   public abstract void render(Graphics g);

   public abstract Rectangle getBounds();

}
