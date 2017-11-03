package dev.pong.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import dev.pong.input.Input;

public class Player extends Object{

	float yMove;
	int ID;
	
	public Player(float x, float y, int width, int height, int ID) 
	{
		super(x ,y ,width,height);
		yMove = 0;
		this.ID = ID;
		speed = 5;
	}

	
	private void getInput()
	{
		yMove = 0;
		if(ID == 1)
		{
		  if(Input.down_1)
		  {
			  yMove = speed;
		  }
		  if(Input.up_1)
		  {
			  yMove = -speed;
		  }
		}
		else if(ID == 2)
		{
		  if(Input.down_2)
		  {
			  yMove = speed;
		  }
		  if(Input.up_2)
		  {
			  yMove = -speed;
		  }
		}
	}
	
	private void move()
	{
		if(yMove < 0 && y == 0)
		{
			y = 0;
		}
		else if(yMove > 0 && y == (525 - height))
		{
			y = 525 - height;
		}
		else
		{
			y += yMove;
		}
	}
	
	@Override
	public void tick() 
	{
		getInput();
		move();
	}

	@Override
	public void render(Graphics g) 
	{
		g.setColor(Color.BLACK);
		g.fillRect((int)x, (int)y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}
}
