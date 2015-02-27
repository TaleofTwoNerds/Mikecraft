package mikecraft;

import static org.lwjgl.opengl.GL11.*;
import static mikecraft.MainGame.*;

public class Enemy {
	public static double x, y, dx;
	
	public Enemy(){
		x = 200;
		y = BlockSize * 2;
	}
	public static void logic(){
		x+=dx;
		if(x>Width+10){
			dx = -10;
		}
	}
	public static void draw(){
		glPushMatrix();
		
		glTranslated(x, y, 0);
		
		glBegin(GL_QUADS);
		glVertex2d(-8, BlockSize * 3);
		glVertex2d(8, BlockSize * 3);
		glVertex2d(8, BlockSize * 2);
		glVertex2d(-8, BlockSize * 2);
		glEnd();
		
		glPopMatrix();
		
		logic();
	}
}