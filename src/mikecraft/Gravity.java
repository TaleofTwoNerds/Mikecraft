package mikecraft;

import static mikecraft.MainGame.*;
import levels.World;
import mikecraft.MainGame.State;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Gravity extends Player {
	
	public static boolean emeraldOne = true, emeraldTwo = true, 
			emeraldThree = true, emeraldFour = true, emeraldFive = true, 
			emeraldSix = true, emeraldSeven = true;
	public static int score = 0;
	
	public static void logic()
	{
		if (y >= 0){
		if (state == State.GAME)
		{
		x+=dx;
		y+=dy;
		dy -= .4;
		int disX;
		int disXDec;
		int disY;
		disX = (int) (x + Width) / Width;
		disXDec = (int) Math.ceil((x / BlockSize) + 9 - disX * 10);
		disY = (int) y / BlockSize;
		Display.setTitle("Mikecraft a1.0 | " + level + " | " + (disX - 1) + "." + disXDec + " ,"+ disY + " | " + score);
		World.chooseLevel();
		if(Keyboard.isKeyDown(Keyboard.KEY_A)  || Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		{
			dx = -Width / 100;
		} if(Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		{
			dx = Width / 100;
		}
		jumpWasPressed = jumpPressed;
		jumpPressed = Keyboard.isKeyDown(Keyboard.KEY_SPACE) || Keyboard.isKeyDown(Keyboard.KEY_UP);
		
		}
		}
	}
}
