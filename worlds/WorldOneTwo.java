package worlds;

import static mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import entity.AbstractMoveableEntity;
import mikecraft.Gravity;
import mikecraft.MainGame;
import mikecraft.Player;
import mikecraft.MainGame.State;

public class WorldOneTwo extends Gravity{
	public static boolean spawnEmerald = false;
	public static Emerald emeraldOne;
	public static Block brickOne, brickTwo, brickThree, brickFour, goldOne, goldTwo, goldThree, goldFour, goldFive;
	public static Ground groundOne, groundTwo;
	public static Back background;
	public static Hill hillOne, hillTwo, hillThree, hillFour;
	private static long lastFrame;

    private static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    protected static int getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }
	public static void drawBackground() {
		
		background = new Back(sky, 0, 0, Width * 5, Height * 4);
		groundOne = new Ground(dirt,0, 0, 0, Width * 2);
		groundTwo = new Ground(dirt, Width * 2 + BlockSize * 2, 0, 0, Width * 2 - BlockSize * 2);
		hillOne = new Hill(grass, Width * 2 + BlockSize * 6, BlockSize * 2, BlockSize, Width);
		hillTwo = new Hill(grass, Width * 2 + BlockSize * 8, BlockSize * 3, BlockSize, Width - BlockSize * 2);
		hillThree = new Hill(grass, Width * 3, BlockSize * 4, BlockSize, Width - BlockSize * 4);
		hillFour = new Hill(grass, Width * 3 + BlockSize * 2, BlockSize * 5, BlockSize, Width - BlockSize * 6);
		
        //blocks
		
		brickOne = new Block(brick, BlockSize * 8, BlockSize * 4, BlockSize, BlockSize);
		brickTwo = new Block(brick, BlockSize * 10, BlockSize * 4, BlockSize, BlockSize);
		brickThree = new Block(brick, Width + BlockSize * 2, BlockSize * 4, BlockSize, BlockSize);
		brickFour = new Block(brick, Width + BlockSize * 4, BlockSize * 4, BlockSize, BlockSize);
        goldOne = new Block(gold, Width + BlockSize, BlockSize * 4, BlockSize, BlockSize);
        goldTwo = new Block(gold, Width * 3 + BlockSize * 6, BlockSize * 2, BlockSize, BlockSize);
        goldThree = new Block(gold, Width * 3 + BlockSize * 6, BlockSize * 3, BlockSize, BlockSize);
        goldFour = new Block(gold, Width * 3 + BlockSize * 6, BlockSize * 4, BlockSize, BlockSize);
        goldFive = new Block(gold, Width * 3 + BlockSize * 6, BlockSize * 5, BlockSize, BlockSize);
        emeraldOne = new Emerald(emerald, Width + BlockSize, BlockSize * 5 + BlockSize / 6, BlockSize, BlockSize);
        
        //emeralds
		
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        flag.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(Width * 4 - BlockSize, BlockSize * 6); // Upper-left
        glTexCoord2f(1, 0);
        glVertex2i(Width * 4, BlockSize * 6); // Upper-right
        glTexCoord2f(1, 4);
        glVertex2i(Width * 4, BlockSize * 2); // Bottom-right
        glTexCoord2f(0, 4);
        glVertex2i(Width * 4 - BlockSize, BlockSize * 2); // Bottom-left
        glEnd();
	}
	static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        background.draw();
        emeraldOne.draw();
		emeraldOne.setDX(5);
        brickOne.draw();
        brickTwo.draw();
        brickThree.draw();
        brickFour.draw();
        goldOne.draw();
        goldTwo.draw();
        goldThree.draw();
        goldFour.draw();
        goldFive.draw();
        groundOne.draw();
        groundTwo.draw();
        hillOne.draw();
        hillTwo.draw();
        hillThree.draw();
        hillFour.draw();
    }
	private static class Emerald extends AbstractMoveableEntity {

		public Emerald(Texture t, double x, double y, double height, double width) {
			super(t, x, y, height, width);
		}

		@Override
		public void draw() {
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			emerald.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
        	glVertex2d(x, y + height); // Upper-left
        	glTexCoord2f(1, 0);
        	glVertex2d(x + width, y + height); // Upper-right
        	glTexCoord2f(1, 1);
        	glVertex2d(x + width, y); // Bottom-right
        	glTexCoord2f(0, 1);
        	glVertex2d(x, y); // Bottom-left
        	glEnd();
		}
		
	}
	private static class Block extends AbstractMoveableEntity {

		public Block(Texture t, double x, double y, double height, double width) {
			super(t, x, y, height, width);
		}

		@Override
		public void draw() {
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			t.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
        	glVertex2d(x, y + height); // Upper-left
        	glTexCoord2d(width / 64, 0);
        	glVertex2d(x + width, y + height); // Upper-right
        	glTexCoord2d(width / 64, height / 64);
        	glVertex2d(x + width, y); // Bottom-right
        	glTexCoord2d(0, height / 64);
        	glVertex2d(x, y); // Bottom-left
        	glEnd();
		}
		
	}
	private static class Hill extends AbstractMoveableEntity {

		public Hill(Texture t, double x, double y, double height, double width) {
			super(t, x, y, height, width);
		}

		@Override
		public void draw() {
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			t.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
        	glVertex2d(x, y + height); // Upper-left
        	glTexCoord2d(width / 64, 0);
        	glVertex2d(x + width, y + height); // Upper-right
        	glTexCoord2d(width / 64, 1);
        	glVertex2d(x + width, y); // Bottom-right
        	glTexCoord2f(0, 1);
        	glVertex2d(x, y); // Bottom-left
        	glEnd();
		}
		
	}
	private static class Ground extends AbstractMoveableEntity {

		public Ground(Texture t, double x, double y, double height, double width) {
			super(t, x, y, height, width);
		}

		@Override
		public void draw() {
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			grass.bind();
			glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
        	glVertex2d(x , BlockSize * 2); // Upper-left
        	glTexCoord2d(width / 64, 0);
        	glVertex2d(x + width, BlockSize * 2); // Upper-right
        	glTexCoord2d(width / 64, 1);
        	glVertex2d(x + width, BlockSize); // Bottom-right
			glTexCoord2d(0, 1);
        	glVertex2d(x, BlockSize); // Bottom-left
        	
        	glEnd();glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			dirt.bind();
			glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
        	glVertex2d(x, BlockSize); // Upper-left
        	glTexCoord2d(width / 64, 0);
        	glVertex2d(x + width, BlockSize); // Upper-right
        	glTexCoord2d(width / 64, 1);
        	glVertex2d(x + width, 0); // Bottom-right
			glTexCoord2d(0, 1);
        	glVertex2d(x, 0); // Bottom-left
        	glEnd();
			//glRectd(x, y, x + width, y + height);
		}
		
	}
	private static class Back extends AbstractMoveableEntity {

		public Back(Texture t, double x, double y, double height, double width) {
			super(t, x, y, height, width);
		}

		@Override
		public void draw() {
			glClear(GL_COLOR_BUFFER_BIT);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	        sky.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0, 1);
	        glVertex2i(0, Height * 4); // Upper-left
	        glTexCoord2f(1, 1);
	        glVertex2i(Width * 10, Height * 4); // Upper-right
	        glTexCoord2f(1, 0);
	        glVertex2i(Width * 10, 0); // Bottom-right
	        glTexCoord2f(0, 0);
	        glVertex2i(0, 0); // Bottom-left
			glEnd();
		}
	}
	static void logic(int delta){
        emeraldOne.update(delta);
	}
	public static void gravitation() {
		if(((x <= Width * 2 || x >= Width * 2 + BlockSize * 2) && y <= BlockSize * 2 && y >= ((BlockSize / 3) * 5)/*if the y is about to be below the ground*/ && /*and player is on the x field of play*/ player.getX() <= Width * 4)){
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
		} else if(y <= BlockSize * 3 && y >= BlockSize * 2 + ((BlockSize / 10) * 9) && x >= Width * 2 + BlockSize * 6 && x <= Width * 3 + BlockSize * 6){
			if (dy  <= 0){
				dy = 0;
			}
			y = BlockSize * 3;
			if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				dx = dx * 0.9;
			} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				dx = dx * 0.9;
			} if(jumpPressed && !jumpWasPressed){
				if (Char == 1 || Char == 2) {dy = Height / 60;}
				if (Char == 3){dy = Height / 50;}
			}
		}  else if(y <= BlockSize * 4 && y >= BlockSize * 3 + ((BlockSize / 10) * 9) && x >= Width * 2 + BlockSize * 8 && x <= Width * 3 + BlockSize * 6){
			if (dy  <= 0){
				dy = 0;
			}
			y = BlockSize * 4;
			if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				dx = dx * 0.9;
			} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				dx = dx * 0.9;
			} if(jumpPressed && !jumpWasPressed){
				if (Char == 1 || Char == 2) {dy = Height / 60;}
				if (Char == 3){dy = Height / 50;}
			}
		}  else if(y <= BlockSize * 5 && y >= BlockSize * 4 + ((BlockSize / 10) * 9) && x >= Width * 3 && x <= Width * 3 + BlockSize * 6){
			if (dy  <= 0){
				dy = 0;
			}
			y = BlockSize * 5;
			if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				dx = dx * 0.9;
			} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				dx = dx * 0.9;
			} if(jumpPressed && !jumpWasPressed){
				if (Char == 1 || Char == 2) {dy = Height / 60;}
				if (Char == 3){dy = Height / 50;}
			}
		}  else if(y <= BlockSize * 6 && y >= BlockSize * 5 + ((BlockSize / 10) * 9) && x >= Width * 3 + BlockSize * 2 && x <= Width * 3 + BlockSize * 7){
			if (dy  <= 0){
				dy = 0;
			}
			y = BlockSize * 6;
			if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				dx = dx * 0.9;
			} if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				dx = dx * 0.9;
			} if(jumpPressed && !jumpWasPressed){
				if (Char == 1 || Char == 2) {dy = Height / 60;}
				if (Char == 3){dy = Height / 50;}
			}
		} else if (x >= Width - BlockSize * 2 && x <= Width - BlockSize * 1 && y >= BlockSize * 2.3){
			dy -= 5;
		} else if (x >= Width +  BlockSize  * 0 && x <= Width + BlockSize * 3 && y >= BlockSize * 2.3){
			dy -= 5;
			if (x >= Width + BlockSize * 1 && x <= Width + BlockSize * 2){
			drawBackground();
			}
		} else if (x >= Width + BlockSize * 4 && x <= Width + BlockSize * 5 && y >= BlockSize * 2.3){
			dy -= 5;
		}
		
		if ( x >= Width * 4){
			dx = 0;
			level = 10;
			score = 0;
			state = State.STAGE_SWAP;
			try {
				MainGame.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Player.x= 100;
			Player.y = BlockSize * 2;
		}
	}
}
