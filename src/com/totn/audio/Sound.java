package com.totn.audio;

public interface Sound 
{
	public int getID();
	public String getName();
	public void play();
	public void stop();
	public void restart();
	public boolean isPlaying();
}
