package com.totn.mikecraft;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import com.totn.entity.Enemy;
import com.totn.entity.Player;
import com.totn.level.Textures;
import com.totn.level.World;

public class MainGame 
{	
//	Game Information
	public static final String ver = "PRE 1.3",title="Mikecraft";
	public static final String themeSong = "dovakiin.wav",levelSong = "clock_town.wav";
	
	public static int Height = 480,Width = 640,Char = 1,lives = 3,difficultyi = 3;
	public static int nHeight = -Height;
	public static String levelName[] = new String[12],charName[] = new String[4],
			difficulty[] = new String[4];
	public static final int blockSize = Width / 10;
	public static double level = 1,volume=60;
	public static boolean display = false,gameOver=false,released[] = new boolean[10],
			developmental=true,goToStageSwap;
	
//	Game Entities
	public static Player player;
	public static Enemy enemy;

//	Textures
	public static TrueTypeFont font,font2,font3;
	public static Texture t[] = new Texture[30];
	public static Texture Sky,Dirt,Brick,Planks_oak,Tnt,
		Gold,Redstone,Wheat7,Flag,Emerald,Grass,Stone;
	public static Texture SteveChar,MikeChar,MineChar,PlayerSkin,Steve2;
	public static Texture Title,TitleBack,Button[] = new Texture[5],button,buttonHover;

    public static enum State 
    {
        MAIN_MENU, GAME, STAGE_SWAP, OPTIONS
    }
    public static State state = State.MAIN_MENU;
    
	public static void setUpDisplay()
	{
    	try 
    	{
            Display.setDisplayMode(new DisplayMode(Width, Height));
            Display.setFullscreen(true);
    		Display.setTitle(title + " "+ ver +" | Loading...");
    		Display.setResizable(true);
            Display.create();
        } catch (LWJGLException e) 
        {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
    }
	
//	deltaTextures
//	Basically the dynamic textures
//	public static void dT() 
//	{	
//		try 
//		{
//			if(Mouse.getX() >= Width / 2 - 200 && Mouse.getX() <= Width / 2 + 183 && Mouse.getY() >= Height - 116 && Mouse.getY() <= Height - 68)
//			{			
//				Button[1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/buttonHover.png")));
//			} else {
//				Button[1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/button.png")));
//			}
//			if(Mouse.getX() >= Width / 2 - 200 && Mouse.getX() <= Width / 2 + 183 && Mouse.getY() >= Height / 2 && Mouse.getY() <= Height / 2 + 44)
//			{			
//				Button[2] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/buttonHover.png")));
//			} else {
//				Button[2] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/button.png")));
//			}
//			if(Mouse.getX() >= Width / 2 - 200 && Mouse.getX() <= Width / 2 + 183 && Mouse.getY() >= Height / 2 + 60 && Mouse.getY() <= Height / 2 + 108)
//			{			
//				Button[3] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/buttonHover.png")));
//			} else {
//				Button[3] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/button.png")));
//			}
//			if(Mouse.getX() >= Width / 2 - 200 && Mouse.getX() <= Width / 2 + 183 && Mouse.getY() >= Height / 2 - 68 && Mouse.getY() <= Height / 2 - 20)
//			{			
//				Button[4] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/buttonHover.png")));
//			} else {
//				Button[4] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/button.png")));
//			}
//		} catch (FileNotFoundException e)
//		{
//			e.printStackTrace();
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//	}
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
//	Render the main menu fonts
	public static void fontRender()
	{			
		if (state == State.MAIN_MENU)
		{
			font3.drawString(Width / 4 + blockSize * 2 - 5, Height / 4 - 52, "Start", Color.white);
			font3.drawString(Width / 4 + blockSize * 2 - 25, Height / 4 + 12, "Options", Color.white);
			font3.drawString(Width / 4 + blockSize * 2 - 5, Height / 4 + 139, "Quit", Color.white);
		}
	}
	public static void main(String args[]) throws Exception 
	{
//		Sets up the display if it's not already set
		if(!display){setUpDisplay(); display = true;}
		
//		Initializes the fonts
		fontInit(24);
		
//		Creates the new entities
		player = new Player();
		enemy = new Enemy();
		
//		Initialize all the textures to be used later in the game
		Textures.t();
		
//		Theme song
		MakeSound.initSounds();
		
		while(!Display.isCloseRequested())
		{		
			System.out.println(MakeSound.themeSound.isPlaying + " | " + MakeSound.levelSound.isPlaying);
//			Check whether stages need to be changed or not
			checkInput();	
			setTitle();
			setCamera();
			if(state == State.MAIN_MENU||state==State.OPTIONS)
			{
				MakeSound.themeSound.play();
			} else {
				MakeSound.themeSound.pause();
			}
//			During game play these run
			if(state == State.GAME)
			{
				inGame();
			}
			
//			Otherwise the GUI needs to load
			else
			{
				GUI.drawBackground();	
//				dT();
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
		if(enemy.toDraw){enemy.draw();}
		player.draw();
	}
	static void setTitle()
	{   
//		Find the player's position
		int disX = (int) (Player.x + Width) / Width;
		int disXDec = (int) Math.ceil((Player.x / blockSize) + 9 - disX * 10);
		int disY = (int) Player.y / blockSize;
		String mousePosition = "Mouse X: " + Mouse.getX() + " | Mouse Y: " + Mouse.getY();
//		Game Title
		if(!developmental){Display.setTitle(title + " " + ver + " | " + Gravity.score + " | " + lives);}
//		Dev Title
		else {Display.setTitle(title + " DEV "+ver+" | " + level + " | " + (disX - 1) + "." + disXDec + " , "+ disY + " | Score: " + Gravity.score + " | Lives: " + lives + " | " + mousePosition);}
	}
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
        
		if (Player.x >= Width / 2)
        {
        	if (Player.y >= Height / 2) 
        	{
        		glTranslated(-Player.x + Width / 2, Player.y - Height / 2, 0);
        	} else 
        	{
        		glTranslated(-Player.x + Width / 2, 0, 0);
        	}
        }
        glEnable(GL_TEXTURE_2D);
	}
	static void checkInput() 
	{
		switch (state) 
		{
		case OPTIONS:
			if (Mouse.getX() >= 122 && Mouse.getX() <= 504 && Mouse.getY() >= 300 && Mouse.getY() <= 348)
			{
				if ( Mouse.isButtonDown(0)  && released[0]){
				released[0] = false;
				MakeSound.playSound("menu_option.wav");
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
			if (Mouse.getX() >= 122 && Mouse.getX() <= 504 && Mouse.getY() >= 236 && Mouse.getY() <= 284 && Mouse.isButtonDown(0) && released[0])
			{
				released[0] = false;
				MakeSound.playSound("menu_option.wav");
				if (difficultyi >= 1)
				{
					difficultyi = difficultyi-1;
				} else {
					difficultyi = 3;
				}
			} else if(!Mouse.isButtonDown(0)){
				released[0] = true;
			}
			if (Mouse.getX() >= 122 && Mouse.getX() <= 504 && Mouse.getY() >= 172 && Mouse.getY() <= 220) 
			{
				if (released[0] && Mouse.isButtonDown(0))
				{
					released[0] = false;
					MakeSound.playSound("menu_option.wav");
					state = State.MAIN_MENU;
					GUI.drawBackground();
				} else if(!Mouse.isButtonDown(0)){
					released[0] = true;
				}
			}
			break;
		case MAIN_MENU:
			if (Mouse.getY() >= 364 && Mouse.getY() <= 412 && Mouse.getX() >= 122 && Mouse.getX() <= 504 && Mouse.isButtonDown(0)) 
			{
				if (released[0])
				{
					released[0] = false;
					MakeSound.playSound("menu_option.wav");
					state = State.STAGE_SWAP;
					if(level==1){lives = difficultyi;}
					GUI.drawBackground();
				} else if(!Mouse.isButtonDown(0)){
					released[0] = true;
				}
			}
			if (Mouse.getX() >= 122 && Mouse.getX() <= 504 && Mouse.getY() >= 300 && Mouse.getY() <= 348 && Mouse.isButtonDown(0)) 
			{	
				if (released[0])
				{
					released[0] = false;
					MakeSound.playSound("menu_option.wav");
					state = State.OPTIONS;
					GUI.drawBackground();
				} else if(!Mouse.isButtonDown(0)){
					released[0] = true;
				}
			}
			if ((Mouse.getX() >= 122 && Mouse.getX() <= 504 && Mouse.getY() >= 172 && Mouse.getY() <= 220 && Mouse.isButtonDown(0) && released[0]) || (Keyboard.isKeyDown(1) && released[1])) 
			{
				Display.destroy();
				System.exit(1);
			} else if (!Mouse.isButtonDown(0))
			{
				released[0] = true;
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
        			break; 
        		}
			}
		case GAME:
			if(Keyboard.isKeyDown(1))
			{
				released[1] = false;
				player.x = 100;
				player.y = blockSize * 2;
				state = State.MAIN_MENU;
				GUI.drawBackground();
			}
			if(goToStageSwap)
			{
				state = State.STAGE_SWAP;
				goToStageSwap=false;
			}
		default:
			break;
		}
	}
}
