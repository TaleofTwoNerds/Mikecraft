package com.totn.level;

import static com.totn.mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import com.totn.audio.MakeSound;
import com.totn.mikecraft.*;

public class WorldOneTwo extends Physics
{
	public static boolean spawnEmerald = false;
	public static float decell = (float) 0.8;
	public static World.Block back, 
			bridge[] = new World.Block[4], stone[] = new World.Block[4];
	public static World.Ground ground[] = new World.Ground[4];
	public static World.Hill hill[] = new World.Hill[10];
	public static World.FullBlock brick[] = new World.FullBlock[10], gold[] = new World.FullBlock[10];
    
    public static void main()
    {
    	drawBackground();
    	variableBlocks();
		gravitation();
		render();
    }
    
	public static void drawBackground() 
	{		
		enemy.setBounds(Width * 5 / 2, Width * 4 - blockSize * 2);

		back = new World.Block(Sky,0, 0, -Height * 2, Width * 8);
		ground[1] = new World.Ground(Dirt,0, blockSize * 2, blockSize * 2, Width * 2 - blockSize * 2);
		ground[2] = new World.Ground(Dirt, Width * 2 + blockSize * 2, blockSize * 2, blockSize * 2, Width * 2 - blockSize * 4);
		ground[3] = new World.Ground(Dirt, Width * 4 + blockSize * 6, blockSize * 2, blockSize * 2, Width * 3);
		hill[1] = new World.Hill(Grass, Width * 2 + blockSize * 9, blockSize * 3, blockSize, blockSize * 5);
		hill[2] = new World.Hill(Grass, Width * 3, blockSize * 4, blockSize, blockSize * 4);
		hill[3] = new World.Hill(Grass, Width * 3 + blockSize, blockSize * 5, blockSize, blockSize * 3);
		hill[4] = new World.Hill(Grass, Width * 3 + blockSize * 2, blockSize * 6, blockSize, blockSize * 2);
		
		bridge[1] = new World.Block(Planks_oak, Width * 3 + blockSize * 8, blockSize * 7, blockSize, blockSize * 4);
		bridge[2] = new World.Block(Planks_oak, Width * 4 + blockSize * 8, blockSize * 7, blockSize, blockSize * 4);
        
        stone[1] = new World.Block(Stone, Width * 3 + blockSize * 4, blockSize * 6, blockSize * 4, blockSize);
	}
	
	public static void variableBlocks()
	{
		gold[1] = new World.FullBlock(Gold, Width + blockSize, blockSize * 6, blockSize, blockSize);
		
		brick[1] = new World.FullBlock(Brick, blockSize * 7, blockSize * 6, blockSize, blockSize);
		brick[2] = new World.FullBlock(Brick, blockSize * 10, blockSize * 6, blockSize, blockSize);
		brick[3] = new World.FullBlock(Brick, Width + blockSize * 2, blockSize * 6, blockSize, blockSize);
		brick[4] = new World.FullBlock(Brick, Width + blockSize * 5, blockSize * 6, blockSize, blockSize);
	}
	
	static void render() 
	{
        glClear(GL_COLOR_BUFFER_BIT);
        back.draw();
		brick[1].draw();
		brick[2].draw();
		brick[3].draw();
		brick[4].draw();
        gold[1].draw();
        stone[1].draw();
        ground[1].draw();
        ground[2].draw();
        ground[3].draw();
        hill[1].draw();
        hill[2].draw();
        hill[3].draw();
        hill[4].draw();
		bridge[1].draw();
		bridge[2].draw();
        
        fontRender();
    }
	
	private static void fontRender()
	{
		MainGame.fontDrawString(font3, blockSize, Height / 4 -  32, "You're not bad...");
		MainGame.fontDrawString(font3, blockSize, Height / 4, "Let's turn it up a notch");
		MainGame.fontDrawString(font3, Width * 5, Height - blockSize * 4, "Missed it");
	}
	
	static void logic(int delta)
	{
        emerald[1].update(delta);
	}
	public static void gravitation()
	{
		endLogic(0);
//		if (x >= Width - BlockSize * 2 && player.getX()<= Width - BlockSize * 1 && player.getY() >= BlockSize * 2.3 && player.getY() <= BlockSize * 2.4)
//		{
//			dy = -5;
//		} else if (x >= Width +  BlockSize  * 0 && player.getX()<= Width + BlockSize * 3 && player.getY() >= BlockSize * 2.3 && player.getY() <= BlockSize * 2.4)
//		{
//			dy = -5;
//		} else if (x >= Width + BlockSize * 4 && player.getX()<= Width + BlockSize * 5 && player.getY() >= BlockSize * 2.3 && player.getY() <= BlockSize * 2.4)
//		{
//			dy = -5;
//		}
		
		if ( player.getX()>= Width * 7)
		{
			level = 3;
			MakeSound.clockTown.stop();
			MakeSound.courseClear.play();

			state = GameCurrentState.STAGE_SWAP;
			player.setX(100);
			player.setY(blockSize * 2);
			player.setDX(0);
			player.setDY(0);
			enemy.setLocation(Width * 4, blockSize * 2);
			enemy.setVisable(true);
		}
	}
}
