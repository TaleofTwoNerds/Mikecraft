package com.totn.entity;

import static org.lwjgl.opengl.GL11.*;
import static com.totn.mikecraft.MainGame.*;

import java.awt.Rectangle;

import org.newdawn.slick.opengl.Texture;

public class Enemy extends AbstractMoveableEntity 
{
	protected double xBoundLeft, xBoundRight, speed;
	protected boolean toDraw = true;
	protected static Rectangle hitbox = new Rectangle();

	
	public Enemy(Texture t, double x, double y, double height,
			double width) {
		super(t, x, y, height, width);
		this.speed = blockSize / 15;
		this.dx = speed;
		this.y = blockSize * 2;
	}
	
	public void draw()
	{
		glPushMatrix();
		
		height = blockSize;
		width = blockSize;
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// enable alpha blending
		glEnable(GL_BLEND);
		glTranslated(x, Height - y, 0);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        if (dx < 0)
        {
        	MikeChar.bind();
        	
    		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        	
    		glBegin(GL_QUADS);
    		glTexCoord2f(0, 1);
    		glVertex2d(-width / 2, 0);
    		glTexCoord2f(1, 1);
    		glVertex2d(width / 2, 0);
    		glTexCoord2f(1, 0);
    		glVertex2d(width / 2, -height);
    		glTexCoord2f(0, 0);
    		glVertex2d(-width / 2, -height);
    		glEnd();
        } else {
        	MikeChar.bind();
        	
    		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        	
    		glBegin(GL_QUADS);
    		glTexCoord2f(0, 1);
    		glVertex2d(-width / 2, 0);
    		glTexCoord2f(-1, 1);
    		glVertex2d(width / 2, 0);
    		glTexCoord2f(-1, 0);
    		glVertex2d(width / 2, -height);
    		glTexCoord2f(0, 0);
    		glVertex2d(-width / 2, -height);
    		glEnd();
        }
		
		glPopMatrix();
	}
	public void setVisable(boolean bool)
	{
		toDraw = bool;
	}
	public void setBounds(int xLeft,int xRight)
	{
		xBoundLeft = xLeft;
		xBoundRight = xRight;
	}
	public boolean isVisable()
	{
		return toDraw;
	}
	public double getBoundLeft()
	{
		return xBoundLeft;
	}
	public double getBoundRight()
	{
		return xBoundRight;
	}
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
	public double getSpeed()
	{
		return speed;
	}
}
