package dev.pong;

public class Launcher {

	public static void main(String[] args) 
	{
		Game game = new Game("Pong!", 700, 700/ 12 * 9);
		game.start();
	}

}
