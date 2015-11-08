package com.totn.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

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
	        clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
        	FloatControl gainControl = 
        			(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        	gainControl.setValue((float) -10);
        	
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
		if(!clip.isRunning())
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
		play();
	}
	
	public boolean isPlaying()
	{
		return clip.isRunning();
	}
}
