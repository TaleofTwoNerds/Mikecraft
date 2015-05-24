package com.totn.level;

import static com.totn.mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;

import com.totn.mikecraft.Gravity;
import com.totn.mikecraft.MainGame;
import com.totn.mikecraft.MakeSound;
import com.totn.mikecraft.MainGame.State;

public class WorldOneOne extends Gravity
{
	public static float decell = (float) 0.8;
	public static World.Ground ground[] = new World.Ground[3];
	public static World.Back dirtOne,explosive, wheat;
	public static World.Hill hill[] = new World.Hill[4];
	public static World.Block back,bridge[] = new World.Block[7];
	public static World.Emerald emerald[] = new World.Emerald[8];

	public static void main()
	{
//		Sound is put into levels individually to allow changing of the individual songs.
		System.out.println(MainGame.state);
		if(MainGame.state==State.GAME)
		{
			MakeSound.levelSound.play();
		}
		drawBackground();
		logic(getDelta());
		gravitation();	
		render();
	}
	
	public static void drawBackground() 
	{	
		enemy.setBounds(Width * 5 / 2, Width * 7 / 2);

		back = new World.Block(Sky,0, 0, -Height * 2, Width * 5);		
		wheat = new World.Back(Wheat7, Width * 3 - blockSize * 2, blockSize * 3, blockSize, blockSize * 4);
		explosive = new World.Back(Tnt, Width * 3 - blockSize * 2, blockSize * 3, blockSize, blockSize * 4);

		ground[1] = new World.Ground(Dirt, 0, blockSize * 2, blockSize * 2, Width * 2);
		ground[2] = new World.Ground(Dirt, Width * 2 + blockSize * 2, blockSize * 2, blockSize * 2, Width * 3);
		
		hill[1] = new World.Hill(Grass, Width - blockSize, blockSize * 3, blockSize, blockSize * 3);
		hill[2] = new World.Hill(Grass, Width, blockSize * 4, blockSize, blockSize * 3);
		hill[3] = new World.Hill(Grass, Width - blockSize * 2, blockSize * 5, blockSize, blockSize * 4);
		
		dirtOne = new World.Back(Dirt, Width - blockSize * 2, blockSize * 4, blockSize * 2, blockSize * 5);
		
		bridge[1] = new World.Block(Planks_oak, Width * 2 - blockSize, blockSize * 2, blockSize, blockSize * 4);
		bridge[2] = new World.Block(Planks_oak, Width + blockSize * 5, blockSize * 4, blockSize, blockSize * 2);
		bridge[3] = new World.Block(Planks_oak, Width + blockSize * 8, blockSize * 5, blockSize, blockSize * 4);
		bridge[4] = new World.Block(Planks_oak, Width * 2 + blockSize * 4, blockSize * 6, blockSize, blockSize * 4);
		bridge[5] = new World.Block(Planks_oak, Width * 3 + blockSize, blockSize * 6, blockSize, blockSize * 3);

		emerald[1] = new World.Emerald(Emerald, Width - blockSize,  blockSize * 7, blockSize, blockSize);
		emerald[2] = new World.Emerald(Emerald, Width + blockSize, blockSize * 7, blockSize, blockSize);
		emerald[3] = new World.Emerald(Emerald, Width + blockSize * 6, blockSize * 6, blockSize, blockSize);
		emerald[4] = new World.Emerald(Emerald, Width * 2, blockSize * 7, blockSize, blockSize);
		emerald[5] = new World.Emerald(Emerald, Width * 2 + Width / 2, blockSize * 8, blockSize, blockSize);
		emerald[6] = new World.Emerald(Emerald, Width * 3 + blockSize * 2, blockSize * 8, blockSize, blockSize);
		emerald[7] = new World.Emerald(Emerald, Width * 3 + blockSize * 8, blockSize * 8, blockSize, blockSize);

		//title
		if (state == State.MAIN_MENU){
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	    Title.bind();
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
	}
	
	static void render() 
	{
        glClear(GL_COLOR_BUFFER_BIT);  
        back.draw();   
        ground[1].draw();
        ground[2].draw();
        dirtOne.draw();
        hill[1].draw();
        hill[2].draw();
        hill[3].draw();
        explosive.draw();
        wheat.draw();
        bridge[1].draw();
        bridge[2].draw();
        bridge[3].draw();
        bridge[4].draw();
        bridge[5].draw();  
        
        if(Gravity.emeraldOne){emerald[1].draw();}
        if(Gravity.emeraldTwo){emerald[2].draw();}
        if(Gravity.emeraldThree){emerald[3].draw();}
        if(Gravity.emeraldFour){emerald[4].draw();}
        if(Gravity.emeraldFive){emerald[5].draw();}
        if(Gravity.emeraldSix){emerald[6].draw();}
        if(Gravity.emeraldSeven){emerald[7].draw();}
        
		fontRender();
    }
	
	@SuppressWarnings("deprecation")
	private static void fontRender()
	{
		font3.drawString(Width / 4, Height / 4 -  32, "Welcome to", Color.white);
		font3.drawString(Width / 4, Height / 4, "Mikecraft, William", Color.white);
		font3.drawString(Width + blockSize * 3, Height - blockSize * 7, "Do you know why you're here?", Color.white);
		font3.drawString(Width * 2 + blockSize * 8, Height - blockSize * 8, "My amusement ", Color.white);
	}
	private static long lastFrame;

    private static long getTime() 
    {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    private static int getDelta() 
    {
//    	Delta (change) in time
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }
	static void logic(int delta)
	{
		emerald[1].update(delta);
		emerald[1].setDX(1);
	}

	@SuppressWarnings("static-access")
//	basically the logic of the level. Name gravitation because it used to
//	be the collision detection method before I got smart.
	public static void gravitation() 
	{		
		endLogic(0);
		if ( x >= Width * 4 + blockSize * 2){
			dx = 0;
			level = 2;
			MakeSound.playSound("course_clear.wav");
			MakeSound.levelSound.pause();
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
			player.y = blockSize * 2;
			enemy.setVisable(false);
			enemy.setPos(Width * 5 / 2, blockSize * 2);
			player.dx = 0;
			player.dy = 0;
		} if (emeraldOne && y <= blockSize * 7 && y >= blockSize * 4.9 && x >= Width - blockSize && x <= Width){
			score++;
			MakeSound.playSound("coin.wav");
			emeraldOne = false;
		} else if (emeraldTwo && y <= blockSize * 7 && y >= blockSize * 5 && x >= Width + blockSize && x <= Width + blockSize * 2){
			score++;
			MakeSound.playSound("coin.wav");
			emeraldTwo = false;
		} else if (emeraldThree && y <= blockSize * 6 && y >= blockSize * 4 && x >= Width + blockSize * 6 && x <= Width + blockSize * 7){
			score++;
			MakeSound.playSound("coin.wav");
			emeraldThree = false;
		} else if (emeraldFour && y <= blockSize * 7 && y >= blockSize * 5 && x >= Width * 2 && x <= Width * 2 + blockSize){
			score++;
			MakeSound.playSound("coin.wav");
			emeraldFour = false;
		} else if (emeraldFive && y <= blockSize * 6 && y >= blockSize * 4 && x >= Width * 2 + blockSize * 5 && x <= Width * 2 + blockSize * 6){
			score++;
			MakeSound.playSound("coin.wav");
			emeraldFive = false;
		} else if (emeraldSix && y <= blockSize * 6 && y >= blockSize * 5 && x >= Width * 3 + blockSize * 1 && x <= Width * 4 + blockSize * 2){
			score++;
			MakeSound.playSound("coin.wav");
			emeraldSix = false;
		} else if (emeraldSeven && y <= blockSize * 6 && y >= blockSize * 5 && x >= Width * 3 + blockSize * 5 && x <= Width * 4 + blockSize * 6){
			score++;
			MakeSound.playSound("coin.wav");
			emeraldSeven = false;
		} 
	}
}
