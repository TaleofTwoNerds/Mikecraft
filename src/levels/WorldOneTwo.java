package levels;

import static mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import entity.AbstractMoveableEntity;
import mikecraft.Gravity;
import mikecraft.MainGame;
import mikecraft.Player;
import mikecraft.MainGame.State;

public class WorldOneTwo extends Gravity{
	public static boolean spawnEmerald = false;
	public static World.Emerald emeraldOne;
	public static World.Block back,brickOne, brickTwo, brickThree, brickFour, goldOne, goldTwo, goldThree, goldFour, goldFive;
	public static World.Ground groundOne, groundTwo;
	public static World.Hill hillOne, hillTwo, hillThree, hillFour;
	private static long lastFrame;

    private static long getTime() 
    {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    protected static int getDelta() 
    {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }
    
    public static void main()
    {
    	WorldOneTwo.drawBackground();
		WorldOneTwo.gravitation();
		WorldOneTwo.render();
    }
    
	public static void drawBackground() 
	{
		back = new World.Block(sky,0, 0, -Height * 2, Width * 5);
		groundOne = new World.Ground(dirt,0, BlockSize * 2, BlockSize * 2, Width * 2);
		groundTwo = new World.Ground(dirt, Width * 2 + BlockSize * 2, BlockSize * 2, BlockSize * 2, Width * 3);
		hillOne = new World.Hill(grass, Width * 2 + BlockSize * 6, BlockSize * 3, BlockSize, Width);
		hillTwo = new World.Hill(grass, Width * 2 + BlockSize * 8, BlockSize * 4, BlockSize, Width - BlockSize * 2);
		hillThree = new World.Hill(grass, Width * 3, BlockSize * 5, BlockSize, Width - BlockSize * 4);
		hillFour = new World.Hill(grass, Width * 3 + BlockSize * 2, BlockSize * 6, BlockSize, Width - BlockSize * 6);
		
        //blocks
		
		brickOne = new World.Block(brick, BlockSize * 8, BlockSize * 5, BlockSize, BlockSize);
		brickTwo = new World.Block(brick, BlockSize * 10, BlockSize * 5, BlockSize, BlockSize);
		brickThree = new World.Block(brick, Width + BlockSize * 2, BlockSize * 5, BlockSize, BlockSize);
		brickFour = new World.Block(brick, Width + BlockSize * 4, BlockSize * 5, BlockSize, BlockSize);
        
		goldOne = new World.Block(gold, Width + BlockSize, BlockSize * 5, BlockSize, BlockSize);
        goldTwo = new World.Block(gold, Width * 3 + BlockSize * 6, BlockSize * 3, BlockSize, BlockSize);
        goldThree = new World.Block(gold, Width * 3 + BlockSize * 6, BlockSize * 4, BlockSize, BlockSize);
        goldFour = new World.Block(gold, Width * 3 + BlockSize * 6, BlockSize * 5, BlockSize, BlockSize);
        goldFive = new World.Block(gold, Width * 3 + BlockSize * 6, BlockSize * 6, BlockSize, BlockSize);
        
        emeraldOne = new World.Emerald(emerald, Width + BlockSize, BlockSize * 6 + BlockSize / 6, BlockSize, BlockSize);
        
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
	static void render() 
	{
        glClear(GL_COLOR_BUFFER_BIT);
        back.draw();
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
        
        fontRender();
    }
	
	private static void fontRender()
	{
		font.drawString(100, 20, "THIS WORLD IS", Color.white);
		font.drawString(100, 70, "NOT UPSIDE DOWN :D", Color.white);
		font.drawString(Width * 3 + BlockSize * 4, -Height - 70, "NOT UPSIDE DOWN :D", Color.white);
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
