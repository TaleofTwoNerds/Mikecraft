package level;

import static mikecraft.MainGame.*;
import mikecraft.Gravity;
import mikecraft.MainGame.State;
import mikecraft.Player;

import org.newdawn.slick.Color;

public class WorldOneThree extends Gravity 
{
	public static float decell = (float) 0.8;
	private static World.Back dirtOne,dirtTwo;
	private static World.Block bridge[] = new World.Block[11],back;
	private static World.Ground ground[] = new World.Ground[6];
	
	public static void main()
	{
		drawBackground();
		gravitation();	
		render();
	}

	private static void drawBackground() 
	{
		enemy.setBounds(Width * 4 + blockSize * 2, Width * 5 + blockSize * 2);

		back = new World.Block(Sky,0, 0, -Height * 2, Width * 10);
		dirtOne = new World.Back(Stone,Width / 2 + blockSize * 4, blockSize * 4, blockSize * 2, blockSize * 3);
		dirtTwo = new World.Back(Stone,Width / 2 + blockSize * 6, blockSize * 5, blockSize,blockSize );
		
		ground[1] = new World.Ground(Dirt,0, blockSize * 2, blockSize * 2, Width * 2 - blockSize);
		ground[2] = new World.Ground(Dirt, Width * 2 + blockSize * 3, blockSize * 2, blockSize * 2, Width * 2 - blockSize);
		ground[3] = new World.Ground(Dirt, Width * 4 + blockSize * 2, blockSize, blockSize * 2, Width);
		ground[4] = new World.Ground(Dirt, Width * 5 + blockSize * 2, blockSize * 2, blockSize * 2, Width / 2);
		ground[5] = new World.Ground(Dirt, Width * 6 + blockSize * 6, blockSize * 2, blockSize * 2, Width * 5);

		bridge[1] = new World.Block(Grass, Width /2 + blockSize * 2, blockSize * 3, blockSize, blockSize * 2);
		bridge[2] = new World.Block(Grass, Width /2 + blockSize * 3, blockSize * 4, blockSize, blockSize * 2);
		bridge[3] = new World.Block(Grass, Width /2 + blockSize * 4, blockSize * 5, blockSize, blockSize * 2);
		bridge[4] = new World.Block(Grass, Width, blockSize * 6, blockSize, blockSize * 2);
		bridge[5] = new World.Block(Planks_oak, Width + blockSize * 4, blockSize * 6, blockSize, blockSize * 4);
		bridge[6] = new World.Block(Planks_oak, Width * 2 + blockSize * 2, blockSize * 6, blockSize, blockSize * 4);
		bridge[7] = new World.Block(Planks_oak, Width * 3, blockSize * 5, blockSize, blockSize * 4);
		bridge[8] = new World.Block(Planks_oak, Width * 4 + blockSize * 3, blockSize * 5, blockSize, Width - blockSize * 2);
		bridge[9] = new World.Block(Planks_oak, Width * 5 + blockSize * 9, blockSize * 2, blockSize, Width / 2);
	}

	private static void render() 
	{
		back.draw();
		ground[1].draw();
		ground[2].draw();
		ground[3].draw();
		ground[4].draw();
		ground[5].draw();
		dirtOne.draw();
		dirtTwo.draw();
		bridge[1].draw();
		bridge[2].draw();
		bridge[3].draw();
		bridge[4].draw();
		bridge[5].draw();
		bridge[6].draw();
		bridge[7].draw();
		bridge[8].draw();
		bridge[9].draw();
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
		if((y <= blockSize && y >= blockSize && x >=  Width * 3))
		{ 
			if(player.getX() <= Width * 4 + blockSize * 2 + SteveX)
			{
				if (dx <= 0)
				{
					dx = 0;
				}
				x = Width * 4 + blockSize * 2 + SteveX;
				movement();
			} else if (player.getX() >= Width * 5 + blockSize * 2 - SteveX){
				if (dx >= 0)
				{
					dx = 0;
				}
				x = Width * 5 + blockSize * 2 - SteveX;
			}
		} if ( x >= Width * 9)
		{
			dx = 0;
			level = 4;
			state = State.STAGE_SWAP;
			Gravity.emeraldOne = true;
			Gravity.emeraldTwo = true;
			Gravity.emeraldThree = true;
			Gravity.emeraldFour = true;
			Gravity.emeraldFive = true;
			Gravity.emeraldSix = true;
			Gravity.emeraldSeven = true;
			player.x= 100;
			player.y = blockSize * 2;
			player.dx = 0;
			player.dy = 0;
			enemy.setPos(Width * 4, blockSize * 2);
			enemy.setVisable(true);
		}
	}
}		
	