package com.totn.level;

import static com.totn.mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;

import com.totn.entity.Player;
import com.totn.mikecraft.Gravity;
import com.totn.mikecraft.MainGame;
import com.totn.mikecraft.MakeSound;
import com.totn.mikecraft.MainGame.State;

public class WorldOneTwo extends Gravity
{
	public static boolean spawnEmerald = false;
	public static float decell = (float) 0.8;
	public static World.Emerald emerald[] = new World.Emerald[10];
	public static World.Block back, gold[] = new World.Block[10], 
			bridge[] = new World.Block[4], stone[] = new World.Block[4];
	public static World.Ground ground[] = new World.Ground[4];
	public static World.Hill hill[] = new World.Hill[10];
	public static World.Block brick[] = new World.Block[10];
	private static long lastFrame;

    private static long getTime() 
    {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    protected static int getDelta() 
    {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }
    
    public static void main()
    {
    	WorldOneTwo.drawBackground();
		WorldOneTwo.gravitation();
		WorldOneTwo.render();
		WorldOneTwo.logic(getDelta());
    }
    
	public static void drawBackground() 
	{		
		enemy.setBounds(Width * 5 / 2, Width * 4 - blockSize * 2);

		back = new World.Block(Sky,0, 0, -Height * 2, Width * 8);
		ground[1] = new World.Ground(Dirt,0, blockSize * 2, blockSize * 2, Width * 2 - blockSize * 2);
		ground[2] = new World.Ground(Dirt, Width * 2 + blockSize * 3, blockSize * 2, blockSize * 2, Width * 2 - blockSize * 4);
		ground[3] = new World.Ground(Dirt, Width * 4 + blockSize * 6, blockSize * 2, blockSize * 2, Width * 3);
		hill[1] = new World.Hill(Grass, Width * 2 + blockSize * 9, blockSize * 3, blockSize, blockSize * 5);
		hill[2] = new World.Hill(Grass, Width * 3, blockSize * 4, blockSize, blockSize * 4);
		hill[3] = new World.Hill(Grass, Width * 3 + blockSize, blockSize * 5, blockSize, blockSize * 3);
		hill[4] = new World.Hill(Grass, Width * 3 + blockSize * 2, blockSize * 6, blockSize, blockSize * 2);
		
		brick[1] = new World.Block(Brick, blockSize * 8, blockSize * 6, blockSize, blockSize);
		brick[2] = new World.Block(Brick, blockSize * 10, blockSize * 6, blockSize, blockSize);
		brick[3] = new World.Block(Brick, Width + blockSize * 2, blockSize * 6, blockSize, blockSize);
		brick[4] = new World.Block(Brick, Width + blockSize * 4, blockSize * 6, blockSize, blockSize);
		
		bridge[1] = new World.Block(Planks_oak, Width * 3 + blockSize * 8, blockSize * 7, blockSize, blockSize * 4);
		bridge[2] = new World.Block(Planks_oak, Width * 4 + blockSize * 8, blockSize * 7, blockSize, blockSize * 4);
        
		gold[1] = new World.Block(Gold, Width + blockSize, blockSize * 6, blockSize, blockSize);
        stone[1] = new World.Block(Stone, Width * 3 + blockSize * 4, blockSize * 6, blockSize * 4, blockSize);
        
        emerald[1] = new World.Emerald(Emerald, Width + blockSize, blockSize * 6 + blockSize / 6, blockSize, blockSize);
	}
	static void render() 
	{
        glClear(GL_COLOR_BUFFER_BIT);
        back.draw();
        if(Gravity.emeraldOne){emerald[1].draw();}
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
	
	@SuppressWarnings("deprecation")
	private static void fontRender()
	{
		font3.drawString(blockSize, Height / 4 -  32, "You're not bad...", Color.white);
		font3.drawString(blockSize, Height / 4, "Let's turn it up a notch", Color.white);
		font3.drawString(Width * 5, Height - blockSize * 4, "Missed it", Color.white);
	}
	
	static void logic(int delta)
	{
        emerald[1].update(delta);
	}
	public static void gravitation()
	{
		endLogic(0);
//		if (x >= Width - BlockSize * 2 && x <= Width - BlockSize * 1 && y >= BlockSize * 2.3 && y <= BlockSize * 2.4)
//		{
//			dy = -5;
//		} else if (x >= Width +  BlockSize  * 0 && x <= Width + BlockSize * 3 && y >= BlockSize * 2.3 && y <= BlockSize * 2.4)
//		{
//			dy = -5;
//		} else if (x >= Width + BlockSize * 4 && x <= Width + BlockSize * 5 && y >= BlockSize * 2.3 && y <= BlockSize * 2.4)
//		{
//			dy = -5;
//		}
		
		if ( x >= Width * 7)
		{
			MakeSound.playSound("course_clear.wav");
			dx = 0;
			level = 3;
			state = State.STAGE_SWAP;
			try 
			{
				MainGame.main(null);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Player.x= 100;
			Player.y = blockSize * 2;
			player.dx = 0;
			player.dy = 0;
		}
	}
}
