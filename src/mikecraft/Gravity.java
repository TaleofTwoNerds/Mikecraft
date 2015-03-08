package mikecraft;

import static mikecraft.MainGame.*;
import level.World;
import mikecraft.MainGame.State;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Gravity extends Player {
	
	public static boolean enemyHit,enemyWasHit = false,emeraldOne = true, emeraldTwo = true, 
			emeraldThree = true, emeraldFour = true, emeraldFive = true, 
			emeraldSix = true, emeraldSeven = true;
	public static int score = 0;
	
	public static void detection(double x, double y, double width, double height)
	{
		double yBottom = y-blockSize * .25, yTop = y+blockSize*.25;
		if(player.getY() <= y && player.getY() >= yBottom && player.getX() >= x-SteveX && player.getX() <= x+width+SteveX)
		{
			if(player.getDY() <= 0 && player.getY() >= yBottom)
			{
				dy = 0;
				player.y = y;
				movement();
			}
		}
		if(enemy.getY() <= y && enemy.getY() >= yBottom && enemy.getX() >= x-SteveX && enemy.getX() <= x+width+SteveX)
		{
			if(enemy.getDY() <= 0 && enemy.getY() >= yBottom)
			{
				enemy.dy = 0;
				enemy.y = y;
				eMovement(enemy.getBoundLeft(), enemy.getBoundRight());
			}
		}
	}
	
	public static boolean detectionTF(double x, double y, double width)
	{
		boolean truth = false;
		double yBottom = y-blockSize * .25;
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
			if (Char == 1 || Char == 2) {dy = Height / 45;}
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
	public static void eMovement(double x,double x2)
	{
		if (enemy.x<=x+SteveX){
			enemy.dx = Width /200;
			enemy.x = x+SteveX;
		} if(enemy.x>=x2-SteveX){
			enemy.dx = -Width/200;
			enemy.x = x2-SteveX;
		}
		if (enemy.x <= SteveX)
		{
			if (enemy.dx <= 0)
			{
				enemy.dx = 0;
			}
			enemy.x = SteveX;
		}
	}
	
	public static void endLogic(double bottom)
	{
		if (y <= bottom || (player.x >= enemy.x - SteveX * 2 && x <= enemy.x + SteveX * 2 && y <= enemy.y + blockSize / 2 && dy >= -1))
		{
			if (MainGame.lives >= 1)
			{
				lives = lives - 1;
			} else {
				level = 0;
				lives = 3;
			}
			state = State.STAGE_SWAP;
			Player.x = 100;
			Player.y = blockSize * 2;
		}
		if(player.x >= enemy.x - SteveX * 2 && x <= enemy.x + SteveX * 2 && y <= enemy.y + blockSize / 2 && dy <= 0 && enemy.toDraw)
		{
			enemyHit = true;
			if(enemyHit && !enemyWasHit)
			{
				player.dy = 0;
			} else {
				player.dy = -4;
			}
			enemy.setVisable(false);
			enemy.setPos(0,0);
			score++;
			score++;
			score++;
			enemyWasHit = true;
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
	public static void enemyLogic()
	{
		if (enemy.y >= 0){
			if (state == State.GAME)
			{
				enemy.x+=enemy.dx;
				enemy.y+=enemy.dy;
				enemy.dy -= .4;
				if(Keyboard.isKeyDown(Keyboard.KEY_F))
				{
					enemy.dx = -Width / 100;
				} if(Keyboard.isKeyDown(Keyboard.KEY_H))
				{
					enemy.dx = Width / 100;
				}
				enemy.jumpWasPressed = enemy.jumpPressed;
				enemy.jumpPressed = Keyboard.isKeyDown(Keyboard.KEY_T);		
			}
		}
	}
}
