package com.totn.level;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;

import com.totn.entity.AbstractMoveableEntity;
import com.totn.mikecraft.Gravity;
import com.totn.mikecraft.MainGame;


public class World extends MainGame 
{
	public static void chooseLevel()
	{
		if (level == 1){
			WorldOneOne.main();
		} else if (level == 2){
			WorldOneTwo.main();
			WorldOneTwo.logic(getDelta());
		} else if (level == 3){
			WorldOneThree.main();
			WorldOneThree.logic(getDelta());
		} else if (level == 4){
			WorldOneFour.main();
			WorldOneFour.logic(getDelta());
		} else if (level == 5){
			WorldOneFive.main();
			WorldOneFive.logic(getDelta());
		}
	}
	
	private static long lastFrame;

    private static long getTime() 
    {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    public static int getDelta() 
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
			glPushMatrix();
			if(true){
				y = Height - y;				
//				glTranslated(x, y, 0);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
				t.bind();
				glBegin(GL_QUADS);
				glTexCoord2f(0, 1);
				glVertex2d(x, y + height); // Upper-left
				glTexCoord2f(1, 1);
				glVertex2d(x + width, y + height); // Upper-right
				glTexCoord2f(1, 0);
				glVertex2d(x + width, y); // Bottom-right
				glTexCoord2f(0, 0);
				glVertex2d(x, y); // Bottom-left
				glEnd();
			}
			glPopMatrix();
			dx=0;
			dy=0;
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
			Gravity.detection(x,y,width,height);
			y = Height - y;
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			t.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
        	glVertex2d(x, y + height); // Upper-left
        	glTexCoord2d(width / blockSize, 0);
        	glVertex2d(x + width, y + height); // Upper-right
        	glTexCoord2d(width / blockSize, -height/blockSize);
        	glVertex2d(x + width, y); // Bottom-right
        	glTexCoord2d(0, -height/blockSize);
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
			Gravity.detection(x,y,width,height);
			y = Height - y;
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			t.bind();
			glBegin(GL_QUADS);
			glTexCoord2d(0, height / blockSize);
        	glVertex2d(x, y + height); // Upper-left
        	glTexCoord2d(width / blockSize, height / blockSize);
        	glVertex2d(x + width, y + height); // Upper-right
        	glTexCoord2d(width / blockSize, 0);
        	glVertex2d(x + width, y); // Bottom-right
        	glTexCoord2d(0, 0);
        	glVertex2d(x, y); // Bottom-left
        	glEnd();
		}
		
	}
	public static class Ground extends AbstractMoveableEntity 
	{

		public Ground(Texture t, double x, double y, double height, double width) 
		{
			super(t,x, y, height, width);
		}

		@Override
		public void draw() 
		{
			Gravity.detection(x,y,width,height);
			y = Height - y;
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			t.bind();
			glBegin(GL_QUADS);
			glTexCoord2d(0,  height / blockSize / 2);
        	glVertex2d(x , y + height); // Upper-left
        	glTexCoord2d(width / blockSize,  height / blockSize / 2);
        	glVertex2d(x + width, y + height); // Upper-right
        	glTexCoord2d(width / blockSize, 0);
        	glVertex2d(x + width, y + height / 2); // Bottom-right
			glTexCoord2d(0, 0);
        	glVertex2d(x, y + height / 2); // Bottom-left
        	
        	glEnd();glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			Grass.bind();
			glBegin(GL_QUADS);
			glTexCoord2d(0, height / blockSize / 2);
        	glVertex2d(x, y + height/ 2); // Upper-left
        	glTexCoord2d(width / blockSize, height / blockSize / 2);
        	glVertex2d(x + width, y + height / 2); // Upper-right
        	glTexCoord2d(width / blockSize, 0);
        	glVertex2d(x + width, y); // Bottom-right
			glTexCoord2d(0, 0);
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
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			t.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
        	glVertex2d(x, y + height); // Upper-left
        	glTexCoord2d(width / blockSize, 0);
        	glVertex2d(x + width, y + height); // Upper-right
        	glTexCoord2d(width / blockSize, -height / blockSize);
        	glVertex2d(x + width, y); // Bottom-right
        	glTexCoord2d(0, -height / blockSize);
        	glVertex2d(x, y); // Bottom-left
        	glEnd();
		}
	}
    
}
