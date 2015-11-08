package com.totn.entity;

import static org.lwjgl.opengl.GL11.*;
import static com.totn.mikecraft.MainGame.*;

import java.awt.Rectangle;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import com.totn.mikecraft.Physics;
import com.totn.mikecraft.MakeSound;

public class Player extends AbstractMoveableEntity{
	
	protected boolean end = false,ground;
	protected int SteveX, SteveY;
	protected int[] setting = new int[16];
	protected double speed;
	protected static Rectangle hitbox = new Rectangle();

	
	public Player(Texture t, double x, double y, double height,
			double width) {
		super(t, x, y, height, width);
		this.speed = blockSize / 10;
		this.dx = speed;
		this.dy = 0;
		this.x = 100;
		this.y = blockSize * 2;
	}
	
	public void draw()
	{
		glPushMatrix();
		
		if (Char == 1){
			SteveY = blockSize * 2;
			SteveX = blockSize / 2;
		} else if (Char == 2) {
			SteveY = blockSize;
			SteveX = blockSize / 2;
		} else {
			SteveY = blockSize * 2;
			SteveX = blockSize / 4;
		}
		
		Physics.logic();
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// enable alpha blending
		glEnable(GL_BLEND);
		glTranslated(player.x, Height - y, 0);
		if (dx < 0){
        	PlayerSkin.bind();
        	
    		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        	
    		glBegin(GL_QUADS);
    		glTexCoord2f(1, 1);
    		glVertex2d(-SteveX, 0);
    		glTexCoord2f(0, 1);
    		glVertex2d(SteveX, 0);
    		glTexCoord2f(0, 0);
    		glVertex2d(SteveX, -SteveY);
    		glTexCoord2f(1, 0);
    		glVertex2d(-SteveX, -SteveY);
    		glEnd();
        } else {
        	PlayerSkin.bind();
        	
    		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        	
    		glBegin(GL_QUADS);
    		glTexCoord2f(0, 1);
    		glVertex2d(-SteveX, 0);
    		glTexCoord2f(1, 1);
    		glVertex2d(SteveX, 0);
    		glTexCoord2f(1, 0);
    		glVertex2d(SteveX,-SteveY);
    		glTexCoord2f(0, 0);
    		glVertex2d(-SteveX,-SteveY);
    		glEnd();
        }
		
		glPopMatrix();
		
	}
	
	public void jump(boolean override)
	{
		if(override||ground)
		{
			dy = Height / 90;
			if(!override)
			{
				MakeSound.playSound("jump.wav");
			}
		}
	}
	
	public void jump()
	{
		dy = Height / 40;  
		MakeSound.playSound("jump.wav");
	}

	public boolean isEnd() 
	{
		return end;
	}

	public void setEnd(boolean end) 
	{
		this.end = end;
	}

	public boolean getGround() 
	{
		return ground;
	}

	public void setGround(boolean ground) 
	{
		this.ground = ground;
	}
	
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
	
	public double getSpeed()
	{
		return speed;
	}
	
	public void setSetting(int setting, int value)
	{
		this.setting[setting] = value;
	}
	
	public int getSetting(int setting)
	{
		return this.setting[setting];
	}
	
	public void defaultSettings()
	{
		setting[0] = Keyboard.KEY_UP;
		setting[1] = Keyboard.KEY_RIGHT;
		setting[2] = Keyboard.KEY_DOWN;
		setting[3] = Keyboard.KEY_LEFT;
	}
}
