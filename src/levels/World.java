package levels;

import static mikecraft.MainGame.BlockSize;
import static mikecraft.MainGame.Height;
import static mikecraft.MainGame.Width;
import static mikecraft.MainGame.dirt;
import static mikecraft.MainGame.emerald;
import static mikecraft.MainGame.grass;
import static mikecraft.MainGame.sky;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL11.glVertex2i;
import mikecraft.MainGame;

import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;

import entity.AbstractMoveableEntity;

public class World extends MainGame 
{
	public static void chooseLevel()
	{
		if (level == 1.1){
			WorldOneOne.main();
			WorldOneOne.logic(getDelta());
		} else if (level == 1.2){
			WorldOneTwo.main();
			WorldOneTwo.logic(getDelta());
		}/* else if (level == 1.3){
			WorldOneThree.drawBackground();
			WorldOneThree.gravitation();		
			fontRender();
		} else if (level == 1.4){
			WorldOneFour.drawBackground();
			WorldOneFour.gravitation();
			fontRender();
		}*/
	}
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
    public static class Emerald extends AbstractMoveableEntity 
    {

		public Emerald(Texture t, double x, double y, double height, double width) 
		{
			super(t, x, y, height, width);
		}

		@Override
		public void draw() 
		{
			y = Height - y;
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			t.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
        	glVertex2d(x, y + height); // Upper-left
        	glTexCoord2f(1, 0);
        	glVertex2d(x + width, y + height); // Upper-right
        	glTexCoord2f(1, -1);
        	glVertex2d(x + width, y); // Bottom-right
        	glTexCoord2f(0, -1);
        	glVertex2d(x, y); // Bottom-left
        	glEnd();
		}
		
	}
    public static class Block extends AbstractMoveableEntity 
    {

		public Block(Texture t, double x, double y, double height, double width) 
		{
			super(t, x, y, height, width);
		}

		@Override
		public void draw() 
		{
			y = Height - y;
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			t.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
        	glVertex2d(x, y + height); // Upper-left
        	glTexCoord2d(width / 64, 0);
        	glVertex2d(x + width, y + height); // Upper-right
        	glTexCoord2d(width / 64, -(height / 64));
        	glVertex2d(x + width, y); // Bottom-right
        	glTexCoord2d(0, -(height / 64));
        	glVertex2d(x, y); // Bottom-left
        	glEnd();
		}
		
	}
	public static class Hill extends AbstractMoveableEntity 
	{

		public Hill(Texture t, double x, double y, double height, double width) 
		{
			super(t, x, y, height, width);
		}

		@Override
		public void draw() 
		{
			y = Height - y;
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			t.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
        	glVertex2d(x, y + height); // Upper-left
        	glTexCoord2d(width / 64, 0);
        	glVertex2d(x + width, y + height); // Upper-right
        	glTexCoord2d(width / 64, -(height / 64));
        	glVertex2d(x + width, y); // Bottom-right
        	glTexCoord2d(0, -(height / 64));
        	glVertex2d(x, y); // Bottom-left
        	glEnd();
		}
		
	}
	public static class Ground extends AbstractMoveableEntity 
	{

		public Ground(Texture t, double x, double y, double height, double width) 
		{
			super(t, x, y, height, width);
		}

		@Override
		public void draw() 
		{
			y = Height - y;
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			dirt.bind();
			glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
        	glVertex2d(x , y + height); // Upper-left
        	glTexCoord2d(width / 64, 0);
        	glVertex2d(x + width, y + height); // Upper-right
        	glTexCoord2d(width / 64, -1);
        	glVertex2d(x + width, y + height / 2); // Bottom-right
			glTexCoord2d(0, -1);
        	glVertex2d(x, y + height / 2); // Bottom-left
        	
        	glEnd();glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			grass.bind();
			glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
        	glVertex2d(x, y + height/ 2); // Upper-left
        	glTexCoord2d(width / 64, 0);
        	glVertex2d(x + width, y + height / 2); // Upper-right
        	glTexCoord2d(width / 64, -1);
        	glVertex2d(x + width, y); // Bottom-right
			glTexCoord2d(0, -1);
        	glVertex2d(x, y); // Bottom-left
        	glEnd();
			//glRectd(x, y, x + width, y + height);
		}
		
	}
	public static class Back extends AbstractMoveableEntity 
	{

		public Back(Texture t, double x, double y, double height, double width) 
		{
			super(t, x, y, height, width);
		}

		@Override
		public void draw() 
		{
			y = Height - y;
			glClear(GL_COLOR_BUFFER_BIT);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	        t.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
	        glVertex2d(0, -height); // Upper-left
	        glTexCoord2f(1, 0);
	        glVertex2d(width, -height); // Upper-right
	        glTexCoord2f(1, -1);
	        glVertex2d(width, 0); // Bottom-right
	        glTexCoord2f(0, -1);
	        glVertex2d(0, 0); // Bottom-left
			glEnd();
		}
	}
    
}
