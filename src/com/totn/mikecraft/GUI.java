package com.totn.mikecraft;

import static com.totn.mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import com.totn.mikecraft.MainGame.State;

public class GUI 
{
	private static void backObject(Texture t,int x,int y,int height,int width)
	{
		glPushMatrix();

		t.bind();

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glBegin(GL_QUADS);
		
		if(t!=Dirt)
		{
			glTexCoord2f(0, 1);
		    glVertex2i(x, y + height); // Upper-left
		    glTexCoord2f(1, 1);
		    glVertex2i(x + width, y + height); // Upper-right
		    glTexCoord2f(1, 0);
		    glVertex2i(x + width, y); // Bottom-right
		    glTexCoord2f(0, 0);
		    glVertex2i(x, y); // Bottom-left
		} else
		{
			glTexCoord2f(0, blockSize / 12);
		    glVertex2i(x, y + height); // Upper-left
		    glTexCoord2f(blockSize / 8, blockSize / 12);
		    glVertex2i(x + width, y + height); // Upper-right
		    glTexCoord2f(blockSize / 8, 0);
		    glVertex2i(x + width, y); // Bottom-right
		    glTexCoord2f(0, 0);
		    glVertex2i(x, y); // Bottom-left
		}
		glEnd();
		glPopMatrix();
	}
	
	public static void centerObject(Texture t,int x,int y,int height,int width)
	{
		glPushMatrix();
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// enable alpha blending
		glEnable(GL_BLEND); 
		glTranslated(x, Height - y, 0);
		
        t.bind();
        
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        	
        glBegin(GL_QUADS);
    	glTexCoord2f(0, 1);
    	glVertex2d(width / 2, 0);
    	glTexCoord2f(-1, 1);
    	glVertex2d(-width / 2, 0);
    	glTexCoord2f(-1, 0);
    	glVertex2d(-width / 2, -height);
    	glTexCoord2f(0, 0);
    	glVertex2d(width / 2, -height);
    	glEnd();
		
		glPopMatrix();
	}
	
	public static void drawBackground()
	{
//		Setup the names into an array so you can use an integer to find them.
		levelName[0] = "Game Over";
		levelName[1] = "1-1";
		levelName[2] = "1-2";
		levelName[3] = "1-3";
		levelName[4] = "1-4";
		levelName[5] = "1-5";
		
		difficulty[3] = "Easy";
		difficulty[2] = "Medium";
		difficulty[1] = "Hard";
		difficulty[0] = "Expert";
		
		glClear(GL_COLOR_BUFFER_BIT);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
//		OPTIONS MENU
		
		if(state == State.OPTIONS)
		{		
			centerObject(TitleBackOption, Width - blockSize, -Height / 2, 768, 1366);
			backObject(Title , Width / 2 - 225, Height / 2 + 100, 100, 800);

			if(optionMenu == 0)
			{ 
				if(inDevelopment)
				{
					buttonAbstract[2].draw(levelName[(int) level]);
				}	
				buttonAbstract[3].draw(difficulty[difficultyi]);
				buttonAbstract[4].draw("Back to Title");
				
				MainGame.fontCenter(font3, Height / 2 - blockSize * 3, "Written by Blaine Harper");
			}
			if(optionMenu == 1)
			{				
				buttonAbstract[2].draw("Change Resolution");
				buttonAbstract[4].draw("Back to Options");
			}
		}
		
//		STAGE SWAP MENU
		
		if(state == State.STAGE_SWAP)
		{
	        centerObject(TitleBackSwap, Width - blockSize, -Height / 2, 768, 1366);
			backObject(Title, Width / 2 - 225, Height / 2 + 100, 100, 800);
			
			if (level == 0)
			{
				MainGame.fontCenter(font2, Height / 2 - blockSize * 2, levelName[(int)level]);
			} else {
				MainGame.fontCenter(font2, Height / 2 - blockSize * 2, "World " + levelName[(int)level]);
			}
			MainGame.fontCenter(font3, Height / 2 - blockSize, "PRESS ENTER");
			MainGame.fontCenter(font3, Height / 2, "Lives: " + lives);
		}
		
//		MAIN MENU
		
		
		if(state == State.MAIN_MENU)
		{
	        centerObject(TitleBack, Width - blockSize, -Height / 2, 768, 1366);
			backObject(Title, Width / 2 - 225, Height / 2 + 100, 100, 800);
		//	Creates the buttons using the abstract entity button so I can create them more dynamically.
			
			buttonAbstract[1].draw("Start");
			buttonAbstract[2].draw("Options");
			buttonAbstract[4].draw("Quit");
		}
	}
	
	private static double xHUD,yHUD;
	
	public static void HUD(double x, double y)
	{
		System.out.println(((int) x) + " | " + ((int) y));
		xHUD = x;
		yHUD = y;
	}
	
	public static void drawHUD()
	{
		MainGame.fontDrawString(font3, (int) xHUD, (int) yHUD + blockSize * 2, "" + lives);
	}
}