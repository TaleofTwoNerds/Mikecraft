package com.totn.level;

import static com.totn.mikecraft.MainGame.*;

import org.newdawn.slick.Color;

import com.totn.mikecraft.Gravity;
import com.totn.mikecraft.MakeSound;
import com.totn.mikecraft.MainGame.State;

public class WorldOneFour extends Gravity 
{
	public static float decell = (float) 0.8;
	private static World.Back dirtOne,dirtTwo;
	private static World.Block bridge[] = new World.Block[10],back;
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
		dirtOne = new World.Back(Stone,Width / 2 + blockSize * 4, blockSize * 4, blockSize * 2, blockSize * 3);
		dirtTwo = new World.Back(Stone,Width / 2 + blockSize * 6, blockSize * 5, blockSize,blockSize );
		
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
	
	@SuppressWarnings("deprecation")
	private static void fontRender()
	{
		font3.drawString(Width / 4, Height / 4 -  32, "You know what? I have a social life and things that I need to do on a daily basis. My life is not completely devoted to you. So yeah. I have't finished this level yet. Get over it.", Color.white);
	}
	
	public static void logic(int delta) 
	{
		
	}

	private static void gravitation() 
	{
		endLogic(0);
		if ( x >= Width * 9)
		{
			MakeSound.playSound("course_clear.wav");
			dx = 0;
			level = 5;
			state = State.STAGE_SWAP;
			Gravity.emeraldOne = true;
			Gravity.emeraldTwo = true;
			Gravity.emeraldThree = true;
			Gravity.emeraldFour = true;
			Gravity.emeraldFive = true;
			Gravity.emeraldSix = true;
			Gravity.emeraldSeven = true;
			player.x= 100;
			player.y = blockSize * 2;
			player.dx = 0;
			player.dy = 0;
		}
	}
}		
	