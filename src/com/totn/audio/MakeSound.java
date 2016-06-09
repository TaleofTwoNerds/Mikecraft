package com.totn.audio;

import com.totn.mikecraft.MainGame;

public class MakeSound extends MainGame
{
	public static Sound theme,jump,menuOption,courseClear,worldClear,
					stomp, lostALife, gameOver, clockTown;
	
	public static void initSounds()
	{
		theme = new Sound(0, "theme", "dovakiin.wav");
		jump = new Sound(1, "jump", "jump.wav");
		menuOption = new Sound(2, "menuOption", "menu_option.wav");
		courseClear = new Sound(3, "courseClear", "course_clear.wav");
		worldClear = new Sound(4, "worldClear", "world_clear.wav");
		stomp = new Sound(5, "stomp", "stomp.wav");
		lostALife = new Sound(6, "lostALife", "lost_a_life.wav");
		gameOver = new Sound(7, "gameOver", "game_over.wav");
		clockTown = new Sound(8, "clockTown", "clock_town.wav");
	}
	
	public static void clearSounds()
	{
		theme.stop();
		jump.stop();
		menuOption.stop();
		courseClear.stop();
		worldClear.stop();
		stomp.stop();
		lostALife.stop();
		gameOver.stop();
		clockTown.stop();
	}

	public static class Sound extends AbstractSound
	{
		public Sound(int ID, String name, String filepath)
		{
			super(ID, name, filepath);
		}
	}
	
	public static void RefreshMusic()
	{
		if(sounds)
		{
			
		} else {
			clearSounds();
		}
	}
}