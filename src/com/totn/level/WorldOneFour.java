package com.totn.level;

import static com.totn.mikecraft.MainGame.*;

import com.totn.audio.MakeSound;
import com.totn.mikecraft.MainGame;
import com.totn.mikecraft.Physics;
import com.totn.mikecraft.MainGame.State;

public class WorldOneFour extends Physics 
{
	public static float decell = (float) 0.8;
	private static World.Block back;
	private static World.Ground ground[] = new World.Ground[5];
	
	public static void main()
	{
		drawBackground();
		gravitation();	
		render();
	}

	private static void drawBackground() 
	{
		enemy.setBounds(Width * 3, Width * 6);
		
		back = new World.Block(Sky,0, 0, -Height * 2, Width * 10);
		
		ground[1] = new World.Ground(Dirt,0, blockSize * 2, blockSize * 2, Width * 3);
		ground[2] = new World.Ground(Dirt, Width * 3 + blockSize * 4, blockSize * 2, blockSize * 2, Width * 7);
		}

	private static void render() 
	{
		back.draw();
		ground[1].draw();
		ground[2].draw();
		fontRender();
	}
	
	private static void fontRender()
	{
		MainGame.fontDrawString(font3 ,Width / 4, Height / 4 -  32, "You know what? I have a social life and things that I need to do on a daily basis. My life is not completely devoted to you. So yeah. I have't finished this level yet. Get over it.");
	}
	
	public static void logic(int delta) 
	{
		
	}

	private static void gravitation() 
	{
		endLogic(0);
		if ( player.getX() >= Width * 9)
		{
			level = 5;
			MakeSound.clockTown.stop();
			MakeSound.courseClear.play();

			state = State.STAGE_SWAP;
			Physics.emeraldOne = true;
			Physics.emeraldTwo = true;
			Physics.emeraldThree = true;
			Physics.emeraldFour = true;
			Physics.emeraldFive = true;
			Physics.emeraldSix = true;
			Physics.emeraldSeven = true;
			player.setLocation(100, blockSize * 2);
			player.setDX(0);
			player.setDY(0);	
		}
	}
}		
	