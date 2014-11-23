package mikecraft;

import static org.lwjgl.opengl.GL11.*;

public class Goals extends Player{
	
	public ItemType emerald;
	
	public void draw(){
		glPushMatrix();
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// enable alpha blending
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glTranslated(x, y, 0);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glBegin(GL_QUADS);
        
        glEnd();
		
		glPopMatrix();
	}
}
