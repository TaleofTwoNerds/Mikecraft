package levels;

import static mikecraft.MainGame.*;
import mikecraft.Gravity;
import mikecraft.MainGame.State;

public class WorldOneFive extends Gravity 
{
	public static float decell = (float) 0.8;
	private static World.Back dirtOne,dirtTwo;
	private static World.Block bridge[] = new World.Block[10],back;
	private static World.Ground ground[] = new World.Ground[5];
	
	public static void main()
	{
		drawBackground();
		gravitation();	
		render();
	}

	private static void drawBackground() 
	{
		back = new World.Block(Sky,0, 0, -Height * 2, Width * 10);
		dirtOne = new World.Back(Stone,Width / 2 + BlockSize * 4, BlockSize * 4, BlockSize * 2, BlockSize * 3);
		dirtTwo = new World.Back(Stone,Width / 2 + BlockSize * 6, BlockSize * 5, BlockSize,BlockSize );
		
		ground[1] = new World.Ground(Dirt,0, BlockSize * 2, BlockSize * 2, Width * 10);
//		ground[2] = new World.Ground(Dirt, Width * 3 + BlockSize * 4, BlockSize * 2, BlockSize * 2, Width * 6);
		}

	private static void render() 
	{
		back.draw();
		ground[1].draw();
		fontRender();
	}
	
	@SuppressWarnings("deprecation")
	private static void fontRender()
	{

	}
	
	public static void logic(int delta) 
	{
		
	}

	private static void gravitation() 
	{
		endLogic(0);
		if ( x >= Width * 9)
		{
			dx = 0;
			level = 0;
			state = State.STAGE_SWAP;
			Gravity.emeraldOne = true;
			Gravity.emeraldTwo = true;
			Gravity.emeraldThree = true;
			Gravity.emeraldFour = true;
			Gravity.emeraldFive = true;
			Gravity.emeraldSix = true;
			Gravity.emeraldSeven = true;
			player.x= 100;
			player.y = BlockSize * 2;
		}
	}
}		
	