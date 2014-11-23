package mikecraft;

import static mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;

public class WorldOneOne extends Gravity{
	
	public static float decell = (float) 0.8;
	
	@SuppressWarnings("static-access")
	public static void gravitation() {
		if((y <= BlockSize * 2 && y >= ((BlockSize / 3) * 5)/*if the y is about to be below the ground*/ && /*and player is on the x field of play*/ player.getX() <= Width * 4 + BlockSize * 2)){
			dy = 0;
			y = BlockSize * 2;
			if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				dx = dx * 0.9;
			} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				dx = dx * 0.9;
			} if(jumpPressed && !jumpWasPressed){
				if (Char == 1 || Char == 2) {dy = Height / 60;}
				if (Char == 3){dy = Height / 50;}
			}
		} else if (y <= BlockSize * 3 && y >= BlockSize * 2 + ((BlockSize / 10) * 9) && x >= Width - BlockSize && x <= Width + BlockSize * 2){
			if (dy  <= 0){
				dy = 0;
			}
			y = BlockSize * 3;
			if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				dx = dx * decell;
			} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				dx = dx * decell;
			} if(jumpPressed && !jumpWasPressed){
				if (Char == 1 || Char == 2) {dy = Height / 55;}
				if (Char == 3){dy = 45;}
			}
		} else if (y <= BlockSize * 4 && y >= BlockSize * 3 + ((BlockSize / 10) * 9) && x >= Width && x <= Width + BlockSize * 4){
			if (dy  <= 0){
				dy = 0;
			}
			y = BlockSize * 4;
			if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				dx = dx * decell;
			} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				dx = dx * decell;
			} if(jumpPressed && !jumpWasPressed){
				if (Char == 1 || Char == 2) {dy = Height / 55;}
				if (Char == 3){dy = 45;}
			}
		} else if (y <= BlockSize * 5 && y >= BlockSize * 4 + ((BlockSize / 10) * 9) && x >= Width - BlockSize * 2 && x <= Width + BlockSize * 2){
			if (dy  <= 0){
				dy = 0;
			}
			y = BlockSize * 5;
			if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				dx = dx * decell;
			} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				dx = dx * decell;
			} if(jumpPressed && !jumpWasPressed){
				if (Char == 1 || Char == 2) {dy = Height / 55;}
				if (Char == 3){dy = 45;}
			}
		} else if (y <= BlockSize * 4 && y >= BlockSize * 3 + ((BlockSize / 10) * 9) && x >= Width + BlockSize * 5 && x <= Width + BlockSize * 7){
			if (dy  <= 0){
				dy = 0;
			}
			y = BlockSize * 4;
			if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				dx = dx * decell;
			} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				dx = dx * decell;
			} if(jumpPressed && !jumpWasPressed){
				if (Char == 1 || Char == 2) {dy = Height / 55;}
				if (Char == 3){dy = 45;}
			}
		} else if (y <= BlockSize * 5 && y >= BlockSize * 4 + ((BlockSize / 10) * 9) && x >= Width + BlockSize * 8 && x <= Width + BlockSize * 10){
			if (dy  <= 0){
				dy = 0;
			}
			y = BlockSize * 5;
			if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				dx = dx * decell;
			} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				dx = dx * decell;
			} if(jumpPressed && !jumpWasPressed){
				if (Char == 1 || Char == 2) {dy = Height / 55;}
				if (Char == 3){dy = 45;}
			}
		} else if (y <= BlockSize * 5 && y >= BlockSize * 4 + ((BlockSize / 10) * 9) && x >= Width + BlockSize * 11 && x <= Width + BlockSize * 13){
			if (dy  <= 0){
				dy = 0;
			}
			y = BlockSize * 5;
			if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				dx = dx * decell;
			} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				dx = dx * decell;
			} if(jumpPressed && !jumpWasPressed){
				if (Char == 1 || Char == 2) {dy = Height / 55;}
				if (Char == 3){dy = 45;}
			}
		} else if (y <= BlockSize * 6 && y >= BlockSize * 5 + ((BlockSize / 10) * 9) && x >= Width + BlockSize * 14 && x <= Width + BlockSize * 18){
			if (dy  <= 0){
				dy = 0;
			}
			y = BlockSize * 6;
			if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				dx = dx * decell;
			} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				dx = dx * decell;
			} if(jumpPressed && !jumpWasPressed){
				if (Char == 1 || Char == 2) {dy = Height / 55;}
				if (Char == 3){dy = 45;}
			}
		} else if (y <= BlockSize * 6 && y >= BlockSize * 5 && x >= Width * 3 + BlockSize && x <= Width * 3 + BlockSize * 4){
			if (dy  <= 0){
				dy = 0;
			}
			y = BlockSize * 6;
			if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				dx = dx * decell;
			} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				dx = dx * decell;
			} if(jumpPressed && !jumpWasPressed){
				if (Char == 1 || Char == 2) {dy = Height / 55;}
				if (Char == 3){dy = 45;}
			}
		} if ( x >= Width * 4 + BlockSize * 2){
			dx = 0;
			level = 1.2;
			state = state.STAGE_SWAP;
			Gravity.emeraldOne = true;
			Gravity.emeraldTwo = true;
			Gravity.emeraldThree = true;
			Gravity.emeraldFour = true;
			Gravity.emeraldFive = true;
			Gravity.emeraldSix = true;
			Gravity.emeraldSeven = true;
			try {
				MainGame.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			player.x= 100;
			player.y = BlockSize * 2;
		} if (emeraldOne && y <= BlockSize * 7 && y >= BlockSize * 4.9 && x >= Width - BlockSize && x <= Width){
			score = score + 1;
			emeraldOne = false;
		} else if (emeraldTwo && y <= BlockSize * 7 && y >= BlockSize * 5 && x >= Width + BlockSize && x <= Width + BlockSize * 2){
			score = score + 1;
			emeraldTwo = false;
		} else if (emeraldThree && y <= BlockSize * 6 && y >= BlockSize * 4 && x >= Width + BlockSize * 6 && x <= Width + BlockSize * 7){
			score = score + 1;
			emeraldThree = false;
		} else if (emeraldFour && y <= BlockSize * 7 && y >= BlockSize * 5 && x >= Width * 2 && x <= Width * 2 + BlockSize){
			score = score + 1;
			emeraldFour = false;
		} else if (emeraldFive && y <= BlockSize * 6 && y >= BlockSize * 4 && x >= Width * 2 + BlockSize * 5 && x <= Width * 2 + BlockSize * 6){
			score = score + 1;
			emeraldFive = false;
		} else if (emeraldSix && y <= BlockSize * 6 && y >= BlockSize * 5 && x >= Width * 3 + BlockSize * 1 && x <= Width * 4 + BlockSize * 2){
			score = score + 1;
			emeraldSix = false;
		} else if (emeraldSeven && y <= BlockSize * 6 && y >= BlockSize * 5 && x >= Width * 3 + BlockSize * 3 && x <= Width * 4 + BlockSize * 4){
			score = score + 1;
			emeraldSeven = false;
		} 
	}
	public static void drawBackground() {
		
		glClear(GL_COLOR_BUFFER_BIT);
		//sky
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        sky.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
        glVertex2i(0, Height * 4); // Upper-left
        glTexCoord2f(2, 1);
        glVertex2i(Width * 11, Height * 4); // Upper-right
        glTexCoord2f(2, 0);
        glVertex2i(Width * 11, 0); // Bottom-right
        glTexCoord2f(0, 0);
        glVertex2i(0, 0); // Bottom-left
		glEnd();
		
		/*
		WorldType.WorldCubed(sky, 0, Width * 10, 0, Height * 2, 1, 1);
		WorldType.WorldCubed(wall, Width + BlockSize * 4, BlockSize * 5, BlockSize * 2, BlockSize * 4, 1, 1);
		*/
		
        
		//wall
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		tnt.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 1);
        glVertex2i(Width * 2 + BlockSize * 9, BlockSize * 2); // Upper-left
        glTexCoord2f(4, 1);
        glVertex2i(Width * 2 + BlockSize * 5, BlockSize * 2); // Upper-right
        glTexCoord2f(4, 0);
        glVertex2i(Width * 2 + BlockSize * 5, BlockSize * 3); // Bottom-right
        glTexCoord2f(0, 0);
        glVertex2i(Width * 2 + BlockSize * 9, BlockSize * 3); // Bottom-left
        glEnd();
        
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        wheat7.bind();
		glBegin(GL_QUADS);
        glTexCoord2f(0, 1);
        glVertex2i(Width * 2 + BlockSize * 9, BlockSize * 2); // Upper-left
        glTexCoord2f(4, 1);
        glVertex2i(Width * 2 + BlockSize * 5, BlockSize * 2); // Upper-right
        glTexCoord2f(4, 0);
        glVertex2i(Width * 2 + BlockSize * 5, BlockSize * 3); // Bottom-right
        glTexCoord2f(0, 0);
        glVertex2i(Width * 2 + BlockSize * 9, BlockSize * 3); // Bottom-left
        glEnd();
       
        //flagpole

        
		
		//title
		if (state == State.MAIN_MENU){
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	    title.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, -1);
	    glVertex2i(Width / 2 - 128, Height / 2 + 256); // Upper-left
	    glTexCoord2f(1, -1);
	    glVertex2i(Width / 2 + 128, Height / 2 + 256); // Upper-right
	    glTexCoord2f(1, 0);
	    glVertex2i(Width / 2 + 128, Height / 2 + 128); // Bottom-right
	    glTexCoord2f(0, 0);
	    glVertex2i(Width / 2 - 128 , Height / 2 + 128); // Bottom-left
		glEnd();
		}
		
		//ground
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        grass.bind();
		glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(0, BlockSize * 2); // Upper-left
        glTexCoord2f(20, 0);
        glVertex2i(Width * 2, BlockSize * 2); // Upper-right
        glTexCoord2f(20, 1);
        glVertex2i(Width * 2, BlockSize); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(0, BlockSize); // Bottom-left
        glEnd();
        
        //ground II
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        dirt.bind();
		glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(0, BlockSize); // Upper-left
        glTexCoord2f(20, 0);
        glVertex2i(Width * 2, BlockSize); // Upper-right
        glTexCoord2f(20, 1);
        glVertex2i(Width * 2, 0); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(0, 0); // Bottom-left
        glEnd();
      
        //ground
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        grass.bind();
      	glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width * 2 + BlockSize * 2, BlockSize * 2); // Upper-left
        glTexCoord2f(20, 0);
        glVertex2i(Width * 4 + BlockSize * 2, BlockSize * 2); // Upper-right
        glTexCoord2f(20, 1);
        glVertex2i(Width * 4 + BlockSize * 2, BlockSize); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width * 2 + BlockSize * 2, BlockSize); // Bottom-left
        glEnd();
              
        //ground II
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        dirt.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width * 2 + BlockSize * 2, BlockSize); // Upper-left
        glTexCoord2f(20, 0);
        glVertex2i(Width * 4 + BlockSize * 2, BlockSize); // Upper-right
        glTexCoord2f(20, 1);
        glVertex2i(Width * 4 + BlockSize * 2, 0); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width * 2 + BlockSize * 2, 0); // Bottom-left
        glEnd();

        //hills
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        grass.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width - BlockSize * 2, BlockSize * 5); // Upper-left
        glTexCoord2f(4, 0);
        glVertex2i(Width + BlockSize * 2, BlockSize * 5); // Upper-right
        glTexCoord2f(4, 1);
        glVertex2i(Width + BlockSize * 2, BlockSize * 4); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width - BlockSize * 2, BlockSize * 4); // Bottom-left
        glEnd();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        dirt.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width - BlockSize * 2, BlockSize * 4); // Upper-left
        glTexCoord2f(4, 0);
        glVertex2i(Width + BlockSize * 2, BlockSize * 4); // Upper-right
        glTexCoord2f(4, 2);
        glVertex2i(Width + BlockSize * 2, BlockSize * 2); // Bottom-right
        glTexCoord2f(0, 2);
        glVertex2i(Width - BlockSize * 2, BlockSize * 2); // Bottom-left
        glEnd();
        
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        grass.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width, BlockSize * 4); // Upper-left
        glTexCoord2f(4, 0);
        glVertex2i(Width + BlockSize * 4, BlockSize * 4); // Upper-right
        glTexCoord2f(4, 1);
        glVertex2i(Width + BlockSize * 4, BlockSize * 3); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width, BlockSize * 3); // Bottom-left
        glEnd();
        
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        dirt.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width, BlockSize * 3); // Upper-left
        glTexCoord2f(4, 0);
        glVertex2i(Width + BlockSize * 4, BlockSize * 3); // Upper-right
        glTexCoord2f(4, 1);
        glVertex2i(Width + BlockSize * 4, BlockSize * 2); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width, BlockSize * 2); // Bottom-left
        glEnd();
        
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        grass.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width - BlockSize, BlockSize * 3); // Upper-left
        glTexCoord2f(3, 0);
        glVertex2i(Width + BlockSize * 2, BlockSize * 3); // Upper-right
        glTexCoord2f(3, 1);
        glVertex2i(Width + BlockSize * 2, BlockSize * 2); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width - BlockSize, BlockSize * 2); // Bottom-left
        glEnd();
        
        
        //bridges
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        planks_oak.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width * 2 - BlockSize, BlockSize * 2); // Upper-left
        glTexCoord2f(4, 0);
        glVertex2i(Width * 2 + BlockSize * 3, BlockSize * 2); // Upper-right
        glTexCoord2f(4, 1);
        glVertex2i(Width * 2 + BlockSize * 3, BlockSize); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width * 2 - BlockSize, BlockSize); // Bottom-left
        glEnd();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        planks_oak.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width + BlockSize * 5, BlockSize * 4); // Upper-left
        glTexCoord2f(2, 0);
        glVertex2i(Width + BlockSize * 7, BlockSize * 4); // Upper-right
        glTexCoord2f(2, 1);
        glVertex2i(Width + BlockSize * 7, BlockSize * 3); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width + BlockSize * 5, BlockSize * 3); // Bottom-left
        glEnd();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        planks_oak.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width + BlockSize * 8, BlockSize * 5); // Upper-left
        glTexCoord2f(2, 0);
        glVertex2i(Width + BlockSize * 10, BlockSize * 5); // Upper-right
        glTexCoord2f(2, 1);
        glVertex2i(Width + BlockSize * 10, BlockSize * 4); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width + BlockSize * 8, BlockSize * 4); // Bottom-left
        glEnd();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        planks_oak.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width * 2 + BlockSize, BlockSize * 5); // Upper-left
        glTexCoord2f(2, 0);
        glVertex2i(Width * 2 + BlockSize * 3, BlockSize * 5); // Upper-right
        glTexCoord2f(2, 1);
        glVertex2i(Width * 2 + BlockSize * 3, BlockSize * 4); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width * 2 + BlockSize * 1, BlockSize * 4); // Bottom-left
        glEnd();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        planks_oak.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width * 2+ BlockSize * 4, BlockSize * 6); // Upper-left
        glTexCoord2f(4, 0);
        glVertex2i(Width * 2 + BlockSize * 8, BlockSize * 6); // Upper-right
        glTexCoord2f(4, 1);
        glVertex2i(Width * 2 + BlockSize * 8, BlockSize * 5); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width * 2 + BlockSize * 4, BlockSize * 5); // Bottom-left
        glEnd();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        planks_oak.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width * 3 + BlockSize, BlockSize * 6); // Upper-left
        glTexCoord2f(3, 0);
        glVertex2i(Width * 3 + BlockSize * 4, BlockSize * 6); // Upper-right
        glTexCoord2f(3, 1);
        glVertex2i(Width * 3 + BlockSize * 4, BlockSize * 5); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width * 3 + BlockSize, BlockSize * 5); // Bottom-left
        glEnd();
        
        //blocks
        if(Gravity.emeraldOne){
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        emerald.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width - BlockSize, BlockSize * 7); // Upper-left
        glTexCoord2f(1, 0);
        glVertex2i(Width, BlockSize * 7); // Upper-right
        glTexCoord2f(1, 1);
        glVertex2i(Width, BlockSize * 6); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width - BlockSize, BlockSize * 6); // Bottom-left
        glEnd();}
        if(Gravity.emeraldTwo){
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        emerald.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width + BlockSize, BlockSize * 7); // Upper-left
        glTexCoord2f(1, 0);
        glVertex2i(Width + BlockSize + BlockSize, BlockSize * 7); // Upper-right
        glTexCoord2f(1, 1);
        glVertex2i(Width + BlockSize + BlockSize, BlockSize * 6); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width + BlockSize, BlockSize * 6); // Bottom-left
        glEnd();}
        if(Gravity.emeraldThree){
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        emerald.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width + BlockSize * 6, BlockSize * 6); // Upper-left
        glTexCoord2f(1, 0);
        glVertex2i(Width + BlockSize * 7, BlockSize * 6); // Upper-right
        glTexCoord2f(1, 1);
        glVertex2i(Width + BlockSize * 7, BlockSize * 5); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width + BlockSize * 6, BlockSize * 5); // Bottom-left
        glEnd();}
        if(Gravity.emeraldFour){
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        emerald.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width * 2, BlockSize * 7); // Upper-left
        glTexCoord2f(1, 0);
        glVertex2i(Width * 2 + BlockSize, BlockSize * 7); // Upper-right
        glTexCoord2f(1, 1);
        glVertex2i(Width * 2 + BlockSize, BlockSize * 6); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width * 2, BlockSize * 6); // Bottom-left
        glEnd();}
        if(Gravity.emeraldFive){
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        emerald.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width * 2 + BlockSize * 5, BlockSize * 8); // Upper-left
        glTexCoord2f(1, 0);
        glVertex2i(Width * 2 + BlockSize * 6, BlockSize * 8); // Upper-right
        glTexCoord2f(1, 1);
        glVertex2i(Width * 2 + BlockSize * 6, BlockSize * 7); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width * 2 + BlockSize * 5, BlockSize * 7); // Bottom-left
        glEnd();}
        if(Gravity.emeraldSix){
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        emerald.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width * 3 + BlockSize * 1, BlockSize * 8); // Upper-left
        glTexCoord2f(1, 0);
        glVertex2i(Width * 3 + BlockSize * 2, BlockSize * 8); // Upper-right
        glTexCoord2f(1, 1);
        glVertex2i(Width * 3 + BlockSize * 2, BlockSize * 7); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width * 3 + BlockSize * 1, BlockSize * 7); // Bottom-left
        glEnd();}
        if(Gravity.emeraldSeven){
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        emerald.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width * 3 + BlockSize * 3, BlockSize * 8); // Upper-left
        glTexCoord2f(1, 0);
        glVertex2i(Width * 3 + BlockSize * 4, BlockSize * 8); // Upper-right
        glTexCoord2f(1, 1);
        glVertex2i(Width * 3 + BlockSize * 4, BlockSize * 7); // Bottom-right
        glTexCoord2f(0, 1);
        glVertex2i(Width * 3 + BlockSize * 3, BlockSize * 7); // Bottom-left
        glEnd();}
        
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        flag.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width * 4 + BlockSize, BlockSize * 6); // Upper-left
        glTexCoord2f(1, 0);
        glVertex2i(Width * 4 + BlockSize * 2, BlockSize * 6); // Upper-right
        glTexCoord2f(1, 4);
        glVertex2i(Width * 4 + BlockSize * 2, BlockSize * 2); // Bottom-right
        glTexCoord2f(0, 4);
        glVertex2i(Width * 4 + BlockSize, BlockSize * 2); // Bottom-left
        glEnd();
	}
}
