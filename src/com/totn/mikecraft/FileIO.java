package com.totn.mikecraft;

import static com.totn.mikecraft.MainGame.level;
import static com.totn.mikecraft.MainGame.levelName;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileIO
{	
	private static String path = null;
	private boolean append_to_file = false;
	
	public static void ReadMain(String file_path)
	{
//		System.out.println("Beginning...");
		try 
		{
			FileIO file = new FileIO(file_path);
			String[] aryLines = file.OpenFile();
			
			int i;
//			System.out.println("");
			for(i = 0; i < aryLines.length; i++)
			{	
//				System.out.println(aryLines[i]);
			}
		} 
		catch ( IOException e ) 
		{
			System.out.println( e.getMessage() );
		}
	}
	
	public FileIO(String file_path) 
	{
		path = file_path;
	}

	public static String[] OpenFile() throws IOException
	{
//		System.out.println("File Opening...");
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		int numberOfLines = ReadLines();
		
		String[] textData =  new String[numberOfLines];
		
		int i;
		
		for (i=0; i < numberOfLines; i++)
		{
			textData[i] = textReader.readLine();
		}
		textReader.close();
		return textData;
	}

	public static int ReadLines() throws IOException
	{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		@SuppressWarnings("unused")
		String aLine;
		int numberOfLines = 0;
		while ((aLine = textReader.readLine()) != null)
		{
			numberOfLines++;
		}
		textReader.close();
//		System.out.println(numberOfLines + " Lines Read...");
		return numberOfLines;
	}
	public static String ReadLine(int line_number) throws IOException
	{
		String line = null;
		return line;
	}
}
