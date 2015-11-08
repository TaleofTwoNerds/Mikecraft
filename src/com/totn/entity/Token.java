package com.totn.entity;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import com.totn.mikecraft.MainGame;

public class Token extends AbstractMoveableEntity 
{
	protected boolean visable;
	
	public Token(Texture t, double x, double y, double height,
			double width) 
	{
		super(t, x, y, height, width);
	}

	@Override
	public void draw() 
	{
		glPushMatrix();
		
		y = MainGame.Height - y;				
//		glTranslated(x, y, 0);
		t.bind();
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        
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
		
		glPopMatrix();
		dx=0;
		dy=0;
	}
	
	public void setVisable(boolean visable)
	{
		this.visable = visable;
	}
	
	public boolean isVisable()
	{
		return visable;
	}
}