package com.totn.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

import com.totn.mikecraft.MainGame;

public abstract class AbstractSound 
{
	protected int ID;
	protected String name, filepath;
	protected AudioInputStream stream;
	protected AudioFormat format;
	protected DataLine.Info info;
	protected Clip clip;
	protected boolean isPlaying = false;
	protected File file;
	
	public AbstractSound(int ID, String name, String filepath) 
	{
		this.ID = ID;
		this.name = name;
		this.filepath = "res/sound/" + filepath;
		file = new File(this.filepath);
		
		try 
        {
			stream = AudioSystem.getAudioInputStream(new File(this.filepath));
	        format = stream.getFormat();
	        info = new DataLine.Info(Clip.class, format);
	        clip = (Clip)AudioSystem.getLine(info);
	        clip.open(stream);
	        
	        // Adjust the volume on the output line.
	        if( clip.isControlSupported( FloatControl.Type.MASTER_GAIN)) {
	            // If inside this if, the Master_Gain must be supported. Yes?
	            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	            // This line throws an exception. "Master_Gain not supported"
	            volume.setValue( (float) MainGame.volume );
	        }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
			e.printStackTrace();
		}
	}
	
	public int getID()
	{
		return ID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void play()
	{
		if(!clip.isRunning() && MainGame.sounds)
		{
			try 
	        {
				clip.close();
				stream.close();
				stream = AudioSystem.getAudioInputStream(file);
				clip.open(stream);
	        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
			{
	        	e.printStackTrace();
			}
	        clip.start();
		}
	}
	
	public void stop()
	{
		clip.stop();
	}
	
	public void restart()
	{
		stop();
		if(MainGame.sounds)
		{
			play();
		}
	}
	
	public boolean isPlaying()
	{
//		return false;
		return clip.isRunning();
	}
}
