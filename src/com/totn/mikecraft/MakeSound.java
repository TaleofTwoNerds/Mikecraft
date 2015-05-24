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
	
	public static void volumeControl()
	{
		if(MainGame.volume>=-60){vLevel = MainGame.volume - 80;}
		else{vLevel=0;}
	}
    public static void playSound(String filename)
    {
    	filename = "res/sound/" + filename;
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        Clip clip;
       
        volumeControl();

    	boolean isPlaying;
    	
        try 
        {
			stream = AudioSystem.getAudioInputStream(new File(filename));
	        format = stream.getFormat();
	        info = new DataLine.Info(Clip.class, format);
	        clip = (Clip) AudioSystem.getLine(info);
	        clip.open(stream);
	        isPlaying=true;
        	FloatControl gainControl = 
        			(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        	gainControl.setValue((float) vLevel);
	        clip.start();
	        if(filename.contains("dovakiin"))
	        {
	        	while(MainGame.state==State.MAIN_MENU||MainGame.state==State.OPTIONS)
	        	{
	        		MainGame.checkInput();	
	        		MainGame.setTitle();
	        		MainGame.setCamera();
	    			if(MainGame.state == State.GAME)
	    			{
	    				World.chooseLevel();
	    				if(MainGame.enemy.toDraw){MainGame.enemy.draw();}
	    				MainGame.player.draw();
	    			}
	    			else
	    			{
	    				GUI.drawBackground();	
	    				MainGame.dT();
	    			}
//	    			System.out.println(player.x + " | " + enemy.x);
//	    			System.out.println("Mouse x: " + Mouse.getX() + " Mouse y: " + Mouse.getY());
	    			Display.update();
	    			Display.sync(60);
	        	}
	        	System.out.println(filename);
	        	System.out.println("While Ended");
	        	clip.stop();
	        }
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}