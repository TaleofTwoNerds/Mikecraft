package mikecraft;

import static mikecraft.MainGame.*;
import levels.World;
import mikecraft.MainGame.State;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Gravity extends Player {
	
	public static boolean emeraldOne = true, emeraldTwo = true, 
			emeraldThree = true, emeraldFour = true, emeraldFive = true, 
			emeraldSix = true, emeraldSeven = true;
	public static int score = 0;
	
	public static void detection(double x, double y, double width)
	{
		double yBottom = y-BlockSize * .25;
		if(player.getY() <= y && player.getY() >= yBottom && player.getX() >= x-SteveX && player.getX() <= x+width+SteveX)
		{
			if(player.getDY() <= 0 && player.getY() >= yBottom)
			{
				dy = 0;
				Player.y = y;
				movement();
			}
		}
	}
	
	public static boolean detectionTF(double x, double y, double width)
	{
		boolean truth = false;
		double yBottom = y-BlockSize * .25;
		if(player.getY() <= y && player.getY() >= yBottom && player.getX() >= x-SteveX && player.getX() <= x+width+SteveX)
		{
			if(player.getDY() <= 0 && player.getY() >= yBottom)
			{
				dy = 0;
				Player.y = y;
				movement();
				truth = true;	
			} else {
				truth = false;
			}
		}
		return truth;
	}
	
	public static void movement()
	{
		if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			dx = dx * 0.9;
		} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			dx = dx * 0.9;
		} if(jumpPressed && !jumpWasPressed){
			if (Char == 1 || Char == 2) {dy = Height / 50;}
			if (Char == 3){dy = Height / 35;}
		}
		if (x <= SteveX)
		{
			if (dx <= 0)
			{
				dx = 0;
			}
			x = SteveX;
		}
	}
	
	public static void endLogic(double bottom)
	{
		if (y <= bottom)
		{
			level = 1;
			state = State.MAIN_MENU;
			Player.x = 100;
			Player.y = BlockSize * 2;
		}
	}
	
	public static void logic()
	{
		if (y >= 0){
			if (state == State.GAME)
			{
				x+=dx;
				y+=dy;
				dy -= .4;
				World.chooseLevel();
				if(Keyboard.isKeyDown(Keyboard.KEY_A)  || Keyboard.isKeyDown(Keyboard.KEY_LEFT))
				{
					dx = -Width / 100;
				} if(Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
				{
					dx = Width / 100;
				}
				jumpWasPressed = jumpPressed;
				jumpPressed = Keyboard.isKeyDown(Keyboard.KEY_SPACE) || Keyboard.isKeyDown(Keyboard.KEY_UP);		
			}
		}
	}
}
