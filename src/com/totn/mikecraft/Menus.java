package com.totn.mikecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.totn.audio.MakeSound;

public class Menus extends MainGame 
{
	static void checkInput() 
	{
		switch (state) 
		{
		case OPTIONS:
			
//			Level selector button
			if(optionMenu == 0)
			{				
				if (buttonAbstract[2].isHovering() && inDevelopment)
				{
					if ( Mouse.isButtonDown(0)  && released[0]){
						released[0] = false;
						MakeSound.menuOption.play();
						if (level <= 4)
						{
							level++;
						} else {
							level = 1;
						}
					}	
				} else if(!Mouse.isButtonDown(0)){
					released[0] = true;
				}
				
//				Difficulty Button
				if (buttonAbstract[3].isHovering() && Mouse.isButtonDown(0) && released[0])
				{
					released[0] = false;
					MakeSound.menuOption.play();
					if (difficultyi >= 1)
					{
						difficultyi = difficultyi-1;
					} else {
						difficultyi = 3;
					}
				} else if(!Mouse.isButtonDown(0)){
					released[0] = true;
				}
				
//				To Title button
				if (buttonAbstract[4].isHovering()) 
				{
					if (released[0] && Mouse.isButtonDown(0))
					{
						released[0] = false;
						MakeSound.menuOption.play();
						optionMenu = 0;
						state = State.MAIN_MENU;
					} else if(!Mouse.isButtonDown(0)) {
						released[0] = true;
					}
				}
			} else if(optionMenu == 1)
			{
				if (buttonAbstract[2].isHovering())
				{
					if (released[0] && Mouse.isButtonDown(0))
					{
						released[0] = false;
						MakeSound.menuOption.play();
						if(!fullscreen)
						{
							fullscreen = true;
							MainGame.updateDisplay(1366, 768);
						}
						else{
							fullscreen = false;
							MainGame.updateDisplay(640, 480);
						}
						
						GUI.drawBackground();
					} else if(!Mouse.isButtonDown(0)) {
						released[0] = true;
					}
				}
				if (buttonAbstract[4].isHovering()) 
				{
					if (released[0] && Mouse.isButtonDown(0))
					{
						released[0] = false;
						MakeSound.menuOption.play();
						state = State.MAIN_MENU;
						GUI.drawBackground();
					} else if(!Mouse.isButtonDown(0)) {
						released[0] = true;
					}
				}
			}
			break;
		case MAIN_MENU:
			if (buttonAbstract[1].isHovering() && Mouse.isButtonDown(0)) 
			{
				if (released[0])
				{
					released[0] = false;
					MakeSound.menuOption.play();
					state = State.STAGE_SWAP;
					enemy.setVisable(true);
					enemy.setY(blockSize * 2);
					if(level==1){lives = difficultyi;}
					GUI.drawBackground();
				} else if(!Mouse.isButtonDown(0)){
					released[0] = true;
				}
			}
			if (buttonAbstract[2].isHovering() && Mouse.isButtonDown(0)) 
			{	
				if (released[0])
				{
					released[0] = false;
					MakeSound.menuOption.play();
					state = State.OPTIONS;
					optionMenu = 0;
					GUI.drawBackground();
				} else if(!Mouse.isButtonDown(0)){
					released[0] = true;
				}
			}
			if ((buttonAbstract[4].isHovering() && Mouse.isButtonDown(0) && released[0]) || (Keyboard.isKeyDown(1) && released[1])) 
			{
				Display.destroy();
				System.exit(1);
			} else 
			{
				if (!Mouse.isButtonDown(0))
				{
					released[0] = true;
				}
				if(!Keyboard.isKeyDown(1))
				{
					released[1] = true;
				}
			}
			break;
			
		case STAGE_SWAP:
			if (Keyboard.isKeyDown(28)) 
			{
        		if (level == 0)
        		{
        			level = 1;
        			state = State.MAIN_MENU;
        		} else
        		{
        			state = State.GAME;
        			if(MakeSound.courseClear.isPlaying())
        			{
        				MakeSound.courseClear.stop();
        			}
        			break; 
        		}
			}
			
		case GAME:
			if(Keyboard.isKeyDown(1))
			{
				released[1] = false;
				player.setLocation(200, blockSize * 2);

    			if(MakeSound.clockTown.isPlaying())
    			{
    				MakeSound.clockTown.stop();
    			}
    			 
				state = State.MAIN_MENU;
				GUI.drawBackground();
			}
		default:
			break;
		}
	}
}
