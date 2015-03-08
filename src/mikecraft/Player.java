package mikecraft;

import static org.lwjgl.opengl.GL11.*;
import static mikecraft.MainGame.*;

import java.awt.Rectangle;

import entity.Entity;

public class Player {
	
	public static double x;
	public static double y;
	public static double z;
	public static double dx;
	public static double dy;
	public static boolean jumpPressed, jumpWasPressed, end = false;
	public static int SteveX, SteveY;	
	protected static Rectangle hitbox = new Rectangle();

	
	public Player(){
		x = 100;
		y = blockSize * 2;
		dx = 4;
	}

	public void sleeping(){
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(){
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
		
		Gravity.logic();
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// enable alpha blending
		glEnable(GL_BLEND);
		glTranslated(x, Height - y, 0);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        if (dx < 0){
        	PlayerSkin.bind();
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
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getDX() {
		return dx;
	}
	public double getDY(){
		return dy;
	}
	public static boolean intersects(Entity other) {
		hitbox.setBounds((int) x, (int) y, (int) SteveX, (int) SteveY);
		return hitbox.intersects(other.getX(), other.getY(), other.getWidth(), other.getHeight());
	}
}
