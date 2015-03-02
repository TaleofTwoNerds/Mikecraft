package levels;

import static mikecraft.MainGame.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import mikecraft.Gravity;
import mikecraft.MainGame;
import mikecraft.MainGame.State;
import mikecraft.Player;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;

public class WorldOneTwo extends Gravity
{
	public static boolean spawnEmerald = false;
	public static float decell = (float) 0.8;
	public static World.Emerald emerald[] = new World.Emerald[10];
	public static World.Block back,brick[] = new World.Block[10], gold[] = new World.Block[10];
	public static World.Ground ground[] = new World.Ground[4];
	public static World.Hill hill[] = new World.Hill[10];
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
		WorldOneTwo.logic(getDelta());
    }
    
	public static void drawBackground() 
	{
		back = new World.Block(Sky,0, 0, -Height * 2, Width * 8);
		ground[1] = new World.Ground(Dirt,0, BlockSize * 2, BlockSize * 2, Width * 2);
		ground[2] = new World.Ground(Dirt, Width * 2 + BlockSize * 3, BlockSize * 2, BlockSize * 2, Width * 2);
		ground[3] = new World.Ground(Dirt, Width * 4 + BlockSize * 6, BlockSize * 2, BlockSize * 2, Width * 3);
		hill[1] = new World.Hill(Grass, Width * 2 + BlockSize * 9, BlockSize * 3, BlockSize, BlockSize * 5);
		hill[2] = new World.Hill(Grass, Width * 3, BlockSize * 4, BlockSize, BlockSize * 4);
		hill[3] = new World.Hill(Grass, Width * 3 + BlockSize, BlockSize * 5, BlockSize, BlockSize * 3);
		hill[4] = new World.Hill(Grass, Width * 3 + BlockSize * 2, BlockSize * 6, BlockSize, BlockSize * 2);
		
        //blocks
		
		brick[1] = new World.Block(Brick, BlockSize * 8, BlockSize * 5, BlockSize, BlockSize);
		brick[2] = new World.Block(Brick, BlockSize * 10, BlockSize * 5, BlockSize, BlockSize);
		brick[3] = new World.Block(Brick, Width + BlockSize * 2, BlockSize * 5, BlockSize, BlockSize);
		brick[4] = new World.Block(Brick, Width + BlockSize * 4, BlockSize * 5, BlockSize, BlockSize);
        
		gold[1] = new World.Block(Gold, Width + BlockSize, BlockSize * 5, BlockSize, BlockSize);
        gold[2] = new World.Block(Gold, Width * 3 + BlockSize * 4, BlockSize * 3, BlockSize, BlockSize);
        gold[3] = new World.Block(Gold, Width * 3 + BlockSize * 4, BlockSize * 4, BlockSize, BlockSize);
        gold[4] = new World.Block(Gold, Width * 3 + BlockSize * 4, BlockSize * 5, BlockSize, BlockSize);
        gold[5] = new World.Block(Gold, Width * 3 + BlockSize * 4, BlockSize * 6, BlockSize, BlockSize);
        
        emerald[1] = new World.Emerald(Emerald, Width + BlockSize, BlockSize * 6 + BlockSize / 6, BlockSize, BlockSize);
	}
	static void render() 
	{
        glClear(GL_COLOR_BUFFER_BIT);
        back.draw();
        if(Gravity.emeraldOne){emerald[1].draw();}
		brick[1].draw();
		brick[2].draw();
		brick[3].draw();
		brick[4].draw();
        gold[1].draw();
        gold[2].draw();
        gold[3].draw();
        gold[4].draw();
        gold[5].draw();
        ground[1].draw();
        ground[2].draw();
        ground[3].draw();
        hill[1].draw();
        hill[2].draw();
        hill[3].draw();
        hill[4].draw();
        
        fontRender();
    }
	
	@SuppressWarnings("deprecation")
	private static void fontRender()
	{
		font.drawString(100, 20, "THIS WORLD IS", Color.white);
		font.drawString(100, 70, "NOT UPSIDE DOWN :D", Color.white);
		font.drawString(Width * 3 + BlockSize * 4, -Height - 70, "NOT UPSIDE DOWN :D", Color.white);
	}
	
	static void logic(int delta)
	{
        emerald[1].update(delta);
	}
	public static void gravitation()
	{
		endLogic(0);
		if (x >= Width - BlockSize * 2 && x <= Width - BlockSize * 1 && y >= BlockSize * 2.3)
		{
			dy = 0;
		} else if (x >= Width +  BlockSize  * 0 && x <= Width + BlockSize * 3 && y >= BlockSize * 2.3)
		{
			dy = 0;
			if (x >= Width + BlockSize * 1 && x <= Width + BlockSize * 2)
			{
			drawBackground();
			}
		} else if (x >= Width + BlockSize * 4 && x <= Width + BlockSize * 5 && y >= BlockSize * 2.3)
		{
			dy = 0;
		}
		
		if ( x >= Width * 7)
		{
			dx = 0;
			level = 3;
			state = State.STAGE_SWAP;
			try 
			{
				MainGame.main(null);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Player.x= 100;
			Player.y = BlockSize * 2;
		}
	}
}
