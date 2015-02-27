package levels;

import static org.lwjgl.opengl.GL11.*;
import mikecraft.MainGame;

import org.newdawn.slick.opengl.Texture;

public class WorldType extends MainGame{
	public static void WorldCubed(Texture texture, int beginX, int X, int beginY, int Y, int repX, int repY){
		glClear(GL_COLOR_BUFFER_BIT);
		//shape
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        texture.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, repY);
        glVertex2i(beginX, beginY + Y); // Upper-left
        glTexCoord2f(repX, repY);
        glVertex2i(beginX + X, beginY + Y); // Upper-right
        glTexCoord2f(repX, 0);
        glVertex2i(beginX + X, beginY); // Bottom-right
        glTexCoord2f(0, 0);
        glVertex2i(beginX, beginY); // Bottom-left
		glEnd();
	}
}
