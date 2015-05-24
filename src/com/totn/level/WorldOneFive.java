package com.totn.level;

import static com.totn.mikecraft.MainGame.*;

import org.newdawn.slick.Color;

import com.totn.mikecraft.Gravity;
import com.totn.mikecraft.MakeSound;
import com.totn.mikecraft.MainGame.State;

public class WorldOneFive extends Gravity 
{
	public static float decell = (float) 0.8;
	private static World.Back dirtOne,dirtTwo;
	private static World.Block bridge[] = new World.Block[10],island[] = new World.Block[10],back;
	private static World.Ground ground[] = new World.Ground[5];
	
	public static void main()
	{
		drawBackground();
		gravitation();	
		render();
	}

	private static void drawBackground() 
	{
		enemy.setBounds(Width * 5 + Width / 2, Width * 6 + Width / 2);

		back = new World.Block(Sky,0, 0, -Height * 3, Width * 10);
		dirtOne = new World.Back(Stone,Width / 2 + blockSize * 4, blockSize * 4, blockSize * 2, blockSize * 3);
		dirtTwo = new World.Back(Stone,Width / 2 + blockSize * 6, blockSize * 5, blockSize,blockSize );
		
		ground[1] = new World.Ground(Dirt,0, blockSize * 2, blockSize * 2, Width / 2);
		ground[2] = new World.Ground(Dirt,Width * 5, blockSize * 2, blockSize * 2, Width * 5);

		bridge[1] = new World.Block(Planks_oak, blockSize * 5, blockSize * 4, blockSize, blockSize * 3);
		bridge[2] = new World.Block(Planks_oak, blockSize * 7, blockSize * 6, blockSize, blockSize * 3);
		bridge[3] = new World.Block(Planks_oak, blockSize * 9, blockSize * 8, blockSize, blockSize * 3);
	
		island[1] = new World.Block(Grass,Width + blockSize, blockSize * 8, blockSize, blockSize * 3);
		island[2] = new World.Block(Grass,Width + blockSize * 7, blockSize * 7, blockSize, blockSize * 3);
		island[3] = new World.Block(Grass,Width * 2 + blockSize * 4, blockSize * 8, blockSize, blockSize * 3);
		island[4] = new World.Block(Grass,Width * 2 + blockSize * 9, blockSize * 9, blockSize, blockSize * 3);
		island[5] = new World.Block(Grass,Width * 3 + blockSize * 4, blockSize * 10, blockSize, blockSize * 3);
		island[6] = new World.Block(Grass,Width * 3 + blockSize * 9, blockSize * 8, blockSize, blockSize * 3);
		island[7] = new World.Block(Grass,Width * 4 + blockSize * 5, blockSize * 9, blockSize, blockSize * 3);
		island[8] = new World.Block(Grass,Width * 5 + blockSize * 3, blockSize * 7, blockSize, blockSize * 3);
		}

	private static void render() 
	{
		back.draw();
		fontRender();
		ground[1].draw();
		ground[2].draw();
		bridge[1].draw();
		bridge[2].draw();
		bridge[3].draw();
		island[1].draw();
		island[2].draw();
		island[3].draw();
		island[4].draw();
		island[5].draw();
		island[6].draw();
		island[7].draw();
		island[8].draw();
	}
	
	@SuppressWarnings("deprecation")
	private static void fontRender()
	{		
		font3.drawString(Width / 4, Height / 4 -  32, "Going up?", Color.white);
		font3.drawString(Width * 8 + blockSize * 6, blockSize, "Written by");
		font3.drawString(Width * 8 + blockSize * 6, blockSize * 2, "Blaine Harper");
	}
	
	public static void logic(int delta) 
	{
		
	}

	private static void gravitation() 
	{
		endLogic(0);
		if ( x >= Width * 9)
		{
			MakeSound.playSound("castle_clear.wav");
			dx = 0;
			level = 0;
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
	