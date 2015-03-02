package mikecraft;

import static mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;

import mikecraft.MainGame.State;

public class GUI 
{
	public static void drawBackground()
	{
		levelName[0] = "Game Over";
		levelName[1] = "1-1";
		levelName[2] = "1-2";
		levelName[3] = "1-3";
		levelName[4] = "1-4";
		levelName[5] = "1-5";
		
		glClear(GL_COLOR_BUFFER_BIT);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		if(state == State.OPTIONS){glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Dirt.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 8);
	    glVertex2i(0, Height); // Upper-left
	    glTexCoord2f(10, 8);
	    glVertex2i(Width, Height); // Upper-right
	    glTexCoord2f(10, 0);
	    glVertex2i(Width, 0); // Bottom-right
	    glTexCoord2f(0, 0);
	    glVertex2i(0, 0); // Bottom-left
		glEnd();
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	    Title.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
	    glVertex2i(Width / 2 - 225, Height / 2 + 200); // Upper-left
	    glTexCoord2f(1, 1);
	    glVertex2i(Width / 2 + 575, Height / 2 + 200); // Upper-right
	    glTexCoord2f(1, 0);
	    glVertex2i(Width / 2 + 575, Height / 2 + 100); // Bottom-right
	    glTexCoord2f(0, 0);
	    glVertex2i(Width / 2 - 225, Height / 2 + 100); // Bottom-left
		glEnd();
		
		double bHeight = 16;
		double bWidth = 128;
		double by = Height / 2 - 175;
		double bx = Width / 2 - 200;		
		double byQuit = by + 128;
		
		Button.bind();		
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glBegin(GL_QUADS);		
		glTexCoord2d(0, 1);
	    glVertex2d(bx, by + bHeight * 3 + 64); // Upper-left
	    glTexCoord2d(1, 1);
	    glVertex2d(bx + bWidth * 3, by + bHeight * 3 + 64); // Upper-right
	    glTexCoord2d(1, 0);
	    glVertex2d(bx + bWidth * 3, by + 64); // Bottom-right
	    glTexCoord2d(0, 0);
	    glVertex2d(bx, by + 64); // Bottom-left
		glEnd();
		
		QuitButton.bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glBegin(GL_QUADS);	
	    glTexCoord2d(0, 1);
	    glVertex2d(bx, byQuit + bHeight * 3); // Upper-left
	    glTexCoord2d(1, 1);
	    glVertex2d(bx + bWidth * 3, byQuit + bHeight * 3); // Upper-right
	    glTexCoord2d(1, 0);
	    glVertex2d(bx + bWidth * 3, byQuit); // Bottom-right
	    glTexCoord2d(0, 0);
	    glVertex2d(bx, byQuit); // Bottom-left
		glEnd();
		
		font.drawString(Width / 4 + BlockSize * 2 + 5, Height / 4 + 17, levelName[(int)level], Color.white);	
		font.drawString(Width / 4 + BlockSize * 2 - 5, Height / 4 + 81, "Back", Color.white);
		}
		
		if(state == State.STAGE_SWAP){glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Dirt.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 8);
	    glVertex2i(0, Height); // Upper-left
	    glTexCoord2f(10, 8);
	    glVertex2i(Width, Height); // Upper-right
	    glTexCoord2f(10, 0);
	    glVertex2i(Width, 0); // Bottom-right
	    glTexCoord2f(0, 0);
	    glVertex2i(0, 0); // Bottom-left
		glEnd();
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	    Title.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
	    glVertex2i(Width / 2 - 225, Height / 2 + 200); // Upper-left
	    glTexCoord2f(1, 1);
	    glVertex2i(Width / 2 + 575, Height / 2 + 200); // Upper-right
	    glTexCoord2f(1, 0);
	    glVertex2i(Width / 2 + 575, Height / 2 + 100); // Bottom-right
	    glTexCoord2f(0, 0);
	    glVertex2i(Width / 2 - 225, Height / 2 + 100); // Bottom-left
		glEnd();
		if (level == 0)
		{
			font2.drawString(Width / 4 + BlockSize * 2 - 85, Height / 4 + 17, "" + levelName[(int)level], Color.white);
		} else {
			font2.drawString(Width / 4 + BlockSize * 2 - 75, Height / 4 + 17, "World " + levelName[(int)level], Color.white);
		}
		}
		
		if(state == State.MAIN_MENU){glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		TitleBack.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
	    glVertex2i(0, 256 * 3); // Upper-left
	    glTexCoord2f(1, 1);
	    glVertex2i(256 * 3, 256 * 3); // Upper-right
	    glTexCoord2f(1, 0);
	    glVertex2i(256 * 3, 0); // Bottom-right
	    glTexCoord2f(0, 0);
	    glVertex2i(0, 0); // Bottom-left
		glEnd();
		
		//glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	    Title.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
	    glVertex2i(Width / 2 - 225, Height / 2 + 200); // Upper-left
	    glTexCoord2f(1, 1);
	    glVertex2i(Width / 2 + 575, Height / 2 + 200); // Upper-right
	    glTexCoord2f(1, 0);
	    glVertex2i(Width / 2 + 575, Height / 2 + 100); // Bottom-right
	    glTexCoord2f(0, 0);
	    glVertex2i(Width / 2 - 225, Height / 2 + 100); // Bottom-left
		glEnd();	
		
		double bHeight = 16;
		double bWidth = 128;
		double by = Height / 2 - 175;
		double bx = Width / 2 - 200;
		
	    StartButton.bind();		
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glBegin(GL_QUADS);		
		glTexCoord2d(0, 1);
	    glVertex2d(bx, by + bHeight * 3); // Upper-left
	    glTexCoord2d(1, 1);
	    glVertex2d(bx + bWidth * 3, by + bHeight * 3); // Upper-right
	    glTexCoord2d(1, 0);
	    glVertex2d(bx + bWidth * 3, by); // Bottom-right
	    glTexCoord2d(0, 0);
	    glVertex2d(bx, by); // Bottom-left
		glEnd();	
		
		Button.bind();		
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glBegin(GL_QUADS);		
		glTexCoord2d(0, 1);
	    glVertex2d(bx, by + bHeight * 3 + 64); // Upper-left
	    glTexCoord2d(1, 1);
	    glVertex2d(bx + bWidth * 3, by + bHeight * 3 + 64); // Upper-right
	    glTexCoord2d(1, 0);
	    glVertex2d(bx + bWidth * 3, by + 64); // Bottom-right
	    glTexCoord2d(0, 0);
	    glVertex2d(bx, by + 64); // Bottom-left
		glEnd();	
		
		double byQuit = by + 128;
		
	    QuitButton.bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glBegin(GL_QUADS);	
	    glTexCoord2d(0, 1);
	    glVertex2d(bx, byQuit + bHeight * 3); // Upper-left
	    glTexCoord2d(1, 1);
	    glVertex2d(bx + bWidth * 3, byQuit + bHeight * 3); // Upper-right
	    glTexCoord2d(1, 0);
	    glVertex2d(bx + bWidth * 3, byQuit); // Bottom-right
	    glTexCoord2d(0, 0);
	    glVertex2d(bx, byQuit); // Bottom-left
		glEnd();
		
		fontRender();
		}
	}
}
