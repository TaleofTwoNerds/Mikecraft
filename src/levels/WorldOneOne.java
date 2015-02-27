package levels;

import static mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.*;
import mikecraft.Gravity;
import mikecraft.MainGame;
import mikecraft.MainGame.State;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

public class WorldOneOne extends Gravity
{
	public static float decell = (float) 0.8;
	public static World.Ground groundOne, groundTwo;
	public static World.Back background;
	public static World.Hill hillOne,hillTwo,hillThree,dirtOne;
	public static World.Block back,bridgeOne,bridgeTwo,bridgeThree,bridgeFour,bridgeFive,bridgeSix,explosive, wheat;

	public static void main()
	{
		drawBackground();
		gravitation();	
		render();
	}
	
	public static void drawBackground() 
	{	
		back = new World.Block(sky,0, 0, -Height * 2, Width * 5);
		groundOne = new World.Ground(dirt,0, BlockSize * 2, BlockSize * 2, Width * 2);
		groundTwo = new World.Ground(dirt, Width * 2 + BlockSize * 2, BlockSize * 2, BlockSize * 2, Width * 3);
		
		hillOne = new World.Hill(grass, Width - BlockSize, BlockSize * 3, BlockSize, BlockSize * 3);
		hillTwo = new World.Hill(grass, Width, BlockSize * 4, BlockSize, BlockSize * 4);
		hillThree = new World.Hill(grass, Width - BlockSize * 2, BlockSize * 5, BlockSize, BlockSize * 4);
		
		dirtOne = new World.Hill(dirt, Width - BlockSize * 2, BlockSize * 4, BlockSize * 2, BlockSize * 6);
		
		bridgeOne = new World.Block(planks_oak, Width * 2 - BlockSize, BlockSize * 2, BlockSize, BlockSize * 4);
		bridgeTwo = new World.Block(planks_oak, Width + BlockSize * 5, BlockSize * 4, BlockSize, BlockSize * 2);
		bridgeThree = new World.Block(planks_oak, Width + BlockSize * 8, BlockSize * 5, BlockSize, BlockSize * 2);
		wheat = new World.Block(wheat7, Width * 3 - BlockSize * 2, BlockSize * 3, BlockSize, BlockSize * 4);
		bridgeFour = new World.Block(planks_oak, Width * 2 + BlockSize, BlockSize * 5, BlockSize, BlockSize * 2);
		bridgeFive = new World.Block(planks_oak, Width * 2 + BlockSize * 4, BlockSize * 6, BlockSize, BlockSize * 4);
		bridgeSix = new World.Block(planks_oak, Width * 3 + BlockSize, BlockSize * 6, BlockSize, BlockSize * 3);
		explosive = new World.Block(tnt, Width * 3 - BlockSize * 2, BlockSize * 3, BlockSize, BlockSize * 4);

		/*
		WorldType.WorldCubed(sky, 0, Width * 10, 0, Height * 2, 1, 1);
		WorldType.WorldCubed(wall, Width + BlockSize * 4, BlockSize * 5, BlockSize * 2, BlockSize * 4, 1, 1);
		*/

		//title
		if (state == State.MAIN_MENU){
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	    title.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, -1);
	    glVertex2i(Width / 2 - 128, Height - (Height / 2 + 256)); // Upper-left
	    glTexCoord2f(1, -1);
	    glVertex2i(Width / 2 + 128, Height - (Height / 2 + 256)); // Upper-right
	    glTexCoord2f(1, 0);
	    glVertex2i(Width / 2 + 128, Height - (Height / 2 + 128)); // Bottom-right
	    glTexCoord2f(0, 0);
	    glVertex2i(Width / 2 - 128 , Height - (Height / 2 + 128)); // Bottom-left
		glEnd();
		}
        
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
	
	static void render() {
        glClear(GL_COLOR_BUFFER_BIT);  
        back.draw();   
        groundOne.draw();
        groundTwo.draw();
        dirtOne.draw();
        hillOne.draw();
        hillTwo.draw();
        hillThree.draw();
        bridgeOne.draw();
        bridgeTwo.draw();
        bridgeThree.draw();
        bridgeFour.draw();
        bridgeFive.draw();
        explosive.draw();
        wheat.draw();
        bridgeSix.draw();  
        
		fontRender();
    }
	private static void fontRender(){
		font.drawString(Width / 4, Height / 4 -  32, "THIS WORLD IS (NO LONGER)", Color.white);
		font.drawString(Width / 4, Height / 4, "INVISIBLE", Color.white);
		font.drawString(Width + BlockSize * 3, Height - BlockSize * 7, "You know what?", Color.white);
		font.drawString(Width * 2, Height - BlockSize * 7, "Fight me", Color.white);
	}
	static void logic(int delta){

	}

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
}
