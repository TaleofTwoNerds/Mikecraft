package com.totn.mikecraft;

import static com.totn.mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.totn.entity.AbstractMoveableEntity;
import com.totn.mikecraft.MainGame.State;

public class GUI 
{
	private static void backObject(Texture t,int x,int y,int height,int width)
	{
		glPushMatrix();
		int my,mheight;
		my = Height - y + 15;
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		if(
				(t==button)&&
				Mouse.getX()>=x&&
				Mouse.getX()<=x+width&&
				Mouse.getY()>=my&&
				Mouse.getY()<=my+height
				)
		{
			t=buttonHover;
		} else {

		}
		t.bind();
		
		glBegin(GL_QUADS);
		
		glTexCoord2f(0, 1);
	    glVertex2i(x, y + height); // Upper-left
	    glTexCoord2f(1, 1);
	    glVertex2i(x + width, y + height); // Upper-right
	    glTexCoord2f(1, 0);
	    glVertex2i(x + width, y); // Bottom-right
	    glTexCoord2f(0, 0);
	    glVertex2i(x, y); // Bottom-left
	    
		glEnd();
		glPopMatrix();
	}
	public static void drawBackground()
	{
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
		
		charName[1] = "Steve";
		
		glClear(GL_COLOR_BUFFER_BIT);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
//		OPTIONS MENU
		
		if(state == State.OPTIONS)
		{		
			glClear(GL_COLOR_BUFFER_BIT);  
			System.out.println((Width / 2 - 200) + " | " + (Height / 2 - 47) + " | " + (Height / 2) + " | " + (Width / 2 + 183));
			backObject(Dirt, 0, 0, Height, Width);
			backObject(Title , Width / 2 - 225, Height / 2 + 100, 100, 800);
			backObject(button, Width / 2 - 200, Height / 2 - 112, 48, 383);
			backObject(button, Width / 2 - 200, Height / 2 - 48, 48, 383);
			backObject(button, Width / 2 - 200, Height / 2 + 18, 48, 383);
		
			font3.drawString(Width / 4 + blockSize * 2 - 150, Height / 4 - 52, "Written by Blaine Harper", Color.white);
			font3.drawString(Width / 4 + blockSize * 2 + 5, Height / 4 + 12, levelName[(int)level], Color.white);	
		
			if(difficultyi==1||difficultyi==3)
			{
				font3.drawString(Width / 4 + blockSize * 2 - 5, Height / 4 + 76, difficulty[difficultyi], Color.white);
			}else{
				font3.drawString(Width / 4 + blockSize * 2 - 15, Height / 4 + 76, difficulty[difficultyi], Color.white);
			}
		
			font3.drawString(Width / 4 + blockSize * 2 - 5, Height / 4 + 139, "Back", Color.white);
		}
		
//		STAGE SWAP MENU
		
		if(state == State.STAGE_SWAP)
		{
	        glClear(GL_COLOR_BUFFER_BIT);  

			backObject(Dirt, 0, 0, Height, Width);
			backObject(Title, Width / 2 - 225, Height / 2 + 100, 100, 800);
			
			if (level == 0)
			{
				font2.drawString(Width / 4 + blockSize * 2 - 105, Height / 4 + 17, levelName[(int)level], Color.white);
			} else {
				font2.drawString(Width / 4 + blockSize * 2 - 105, Height / 4 + 17, "World " + levelName[(int)level], Color.white);
			}
			font3.drawString(Width / 4 + blockSize * 2 - 57, Height / 4 + 82, "PRESS ENTER", Color.white);
			font3.drawString(Width / 4 + blockSize * 2 - 22, Height / 4 + 114, "Lives: " + lives, Color.white);
		}
		
//		MAIN MENU
		
		if(state == State.MAIN_MENU)
		{
	        glClear(GL_COLOR_BUFFER_BIT);  

			backObject(TitleBack, 0, 0, Height, Width);
			backObject(Title, Width / 2 - 225, Height / 2 + 100, 100, 800);
			backObject(Button[1], Width / 2 - 200, Height / 2 - 175, 48, 383);
			backObject(Button[3], Width / 2 - 200, Height / 2 - 111, 48, 383);
			backObject(Button[4], Width / 2 - 200, Height / 2 + 17, 48, 383);
		
		fontRender();
		}
	}
}