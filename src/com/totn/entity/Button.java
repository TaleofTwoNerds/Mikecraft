package com.totn.entity;

import static com.totn.mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import com.totn.mikecraft.MainGame;

public class Button extends AbstractEntity 
{
	protected boolean hovering;
	protected String text;
	protected int size = 1;
	
	public Button(Texture t, double x, double y, double height, 
			double width) {
		super(t, x, y, height, width);
	}
	
	public void draw(String text)
	{
		this.text = text;
		
		glPushMatrix();
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// enable alpha blending
		glEnable(GL_BLEND); 

		glTranslated(x, Height - y, 0);
		
        if(isHovering())
        {
        	buttonHover.bind();
        } else {   
        	button.bind();
        }
        
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        if(size == 1)
        {
        	glBegin(GL_QUADS);
        	glTexCoord2f(0, 1);
        	glVertex2d(width / 2, 0);
        	glTexCoord2f(-1, 1);
        	glVertex2d(-width / 2, 0);
        	glTexCoord2f(-1, 0);
        	glVertex2d(-width / 2, -height);
        	glTexCoord2f(0, 0);
        	glVertex2d(width / 2, -height);
        	glEnd();
        }
		
		glPopMatrix();
		
		fontCenter(MainGame.font3, (float) (Height - y - height + 2), text);
	}
	
	public boolean isHovering()
	{
		if(Mouse.getX() >= x - width / 2
			&& Mouse.getX() <= x + width / 2
			&& Mouse.getY() >= y
			&& Mouse.getY() <= y + height)
		{
			hovering = true;
			return hovering;
		} else {
			hovering = false;
			return hovering;
		}
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}

	@Override
	public void update(int delta) {
		
	}
	
	public void draw()
	{		
		
	}
}
