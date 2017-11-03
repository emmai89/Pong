package dev.pong;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.pong.display.Display;
import dev.pong.input.Input;
import dev.pong.objects.Ball;
import dev.pong.objects.Object;
import dev.pong.objects.Player;

public class Game implements Runnable
{
	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//objects
	Object player1, player2, ball;
	
	
	//Input
	private Input input;
	
	
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
		
		player1 = new Player(10, 300, 20, 100, 1);
		player2 = new Player(670, 300, 20, 100, 2);
		ball = new Ball(width/2, height/2, 10, 10, this);
	}
	
	private void tick()
	{
		input.tick();
		
		ball.tick();
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
		//clear screen
		g.clearRect(0, 0, width, height);
		// Draw here!	
		
		player1.render(g);
		player2.render(g);;
		ball.render(g);
		//End dreawing!
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
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public Object getPlayer1()
	{
		return player1;
	}
	
	public Object getPlayer2()
	{
		return player2;
	}
}
