package com.totn.entity;

import static org.lwjgl.opengl.GL11.*;
import static com.totn.mikecraft.MainGame.*;

import java.awt.Rectangle;

import com.totn.mikecraft.Gravity;

public class Enemy 
{
	public static double x;
	public double y;
	public double dx;
	public double dy;
	public double xbL;
	public double xbR;
	public boolean toDraw = true;
	public boolean jumpPressed,jumpWasPressed,end = false;
	public static int enemyX, enemyY;	
	protected static Rectangle hitbox = new Rectangle();

	
	public Enemy()
	{
		y = blockSize * 2;
		dx = 4;
	}
	public void draw()
	{
		glPushMatrix();
		
		enemyY = blockSize;
		enemyX = blockSize / 2;
		
		Gravity.enemyLogic();
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// enable alpha blending
		glEnable(GL_BLEND);
		glTranslated(x, Height - y, 0);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        if (dx < 0)
        {
        	MikeChar.bind();
    		glBegin(GL_QUADS);
    		glTexCoord2f(0, 1);
    		glVertex2d(-enemyX, 0);
    		glTexCoord2f(1, 1);
    		glVertex2d(enemyX, 0);
    		glTexCoord2f(1, 0);
    		glVertex2d(enemyX, -enemyY);
    		glTexCoord2f(0, 0);
    		glVertex2d(-enemyX, -enemyY);
    		glEnd();
        } else {
        	MikeChar.bind();
    		glBegin(GL_QUADS);
    		glTexCoord2f(0, 1);
    		glVertex2d(-enemyX, 0);
    		glTexCoord2f(-1, 1);
    		glVertex2d(enemyX, 0);
    		glTexCoord2f(-1, 0);
    		glVertex2d(enemyX, -enemyY);
    		glTexCoord2f(0, 0);
    		glVertex2d(-enemyX, -enemyY);
    		glEnd();
        }
		
		glPopMatrix();
		
	}
	public void setX(int xPos)
	{
		x = xPos;
	}
	public void setY(int yPos)
	{
		y = yPos;
	}
	public void setPos(int xPos,int yPos)
	{
		x = xPos;
		y = yPos;
	}
	public void setVisable(boolean bool)
	{
		toDraw = bool;
	}
	public void setBounds(int x,int x2)
	{
		xbL = x;
		xbR = x2;
	}
	public double getBoundLeft()
	{
		return xbL;
	}
	public double getBoundRight()
	{
		return xbR;
	}
	public double getX() 
	{
		return x;
	}
	public double getY() 
	{
		return y;
	}
	public double getDX() 
	{
		return dx;
	}
	public double getDY()
	{
		return dy;
	}
}
