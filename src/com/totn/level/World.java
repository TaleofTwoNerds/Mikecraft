package com.totn.level;

import static com.totn.mikecraft.Physics.*;
import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import com.totn.entity.AbstractMoveableEntity;
import com.totn.mikecraft.Physics;
import com.totn.mikecraft.MainGame;

/*
 * 	This needs to be overhauled completely
 * 	The levels should be JSON objects that are built by a Level class with a Level.load(...) method
 * 	Current level can be stored in a Global object
 */

public class World extends MainGame 
{
	private static boolean entitiesSetup = false;
	
	public static void chooseLevel()
	{
		if(!entitiesSetup)
		{
			enemy.setY(blockSize * 2);
			player.setY(blockSize * 2.6);
	        emerald[0].setLocation(Width - blockSize * 2, blockSize * 6);
			entitiesSetup = true;
			WorldOneTwo.drawBackground();
		}
		if (level == 1)
		{
			WorldOneOne.main();
		} else if (level == 2)
		{
			WorldOneTwo.main();
		} else if (level == 3)
		{
			WorldOneThree.main();
		} else if (level == 4)
		{
			WorldOneFour.main();
		} else if (level == 5)
		{
			WorldOneFive.main();
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
			Physics.detection(false, x,y,width,height);
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
    public static class FullBlock extends AbstractMoveableEntity 
    {
		public FullBlock(Texture t, double x, double y, double height, double width) 
		{
			super(t, x, y, height, width);
		}
		
		@Override
		public void draw() 
		{
			Physics.detection(true, x,y,width,height);
	       	
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
			Physics.detection(false, x,y,width,height);
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
			Physics.detection(false, x,y,width,height);
			
			if((player.getY() <= y - blockSize && player.getY() >= y - height) && (player.getX() >=  x - blockSize / 3 && player.getX() <=  x + width + blockSize / 3))
			{
				if(player.getX() <= x + player.getWidth() / 2)
				{
					if (player.getDX() <= 0)
					{
						player.setDX(0);
					}
					player.setX(x + player.getWidth() / 2);
				} else if (player.getX() >= x + width - player.getWidth() / 2)
				{
					if (player.getDX() >= 0)
					{
						player.setDX(0);
					}
					player.setX(x + width - player.getWidth() / 2);
				}
			} 
			
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
