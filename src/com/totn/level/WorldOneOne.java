package com.totn.level;

import static com.totn.mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;

import com.totn.audio.MakeSound;
import com.totn.mikecraft.MainGame;
import com.totn.mikecraft.Physics;

public class WorldOneOne extends Physics
{
	public static float decell = (float) 0.8;
	public static World.Ground ground[] = new World.Ground[16];
	public static World.Back dirt[] = new World.Back[16], explosive, wheat;
	public static World.Hill hill[] = new World.Hill[16];
	public static World.Block back,bridge[] = new World.Block[16];
	private static int worldWidth = Width * 10;

	public static void main()
	{
		drawBackground();
		gravitation();	
		render();
	}
	
//	Test CRON II
	
	public static void drawBackground() 
	{	
		enemy.setBounds(Width * 4 / 2, Width * 7 / 2);

		back = new World.Block(Sky,0, 0, -Height * 2, worldWidth + Width);		
		wheat = new World.Back(Wheat7, Width * 3 - blockSize * 2, blockSize * 3, blockSize, blockSize * 4);
		explosive = new World.Back(Tnt, Width * 3 - blockSize * 2, blockSize * 3, blockSize, blockSize * 4);

		ground[1] = new World.Ground(Dirt, 0, blockSize * 2, blockSize * 2, Width * 2);
		ground[2] = new World.Ground(Dirt, Width * 2 + blockSize * 2, blockSize * 2, blockSize * 2, Width * 2 - blockSize * 4);
		ground[3] = new World.Ground(Dirt, Width * 4 + blockSize * 3, blockSize * 1, blockSize * 2, Width * 2);
		ground[4] = new World.Ground(Dirt, Width * 6 + blockSize * 3, blockSize * 2, blockSize * 2, Width * 5);

		hill[1] = new World.Hill(Grass, Width - blockSize, blockSize * 3, blockSize, blockSize * 3);
		hill[2] = new World.Hill(Grass, Width, blockSize * 4, blockSize, blockSize * 3);
		hill[3] = new World.Hill(Grass, Width - blockSize * 2, blockSize * 5, blockSize, blockSize * 4);
		
		dirt[1] = new World.Back(Dirt, Width - blockSize * 2, blockSize * 4, blockSize * 3, blockSize * 5);
		
		bridge[0] = new World.Block(Planks_oak, Width + blockSize * 5, blockSize * 4, blockSize, blockSize * 2);
		bridge[1] = new World.Block(Planks_oak, Width + blockSize * 8, blockSize * 5, blockSize, blockSize * 4);
		bridge[2] = new World.Block(Planks_oak, Width + blockSize * 9, blockSize * 2, blockSize, blockSize * 4);
		bridge[3] = new World.Block(Planks_oak, Width * 2 + blockSize * 4, blockSize * 6, blockSize, blockSize * 4);
		bridge[5] = new World.Block(Planks_oak, Width * 3 + blockSize, blockSize * 6, blockSize, blockSize * 3);
		bridge[4] = new World.Block(Planks_oak, Width * 3 + blockSize * 7, blockSize * 7, blockSize, Width / 2);
		bridge[6] = new World.Block(Planks_oak, Width * 3 + blockSize * 8, blockSize * 5, blockSize, blockSize * 4);
		bridge[7] = new World.Block(Planks_oak, Width * 4, blockSize * 6, blockSize, blockSize * 2);
		bridge[8] = new World.Block(Planks_oak, Width * 4 + blockSize * 2, blockSize * 6, blockSize, blockSize * 3);

		//title
		if (state == State.MAIN_MENU){
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			Title.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0, -1);
			glVertex2i(Width / 2 - 128, Height - (Height / 2 + 256)); // Upper-left
			glTexCoord2f(1, -1);
			glVertex2i(Width / 2 + 128, Height - (Height / 2 + 256)); // Upper-right
			glTexCoord2f(1, 0);
			glVertex2i(Width / 2 + 128, Height - (Height / 2 + 128)); // Bottom-right
			glTexCoord2f(0, 0);
			glVertex2i(Width / 2 - 128 , Height - (Height / 2 + 128)); // Bottom-left
			glEnd();
		}
	}
	
	static void render() 
	{
        glClear(GL_COLOR_BUFFER_BIT);  
        back.draw();  
        dirt[1].draw();
        hill[1].draw();
        hill[2].draw();
        hill[3].draw(); 
        ground[1].draw();
        ground[2].draw();
        ground[3].draw();
        ground[4].draw();
        explosive.draw();
        wheat.draw();
        for(int bridgeToDraw=0;bridgeToDraw<=8;bridgeToDraw++)
        {
        	if(!Keyboard.isKeyDown(2 + bridgeToDraw))
        	{
        		bridge[bridgeToDraw].draw();
        	}
        }
        
		fontRender();
    }
	
	private static void fontRender()
	{
		MainGame.fontDrawString(font3, Width / 4, Height / 4 -  32, "Welcome to");
		MainGame.fontDrawString(font3, Width / 4, Height / 4, "Mikecraft, William");
		MainGame.fontDrawString(font3, Width + blockSize * 3, Height - blockSize * 7, "Do you know why you're here?");
		MainGame.fontDrawString(font3, Width * 2 + blockSize * 8, Height - blockSize * 8, "My amusement ");
	}

	@SuppressWarnings("static-access")
//	basically the logic of the level. Name gravitation because it used to
//	be the collision detection method before I got smart.
	public static void gravitation() 
	{		
		endLogic(0);
		if (player.getX() >= worldWidth)
		{
			level = 2;
			MakeSound.clockTown.stop();
			MakeSound.courseClear.play();

			
			state = state.STAGE_SWAP;

			player.setX(100);
			player.setY(blockSize * 2);
			player.setDX(0);
			player.setDY(0);
			enemy.setVisable(true);
			enemy.setLocation(Width * 5 / 2, blockSize * 2);
		}
	}
}
