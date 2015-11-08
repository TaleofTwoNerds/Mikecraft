package com.totn.mikecraft;

import static com.totn.mikecraft.MainGame.*;

import org.lwjgl.input.Keyboard;

import com.totn.audio.MakeSound;
import com.totn.entity.Token;
import com.totn.mikecraft.MainGame.State;

public class Physics 
{
	public static double decell = (double) blockSize / 70; 
	public static boolean emeraldOne = true, emeraldTwo = true, 
			emeraldThree = true, emeraldFour = true, emeraldFive = true, 
			emeraldSix = true, emeraldSeven = true,lifeLost=false,lifeWasLost=false;
	public static int score = 0;
	public static Token[] emerald = new Token[16];
	private static boolean dead = false, wasDead = false, toWait = true;
	
	public static void createEmeralds()
	{
		emerald[0] = new Token(Emerald, 0, 0, blockSize, blockSize);
	}
	
//	Collision detection
//	Called by the world class
	public static void detection(double x, double y, double width, double height)
	{
		double yBottom = y - blockSize * .25;
		if(!dead)
		{
				if(player.getY() <= y && player.getY() >= yBottom && player.getX() >= x-player.getWidth() / 4 && player.getX() <= x+width+player.getWidth() / 2)
				{
					if(player.getDY() <= 0 && player.getY() >= yBottom)
				{
					player.setDY(0);
					player.setY(y);
					player.setGround(true);
					movement();
					player.setGround(false);
				} else {
					player.setGround(false);
				}
			}
		}
		
		if(enemy.getY() <= y && enemy.getY() >= yBottom && enemy.getX() >= x-player.getWidth() / 4 && enemy.getX() <= x+width+player.getWidth() / 2)
		{
			if(enemy.getDY() <= 0 && enemy.getY() >= yBottom)
			{
				enemy.setDY(0);
				enemy.setY(y);
				eMovement(enemy.getBoundLeft(), enemy.getBoundRight());
			}
		}
		
		if(emerald[0].getY() <= y && emerald[0].getY() >= yBottom && emerald[0].getX() >= x-emerald[0].getWidth() / 4 && emerald[0].getX() <= x+width+emerald[0].getWidth() / 2)
		{
			if(emerald[0].getDY() <= 0 && emerald[0].getY() >= yBottom)
			{
				emerald[0].setDY(0);
				emerald[0].setY(y);
			}
		}
	}
//	Controls the movement of the player
	public static void movement()
	{			
		if (!Keyboard.isKeyDown(player.getSetting(3)))
		{
			player.setDX(player.getDX()*decell);
		} 
		if(!Keyboard.isKeyDown(player.getSetting(1)))
		{
			player.setDX(player.getDX()*decell);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)||Keyboard.isKeyDown(player.getSetting(0)))
		{
			player.jump();
		}
		
		if (player.getX() <= player.getWidth() / 2)
		{
			if (player.getDX() <= 0)
			{
				player.setDX(0);
			}
			player.setX(player.getWidth() / 2);
		}
	}
//	Controls the movement of the enemies
	public static void eMovement(double xLeft,double xRight)
	{
		if(enemy.isVisable())
		{
			
		}
		if(enemy.getX() >= xRight - enemy.getWidth()){
			enemy.setDX(-enemy.getSpeed());
			enemy.setX(xRight - enemy.getWidth());
		} else if (enemy.getX() <= xLeft + enemy.getWidth())
		{
			enemy.setDX(enemy.getSpeed());
			enemy.setX(xLeft + enemy.getWidth());
		} 
	}
//	Logic for the end of the level, or the end of the player's life
	public static void endLogic(double bottom)
	{
		if (player.getY() <= bottom || (player.getX() >= enemy.getX() - player.getWidth() && player.getX() <= enemy.getX() + player.getWidth() && player.getY() <= enemy.getY() + blockSize / 2 && player.getDY() >= -1))
		{
		//	Death loop
			dead = true;
		//	Stop enemy
			enemy.setDX(0);
			player.setDX(0);
			if(toWait)
			{
				MainGame.wait(.5);
				toWait = false;
			}
			player.jump(true);
			
			if(player.getY() <= 0 && dead)
			{
				wasDead = true;
			}
			
			if(wasDead)
			{
				if (MainGame.lives >= 1)
				{
					MakeSound.clearSounds();
					MakeSound.lostALife.play();
					lives = lives - 1;
				} else {
					MakeSound.clearSounds();
					MakeSound.gameOver.play();
					level = 0;
					lives = 3;
					score = 0;
				}
			//	Reset the level
				state = State.STAGE_SWAP;
				player.setX(100);
				player.setY(blockSize * 2);
				dead = false;
				wasDead = false;
				lifeWasLost=false;
				if(enemy.isVisable())
				{
					enemy.setDX(enemy.getSpeed());
				}
			}
		}
			
		if (enemy.getY() <= bottom)
		{
			enemy.setVisable(false);
			enemy.setLocation(0,0);
		}

		if(player.getX() >= enemy.getX() - player.getWidth() && player.getX() <= enemy.getX() + player.getWidth() && player.getY() <= enemy.getY() + blockSize / 2 && player.getDY() <= 0 && enemy.isVisable())
		{
			if(!dead && !wasDead)
			{
				player.jump(true);
				enemy.setVisable(false);
				enemy.setLocation(0,0);
				score+=3;
				MakeSound.stomp.play();
			}	
		}
	}
	
//	Logic for the player
	public static void logic()
	{
		if (player.getY() >= 0){
			if (state == State.GAME)
			{
				player.setX(player.getX()+player.getDX());
				player.setY(player.getY()+player.getDY());
				player.setDY(player.getDY() - (double) blockSize / 125);
				if(Keyboard.isKeyDown(player.getSetting(3)))
				{
					player.setDX(-player.getSpeed());
				} if(Keyboard.isKeyDown(player.getSetting(1)))
				{
					player.setDX(player.getSpeed());
				}
			}
		}
		
		if (enemy.getY() >= 0){
			if (state == State.GAME)
			{
				enemy.setX(enemy.getX() + enemy.getDX());
				enemy.setY(enemy.getY() + enemy.getDY());
				enemy.setDY(-((double) blockSize) / 125);

			}
		}
		emerald[0].setX(emerald[0].getX() + emerald[0].getDX());
		emerald[0].setY(emerald[0].getY() + emerald[0].getDY());
	}
}
