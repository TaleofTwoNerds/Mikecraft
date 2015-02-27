package mikecraft;

import static mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.*;

public class GUI {
	public static void drawBackground(){
		glClear(GL_COLOR_BUFFER_BIT);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		/*glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	    dirt.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 8);
	    glVertex2i(0, Height); // Upper-left
	    glTexCoord2f(10, 8);
	    glVertex2i(Width, Height); // Upper-right
	    glTexCoord2f(10, 0);
	    glVertex2i(Width, 0); // Bottom-right
	    glTexCoord2f(0, 0);
	    glVertex2i(0, 0); // Bottom-left
		glEnd();*/
		
		if(state == state.STAGE_SWAP){glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		dirt.bind();
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
	    title.bind();
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
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	    worldStage.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
	    glVertex2i(Width / 2 - 135, Height / 2 + 28); // Upper-left
	    glTexCoord2f(1, 1);
	    glVertex2i(Width / 2 + 128, Height / 2 + 28); // Upper-right
	    glTexCoord2f(1, 0);
	    glVertex2i(Width / 2 + 128, Height / 2 - 100); // Bottom-right
	    glTexCoord2f(0, 0);
	    glVertex2i(Width / 2 - 135, Height / 2 - 100); // Bottom-left
		glEnd();}
		
		if(state == state.MAIN_MENU){glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		titleBack.bind();
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
	    title.bind();
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
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	    quitButton.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
	    glVertex2i(Width / 2 - 128, Height / 2); // Upper-left
	    glTexCoord2f(1, 1);
	    glVertex2i(Width / 2 + 128, Height / 2); // Upper-right
	    glTexCoord2f(1, 0);
	    glVertex2i(Width / 2 + 128, Height / 2 - 90); // Bottom-right
	    glTexCoord2f(0, 0);
	    glVertex2i(Width / 2 - 128, Height / 2 - 90); // Bottom-left
		glEnd();
		
	    button.bind();		
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glBegin(GL_QUADS);		
		glTexCoord2d(0, 1);
	    glVertex2d(Width / 2 - 200, Height / 2 - 100); // Upper-left
	    glTexCoord2d(1, 1);
	    glVertex2d(Width / 2 + 200, Height / 2 - 100); // Upper-right
	    glTexCoord2d(1, 0);
	    glVertex2d(Width / 2 + 200, Height / 2 - 175); // Bottom-right
	    glTexCoord2d(0, 0);
	    glVertex2d(Width / 2 - 200, Height / 2 - 175); // Bottom-left
		glEnd();		
		
		fontRender();
		}
	}
}
