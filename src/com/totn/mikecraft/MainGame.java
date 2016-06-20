package com.totn.mikecraft;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import com.totn.audio.MakeSound;
import com.totn.entity.Enemy;
import com.totn.entity.Player;
import com.totn.entity.Button;
import com.totn.level.Textures;
import com.totn.level.World;

public class MainGame 
{	
//	Game Information
	public static final String ver = "PRE 1.3.1 LB",title="Mikecraft";
	public static final String themeSong = "dovakiin.wav",levelSong = "clock_town.wav";
	
	public static int Height = 480,Width = 640,Char = 1,lives = 3,difficultyi = 3,optionMenu = 1;
	public static int displayHeight, displayWidth;
//	Cannot even remember why I needed nHeight.
	public static String levelName[] = new String[12],charName[] = new String[4],
			difficulty[] = new String[4];
	public static int blockSize = Width / 10;
	public static double level = 1,volume=0;
	public static boolean display = false,gameOver=false,released[] = new boolean[10],
			inDevelopment=true, debug=true, fullscreen = false,paused = false,
			music=false, sounds=false;
	
//	Game Entities
	public static Player player;
	public static Enemy enemy;
	public static Button buttonAbstract[] = new Button[12];

//	Textures
	public static TrueTypeFont font,font2,font3;
	public static Texture t[] = new Texture[30];
	public static Texture Sky,Dirt,Brick,Planks_oak,Tnt,
		Gold,Redstone,Wheat7,Flag,Emerald,Grass,Stone;
	public static Texture SteveChar,MikeChar,MineChar,PlayerSkin,Steve2;
	public static Texture Title,TitleBack,TitleBackOption,TitleBackSwap,Button[] = new Texture[5],button,buttonHover;

    public static enum State 
    {
        MAIN_MENU, GAME, STAGE_SWAP, OPTIONS
    }
    public static State state = State.MAIN_MENU;
    
	public static void setUpDisplay(int Width, int Height)
	{
    	try 
    	{
    		displayHeight = Height;
    		displayWidth = Width;
            Display.setDisplayMode(new DisplayMode(displayWidth, displayHeight));
            Display.setFullscreen(fullscreen);
    		Display.setTitle(title + " "+ ver +" | Loading...");
            Display.create();
            System.out.println(Sys.getVersion());
    	} catch (LWJGLException e)
        {
            e.printStackTrace();
            Display.destroy();
        }
    }
	
	public static void updateDisplay(int newWidth, int newHeight)
	{
		Display.destroy();
        setUpDisplay(newWidth,newHeight);
		fontInit(24);
        Textures.t();
        setCamera();
	}

//	Initialize the fonts
	public static void fontInit(int size)
	{
		// load a default java font
		Font awtFont = new Font("Arial", Font.BOLD, size);
		font = new TrueTypeFont(awtFont, false);
		
		try 
		{
			InputStream inputStream	= ResourceLoader.getResourceAsStream("res/font/Minecraftia.ttf");
			InputStream inputStream2	= ResourceLoader.getResourceAsStream("res/font/Minecraftia.ttf");

			Font awtFont3 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont3 = awtFont3.deriveFont(24f); // set font size
			font3 = new TrueTypeFont(awtFont3, false);
	 
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream2);
			awtFont2 = awtFont2.deriveFont(48f); // set font size
			font2 = new TrueTypeFont(awtFont2, false);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}
	
	public static void fontCenter(TrueTypeFont font, float y, String text)
	{		
		fontDrawString(font, Width / 2 - font.getWidth(text) / 2, y, text);
	}
	
	public static void fontDrawString(TrueTypeFont font, float x, float y, String text)
	{
		font.drawString(x + 3, y + 3, text, Color.darkGray);
		font.drawString(x, y, text, Color.white);
	}
	
	public static void main(String args[]) throws Exception 
	{
//		Sets up the display if it's not already set
		if(!display){setUpDisplay(Width, Height); display = true;}

//		Initialize all the textures to be used later in the game
		Textures.t();
		
		setEntities();
		setCamera();
        GUI.centerObject(TitleBackOption, Width - blockSize, -Height / 2, 768, 1366);
		
//		Theme song
		MakeSound.initSounds();
		MakeSound.theme.play();
		
//		Initializes the fonts
		fontInit(24);
		
		while(!Display.isCloseRequested())
		{
//			System.out.println(MakeSound.themeSound.isPlaying + " | " + MakeSound.levelSound.isPlaying);
//			Check whether stages need to be changed or not
			Menus.checkInput();	
			setTitle();
			setCamera();
			MakeSound.RefreshMusic();
			
//			During game play these run
			if(state == State.GAME && !paused)
			{
				inGame();
			}
//			Otherwise the GUI needs to load
			else
			{
				GUI.drawBackground();
			}
//			Update and Sync
			Display.update();
			Display.sync(60);
		}
        Display.destroy();
        System.exit(1);
	}

	static void inGame()
	{
		World.chooseLevel();
		if(enemy.isVisable())
		{
			enemy.draw();
		}
		player.draw();
	}
	static void setTitle()
	{   
//		Find the player's position
		int disX = (int) (player.getX() + Width) / Width;
		int disXDec = (int) Math.ceil((player.getX() / blockSize) + 9 - disX * 10);
		int disY = (int) player.getY() / blockSize;
		String mousePosition = "Mouse X: " + Mouse.getX() + " | Mouse Y: " + Mouse.getY();
//		Game Title
		if(!inDevelopment){Display.setTitle(title + " " + ver + " | " + Physics.score + " | " + lives);}
//		Dev Title
		else {Display.setTitle(title + " DEV "+ver+" | " + level + " | " + (disX - 1) + "." + disXDec + " , "+ disY + " | Score: " + Physics.score + " | Lives: " + lives + " | " + mousePosition);}
	}
	
//	Look into modifying the camera translation so I can add a HUD which "sticks"
//	to the corner of the camera. Perhaps add a camera class (i.e. Camera.getX()) 
//	or similar
	
	static void setCamera() 
	{
		//clear screen
		glClear(GL_COLOR_BUFFER_BIT);
		//modify projection matrix
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Width, Height, 0, 1, -1);
        
        //modify model view matrix
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        
		if (player.getX() >= Width / 2)
        {
        	if (player.getY() >= Height / 2) 
        	{
        		glTranslated(-player.getX() + Width / 2, player.getY() - Height / 2, 0);
        	} else 
        	{
        		glTranslated(-player.getX() + Width / 2, 0, 0);
        	}
        }
        glEnable(GL_TEXTURE_2D);
	}
	
	static void setEntities()
	{
//		Creates the new entities
		player = new Player(null, blockSize, blockSize * 2, blockSize * 2, blockSize);
		enemy = new Enemy(null, blockSize, blockSize * 2, blockSize, blockSize);
		
		buttonAbstract[1] = new Button(null, Width / 2, Height / 2 + blockSize * 2, 48, blockSize * 6);
		buttonAbstract[2] = new Button(null, Width / 2, Height / 2 + blockSize * 1, 48, blockSize * 6);
		buttonAbstract[3] = new Button(null, Width / 2, Height / 2, 48, blockSize * 6);
		buttonAbstract[4] = new Button(null, Width / 2, Height / 2 - blockSize, 48, blockSize * 6);

		Physics.createEmeralds();
		
		player.defaultSettings();
	}
	
	public static void debugln(String text)
	{
		if(debug){System.out.println(text);}
	}
	
	public static void wait(double d)
	{
		try {
			Thread.sleep((long) (d * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}