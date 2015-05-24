package com.totn.mikecraft;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.lwjgl.opengl.Display;

import com.totn.level.World;
import com.totn.mikecraft.MainGame.State;

public class MakeSound 
{
	private static double vLevel = -80;
	public static Sound levelSound,themeSound,menuOption,gameOver,lifeLost,jump,stomp,
		worldClear,courseClear,coin;
	
	public static void initSounds()
	{		
		levelSound = new Sound(MainGame.levelSong);
		themeSound = new Sound(MainGame.themeSong);
	}
	public static void volumeControl()
	{
		if(MainGame.volume>=-60){vLevel = MainGame.volume - 80;}
		else{vLevel=0;}
	}
	public static class Sound
	{
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        static Clip clip;
        String filepath;
		boolean isPlaying;
        
		public Sound(String filename)
		{			
			filename = "res/sound/" + filename;

			System.out.println(filename);
	        volumeControl();
	    	
	        try 
	        {
				stream = AudioSystem.getAudioInputStream(new File(filename));
		        format = stream.getFormat();
		        info = new DataLine.Info(Clip.class, format);
		        clip = (Clip) AudioSystem.getLine(info);
		        clip.open(stream);
	        	FloatControl gainControl = 
	        			(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        	gainControl.setValue((float) vLevel);
	        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
	        {
				e.printStackTrace();
			}
		}
		public void play()
		{
			if(!isPlaying)
			{
				clip.start();
				isPlaying =true;
			}
		}
		public void pause()
		{
			if(isPlaying)
			{
				clip.stop();
				isPlaying= false;
			}
		}
		public void restart()
		{
			if(isPlaying)
			{
				clip.stop();
				isPlaying= false;
			} if (!isPlaying)
			{
				clip.start();
				isPlaying= true;
			}
		}
	}
	public static void playSound(String filename)
    {
    	filename = "res/sound/" + filename;
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        Clip clip;
       
        volumeControl();
        
        try 
        {
			stream = AudioSystem.getAudioInputStream(new File(filename));
	        format = stream.getFormat();
	        info = new DataLine.Info(Clip.class, format);
	        clip = (Clip) AudioSystem.getLine(info);
	        clip.open(stream);
        	FloatControl gainControl = 
        			(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        	gainControl.setValue((float) vLevel);
	        clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
			e.printStackTrace();
		}
    }
}