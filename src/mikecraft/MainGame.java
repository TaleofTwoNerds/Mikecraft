package mikecraft;

import static mikecraft.MainGame.blockSize;
import static mikecraft.MainGame.Height;
import static mikecraft.MainGame.Width;
import static mikecraft.MainGame.difficultyi;
import static mikecraft.MainGame.font3;
import static mikecraft.MainGame.lives;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import level.Textures;
import level.World;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

@SuppressWarnings("deprecation")
public class MainGame 
{	
	public static final String ver = "PRE 1.3";
	public static int Height = 480,Width = 640,Char = 1,levelTwo,lives = 3,difficultyi = 3;
	public static int nHeight = -Height;
	public static String levelName[] = new String[12],charName[] = new String[4],
			difficulty[] = new String[4];
	public static final int blockSize = Width / 10;
	public static Player player;
	public static Enemy enemy;
	public static boolean display = false,gameOver=false,released[] = new boolean[10];
	public static double level = 1;
	public static TrueTypeFont font,font2,font3;

	public static Texture t[] = new Texture[30];
	public static Texture Sky,Dirt,Brick,Planks_oak,Tnt,
		Gold,Redstone,Wheat7,Flag,Emerald,Grass,Stone;
	public static Texture SteveChar,MikeChar,MineChar,PlayerSkin,Steve2;
	public static Texture Title,TitleBack,Button[] = new Texture[5];

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
            Display.setFullscreen(true);
    		Display.setTitle("Mikecraft "+ ver +" | Loading...");
    		Display.setResizable(true);
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
			if(Mouse.getX() >= Width / 2 - 200 && Mouse.getX() <= Width / 2 + 183 && Mouse.getY() >= Height - 116 && Mouse.getY() <= Height - 68)
			{			
				Button[1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/buttonHover.png")));
			} else {
				Button[1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/button.png")));
			}
			if(Mouse.getX() >= Width / 2 - 200 && Mouse.getX() <= Width / 2 + 183 && Mouse.getY() >= Height / 2 && Mouse.getY() <= Height / 2 + 44)
			{			
				Button[2] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/buttonHover.png")));
			} else {
				Button[2] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/button.png")));
			}
			if(Mouse.getX() >= Width / 2 - 200 && Mouse.getX() <= Width / 2 + 183 && Mouse.getY() >= Height / 2 + 60 && Mouse.getY() <= Height / 2 + 108)
			{			
				Button[3] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/buttonHover.png")));
			} else {
				Button[3] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/button.png")));
			}
			if(Mouse.getX() >= Width / 2 - 200 && Mouse.getX() <= Width / 2 + 183 && Mouse.getY() >= Height / 2 - 68 && Mouse.getY() <= Height / 2 - 20)
			{			
				Button[4] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/buttonHover.png")));
			} else {
				Button[4] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/button.png")));
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
		font = new TrueTypeFont(awtFont, false);
		
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("res/font/Minecraftia.ttf");
			InputStream inputStream2	= ResourceLoader.getResourceAsStream("res/font/Minecraftia.ttf");

			Font awtFont3 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont3 = awtFont3.deriveFont(24f); // set font size
			font3 = new TrueTypeFont(awtFont3, false);
	 
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream2);
			awtFont2 = awtFont2.deriveFont(48f); // set font size
			font2 = new TrueTypeFont(awtFont2, false);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
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
		if(!display){setUpDisplay(); display = true;}
		player = new Player();
		enemy = new Enemy();
		Textures.t();
		fontInit(24);
		while(!Display.isCloseRequested())
		{
			checkInput();	
			setTitle();
			setCamera();
			if(state == State.GAME)
			{
				World.chooseLevel();
				if(enemy.toDraw){enemy.draw();}
				player.draw();
			}
			else
			{
				GUI.drawBackground();	
				dT();
			}
//			System.out.println(player.x + " | " + enemy.x);
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
		int disX = (int) (player.x + Width) / Width;
		int disXDec = (int) Math.ceil((player.x / blockSize) + 9 - disX * 10);
		int disY = (int) player.y / blockSize;
//		Game Title
//		Display.setTitle("Mikecraft " + ver + " | " + Gravity.score + " | " + lives);
//		Dev Title
		Display.setTitle("Mikecraft DEV "+ver+" | " + level + " | " + (disX - 1) + "." + disXDec + " , "+ disY + " | " + Gravity.score + " | " + lives);
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
			if (Mouse.getX() >= 122 && Mouse.getX() <= 504 && Mouse.getY() >= 300 && Mouse.getY() <= 348)
			{
				if ( Mouse.isButtonDown(0)  && released[0]){
				released[0] = false;
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
		default:
			break;
		}
	}
}