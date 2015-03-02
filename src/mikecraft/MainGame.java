package mikecraft;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import levels.Textures;
import levels.World;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

@SuppressWarnings("deprecation")
public class MainGame 
{	
	public static final String ver = "PRE 1.1";
	public static int Height = 480,Width = 640,Char = 1,levelTwo;
	public static int nHeight = -Height;
	public static String levelName[] = new String[12];
	public static final int BlockSize = Width / 10;
	public static Player player;
	public static Enemy enemy;
	public static boolean display = false,gameOver=false,released[] = new boolean[10];
	public static double level = 1;
	public static TrueTypeFont font,font2;

	public static Texture t[] = new Texture[30];
	public static Texture Sky,Dirt,Brick,Planks_oak,Tnt,
		Gold,Redstone,Wheat7,Flag,Emerald,Grass,Stone;
	public static Texture SteveChar,MikeChar,MineChar,PlayerSkin;
	public static Texture Title,TitleBack,StartButton,QuitButton,Button;

    public static enum State 
    {
        MAIN_MENU, GAME, STAGE_SWAP, OPTIONS
    }
    public static State state = State.MAIN_MENU;
    
	@SuppressWarnings("static-access")
	public static void setUpDisplay()
	{
    	try 
    	{
            Display.setDisplayMode(new DisplayMode(Width, Height));
//            Display.setFullscreen(true);
    		int disX;
    		int disXDec;
    		int disY;
    		disX = (int) (player.x + Width) / Width;
    		disXDec = (int) Math.ceil((player.x / BlockSize) + 9 - disX * 10);
    		disY = (int) player.y / BlockSize;
    		Display.setTitle("Mikecraft "+ ver +" | Loading...");
            Display.create();
        } catch (LWJGLException e) 
        {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
    }
	
	
	public static void dT() 
	{	
		try {
			if(Mouse.getX() >= 122 && Mouse.getX() <= 504 && Mouse.getY() >= 364 && Mouse.getY() <= 412)
			{			
				StartButton = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/buttonHover.png")));
			} else {
				StartButton = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/button.png")));
			}
			if(Mouse.getX() >= 122 && Mouse.getX() <= 504 && Mouse.getY() >= 236 && Mouse.getY() <= 284)
			{			
				QuitButton = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/buttonHover.png")));
			} else {
				QuitButton = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/button.png")));
			}
			if(Mouse.getX() >= 122 && Mouse.getX() <= 504 && Mouse.getY() >= 300 && Mouse.getY() <= 348)
			{			
				Button = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/buttonHover.png")));
			} else {
				Button = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/button.png")));
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void fontInit(int size)
	{
		// load a default java font
		Font awtFont = new Font("Arial", Font.BOLD, size);
		Font awtFont2 = new Font("Arial", Font.PLAIN, 48);
		font = new TrueTypeFont(awtFont, false);
		font2 = new TrueTypeFont(awtFont2, false);
	}
	public static void fontRender()
	{			
		if (state == State.MAIN_MENU)
		{
			font.drawString(Width / 4 + BlockSize * 2 - 5, Height / 4 - 47, "Start", Color.white);
			font.drawString(Width / 4 + BlockSize * 2 - 25, Height / 4 + 17, "Options", Color.white);
			font.drawString(Width / 4 + BlockSize * 2 - 5, Height / 4 + 81, "Quit", Color.white);
		}
	}
	public static void main(String args[]) throws Exception 
	{
		if(!display){setUpDisplay(); display = true;}
		player = new Player();
		fontInit(24);
		Textures.t();
		while(!Display.isCloseRequested())
		{			
			checkInput();	
			setTitle();
			setCamera();
			if(state == State.GAME)
			{
				World.chooseLevel();
				player.draw();
				Enemy.draw();
				}
			else
			{
				GUI.drawBackground();	
				dT();
			}	
//			System.out.println(Mouse.isButtonDown(0));
//			System.out.println(level + " " + (int)(level-1)*10);
//			System.out.println(levelName[(int) ((level-1)*10)]);
//			System.out.println("Mouse x: " + Mouse.getX() + " Mouse y: " + Mouse.getY());
			Display.update();
			Display.sync(60);
		}
        Display.destroy();
        System.exit(1);
	}
	public static void GameOver()
	{
		Player.jumpPressed = true;
	}
	private static void setTitle()
	{
		int disX;
		int disXDec;
		int disY;
		disX = (int) (Player.x + Width) / Width;
		disXDec = (int) Math.ceil((Player.x / BlockSize) + 9 - disX * 10);
		disY = (int) Player.y / BlockSize;
//		Game Title
		Display.setTitle("Mikecraft " + ver + " | " + Gravity.score);
//		Dev Title
//		Display.setTitle("Mikecraft "+ver+" | " + level + " | " + (disX - 1) + "." + disXDec + " , "+ disY + " | " + Gravity.score);
	}
	private static void setCamera() 
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
	private static void checkInput() 
	{
		switch (state) 
		{
		case OPTIONS:
			if (Mouse.getX() >= 122 && Mouse.getX() <= 504 && Mouse.getY() >= 300 && Mouse.getY() <= 348 && Mouse.isButtonDown(0) && released[0])
			{
				released[0] = false;
				if (level <= 4)
				{
					level++;
				} else {
					level = 1;
				}
			} else if(!Mouse.isButtonDown(0)){
				released[0] = true;
			}
			if (Mouse.getY() >= 236 && Mouse.getY() <= 284 && Mouse.getX() >= 122 && Mouse.getX() <= 504 && Mouse.isButtonDown(0)) 
			{
				if (released[0])
				{
					released[0] = false;
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
					state = State.STAGE_SWAP;
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
					state = State.OPTIONS;
					GUI.drawBackground();
				} else if(!Mouse.isButtonDown(0)){
					released[0] = true;
				}
			}
			if ((Mouse.getY() >= 236 && Mouse.getY() <= 284 && Mouse.getX() >= 122 && Mouse.getX() <= 504 && Mouse.isButtonDown(0) && released[0]) || (Keyboard.isKeyDown(1) && released[1])) 
			{
				Display.destroy();
				System.exit(1);
			} else if (!Mouse.isButtonDown(0))
			{
				released[0] = true;
			}
			break;
		case STAGE_SWAP:
			if (Keyboard.isKeyDown(28) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)) 
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
				player.y = BlockSize * 2;
				state = State.MAIN_MENU;
				GUI.drawBackground();
			}
		default:
			break;
		}
	}
}
