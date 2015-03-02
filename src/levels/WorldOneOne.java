package levels;

import static mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.*;
import mikecraft.Gravity;
import mikecraft.MainGame;
import mikecraft.MainGame.State;

import org.newdawn.slick.Color;

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
		drawBackground();
		gravitation();	
		render();
	}
	
	public static void drawBackground() 
	{	
		back = new World.Block(Sky,0, 0, -Height * 2, Width * 5);		
		wheat = new World.Back(Wheat7, Width * 3 - BlockSize * 2, BlockSize * 3, BlockSize, BlockSize * 4);
		explosive = new World.Back(Tnt, Width * 3 - BlockSize * 2, BlockSize * 3, BlockSize, BlockSize * 4);

		ground[1] = new World.Ground(Dirt, 0, BlockSize * 2, BlockSize * 2, Width * 2);
		ground[2] = new World.Ground(Dirt, Width * 2 + BlockSize * 2, BlockSize * 2, BlockSize * 2, Width * 3);
		
		hill[1] = new World.Hill(Grass, Width - BlockSize, BlockSize * 3, BlockSize, BlockSize * 3);
		hill[2] = new World.Hill(Grass, Width, BlockSize * 4, BlockSize, BlockSize * 3);
		hill[3] = new World.Hill(Grass, Width - BlockSize * 2, BlockSize * 5, BlockSize, BlockSize * 4);
		
		dirtOne = new World.Back(Dirt, Width - BlockSize * 2, BlockSize * 4, BlockSize * 2, BlockSize * 5);
		
		bridge[1] = new World.Block(Planks_oak, Width * 2 - BlockSize, BlockSize * 2, BlockSize, BlockSize * 4);
		bridge[2] = new World.Block(Planks_oak, Width + BlockSize * 5, BlockSize * 4, BlockSize, BlockSize * 2);
		bridge[3] = new World.Block(Planks_oak, Width + BlockSize * 8, BlockSize * 5, BlockSize, BlockSize * 4);
		bridge[4] = new World.Block(Planks_oak, Width * 2 + BlockSize * 4, BlockSize * 6, BlockSize, BlockSize * 4);
		bridge[5] = new World.Block(Planks_oak, Width * 3 + BlockSize, BlockSize * 6, BlockSize, BlockSize * 3);

		emerald[1] = new World.Emerald(Emerald, Width - BlockSize,  BlockSize * 7, BlockSize, BlockSize);
		emerald[2] = new World.Emerald(Emerald, Width + BlockSize, BlockSize * 7, BlockSize, BlockSize);
		emerald[3] = new World.Emerald(Emerald, Width + BlockSize * 6, BlockSize * 6, BlockSize, BlockSize);
		emerald[4] = new World.Emerald(Emerald, Width * 2, BlockSize * 7, BlockSize, BlockSize);
		emerald[5] = new World.Emerald(Emerald, Width * 2 + Width / 2, BlockSize * 8, BlockSize, BlockSize);
		emerald[6] = new World.Emerald(Emerald, Width * 3 + BlockSize * 2, BlockSize * 8, BlockSize, BlockSize);
		emerald[7] = new World.Emerald(Emerald, Width * 3 + BlockSize * 8, BlockSize * 8, BlockSize, BlockSize);

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
		font.drawString(Width / 4, Height / 4 -  32, "THIS WORLD IS (NO LONGER)", Color.white);
		font.drawString(Width / 4, Height / 4, "INVISIBLE", Color.white);
		font.drawString(Width + BlockSize * 3, Height - BlockSize * 8, "You know what?", Color.white);
		font.drawString(Width * 2, Height - BlockSize * 8, "Fight me", Color.white);
	}
	
	static void logic(int delta)
	{

	}

	@SuppressWarnings("static-access")
	public static void gravitation() 
	{		
		endLogic(0);
		if ( x >= Width * 4 + BlockSize * 2){
			dx = 0;
			level = 2;
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
			score++;
			emeraldOne = false;
		} else if (emeraldTwo && y <= BlockSize * 7 && y >= BlockSize * 5 && x >= Width + BlockSize && x <= Width + BlockSize * 2){
			score++;
			emeraldTwo = false;
		} else if (emeraldThree && y <= BlockSize * 6 && y >= BlockSize * 4 && x >= Width + BlockSize * 6 && x <= Width + BlockSize * 7){
			score++;
			emeraldThree = false;
		} else if (emeraldFour && y <= BlockSize * 7 && y >= BlockSize * 5 && x >= Width * 2 && x <= Width * 2 + BlockSize){
			score++;
			emeraldFour = false;
		} else if (emeraldFive && y <= BlockSize * 6 && y >= BlockSize * 4 && x >= Width * 2 + BlockSize * 5 && x <= Width * 2 + BlockSize * 6){
			score++;
			emeraldFive = false;
		} else if (emeraldSix && y <= BlockSize * 6 && y >= BlockSize * 5 && x >= Width * 3 + BlockSize * 1 && x <= Width * 4 + BlockSize * 2){
			score++;
			emeraldSix = false;
		} else if (emeraldSeven && y <= BlockSize * 6 && y >= BlockSize * 5 && x >= Width * 3 + BlockSize * 5 && x <= Width * 4 + BlockSize * 6){
			score++;
			emeraldSeven = false;
		} 
	}
}
