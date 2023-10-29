package com.totn.mikecraft;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
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
	public static final String ver = "PRE 1.5 2023 Rebuild",title="Mikecraft";
	public static final String themeSong = "dovakiin.wav",levelSong = "clock_town.wav";
	
	public static int Height = 480,Width = 640,Char = 1,lives = 3,difficultyi = 3,optionMenu = 1;
	public static int displayHeight, displayWidth;
	public static String levelName[] = new String[12],charName[] = new String[4],
			difficulty[] = new String[4];
	public static int blockSize = Width / 10;
	public static double level = 1,volume=-5;
	public static boolean display = false,gameOver=false,released[] = new boolean[10],
			inDevelopment=false, debug=true, fullscreen = false,paused = false,
			music=true, sounds=true;
	
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

//	State for game status
    public static enum GameCurrentState 
    {
        MAIN_MENU, GAME, STAGE_SWAP, OPTIONS, PAUSE
    }
    public static GameCurrentState state = GameCurrentState.MAIN_MENU;
    
//	Setup the display
	public static void setUpDisplay(int Width, int Height)
	{
    	try 
    	{
//			Initialize variables and run display functions
    		displayHeight = Height;
    		displayWidth = Width;
            Display.setDisplayMode(new DisplayMode(displayWidth, displayHeight));
            Display.setFullscreen(fullscreen);
    		Display.setTitle(title + " "+ ver +" | Loading...");
            Display.create();
            System.out.println(Sys.getVersion());
    	} catch (LWJGLException e)
        {
//			Error checking for display generation
            e.printStackTrace();
            Display.destroy();
        }
    }
	
//	Update the display every tick
	public static void updateDisplay(int newWidth, int newHeight)
	{
//		Destroy the current display and build the new display.
		Display.destroy();
        setUpDisplay(newWidth,newHeight);
		fontInit(24);
        setCamera();

//		Reload textures. Later this should be moved to the level world class and run during world load every cycle.
        Textures.t();
	}

	
//
//	Consider moving the font setup and loading into its own Class file
//		public class FontHandler
	
//	Initialize the fonts
	public static void fontInit(int size)
	{
//		Load a default java font
		Font awtFont = new Font("Arial", Font.BOLD, size);
		font = new TrueTypeFont(awtFont, false);
		
		try 
		{
//			Try loading the fonts
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
	
//	Draw text around a specific y coord, centered on the screen
	public static void fontCenter(TrueTypeFont font, float y, String text)
	{		
		fontDrawString(font, Width / 2 - font.getWidth(text) / 2, y, text);
	}
	
//	Draw text at specific x and y coord
	public static void fontDrawString(TrueTypeFont font, float x, float y, String text)
	{
		font.drawString(x + 3, y + 3, text, Color.darkGray);
		font.drawString(x, y, text, Color.white);
	}
	
//	Main
	public static void main(String args[]) throws Exception 
	{
//		Sets up the display if it's not already set
		if(!display){setUpDisplay(Width, Height); display = true;}

//		Initialize all the textures to be used later in the game
//		This should be moved to the world files.
//		Each world should load its own textures, either from base textures or custom.
//		This is an engine. Move things where they need to be and not globally.
		Textures.t();
		
//		Load the entities, another method that should be moved to future World class under buildEntities method
//			Entities should be loaded from JSON loaded class
		setEntities();
		setCamera();
        GUI.centerObject(TitleBackOption, Width - blockSize, -Height / 2, 768, 1366);
		
//		Theme song
		MakeSound.initSounds();
//		There should be more state logic behind this. If the state changes to MAIN_MENU, play music
		MakeSound.theme.play();
		
//		Initializes the fonts
		fontInit(24);
		
//		While the display is kept open loop and update display each tick
		while(!Display.isCloseRequested())
		{
//			Check whether stages need to be changed or not
			Menus.checkInput();	

//			Move these to class methods like Display.setTitle() or setUpDsiplay.setCamera()
			setTitle();
			setCamera();
			MakeSound.RefreshMusic();
			
//			During game play these run
			if(state == GameCurrentState.GAME && !paused)
			{
//				Update to a class method inGame.draw()
				inGame();
			}
//			Otherwise the GUI needs to load
			else
			{
//				Update later to GUI.draw();
				GUI.drawBackground();
			}
//			Update and Sync
			Display.update();
			Display.sync(60);
		}
        Display.destroy();
        System.exit(1);
	}

//	General method for active game loop
//	Only runs if the game is in GAME state
	static void inGame()
	{
//		World.chooseLevel should load world level class from JSON object.
		World.chooseLevel();
		
//		Load/draw enemies from the world level JSON object
		if(enemy.isVisable())
		{
			enemy.draw();
		}
		
//		Draw the player sprite
		player.draw();
	}
	
//	Set the title of the screen
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
//		Clear screen
		glClear(GL_COLOR_BUFFER_BIT);
		
//		Modify projection matrix
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Width, Height, 0, 1, -1);
        
//		Modify model view matrix
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        
//		Jump 'n run camera movement
//			If the player moves beyond 50% of the page, advance the side scroll
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
	
//	Setup entities for the world
//		Should be moved to World class under World.buildEntities and loaded from level JSON
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
	
//	Method for printing debug variables to console
	public static void debugln(String text)
	{
		if(debug){System.out.println(text);}
	}
	
//	Wait
	public static void wait(double d)
	{
		try {
			Thread.sleep((long) (d * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}