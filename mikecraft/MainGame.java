package mikecraft;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import worlds.World;

public class MainGame {
	
	/*
	Possible plot twist. Steve is actually a robot.
	*/
	
	public static int Height = 480,Width = 640,Char = 2;
	public static final int BlockSize = Width / 10;
	public static Player player;
	public static Enemy enemy;
	public static boolean display = false;
	public static double level = 1.1;
	
    public static Texture sky,dirt,brick,planks_oak,tnt
    		,gold,redstone,wheat7,flag,emerald,grass;
    public static Texture steveChar,mikeChar,mineChar,playerSkin;
    public static Texture title,titleBack,startButton,quitButton,worldStage;
    
    public static enum State {
        MAIN_MENU, GAME, STAGE_SWAP
    }
    public static State state = State.MAIN_MENU;
    
	@SuppressWarnings("static-access")
	public static void setUpDisplay(){
    	try {
            Display.setDisplayMode(new DisplayMode(Width, Height));
//            Display.setFullscreen(true);
    		int disX;
    		int disXDec;
    		int disY;
    		disX = (int) (player.x + Width) / Width;
    		disXDec = (int) Math.ceil((player.x / BlockSize) + 9 - disX * 10);
    		disY = (int) player.y / BlockSize;
    		Display.setTitle("Mikecraft a1.0 | " + level + " | " + disX + "." + disXDec + " ,"+ disY);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
    }
	public static void main(String args[]) throws Exception {
		if(!display){setUpDisplay(); display = true;}
		player = new Player();
		try {
			//characters
            if(Char == 1){playerSkin = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/Steve1-3.png")));}
            steveChar = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/Steve1-3.png")));
            if(Char == 2){playerSkin = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/Mike1-2.png")));}
            mikeChar = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/Mike1-2.png")));
	        if(Char == 3){playerSkin = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/SteveSideXPos.png")));}
	        mineChar = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/SteveSideXPos.png")));
	        
	        //environment
	        sky = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/sky.png")));
            grass = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/grass_side.png")));
            planks_oak = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/planks_oak.png")));
            dirt = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/dirt.png")));
            emerald = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/items/emerald.png")));
            gold = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/gold_block.png")));
            brick = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/brick.png")));
            tnt = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/tnt_side.png")));
            redstone = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/redstone_block.png")));
            flag = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/flag.png")));
            wheat7 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/wheat_stage_7.png")));
            
            //menus
            if (level == 1.1){worldStage = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/worldOneOne.png")));}
            if (level == 1.2){worldStage = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/worldOneTwo.png")));}
            if (level == 10){worldStage = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/gameOver.png")));}
            title = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/Mikecraft.png")));
            quitButton = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/quit.png")));
            startButton = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/start.png")));
            titleBack = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/panorama_1.png")));
		} catch (IOException e) {
            e.printStackTrace();
            System.out.println("MISSING TEXTURE!");
            Display.destroy();
            System.exit(1);
        }
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(1)){
			setCamera();
			if(state == State.GAME){World.chooseLevel();
			player.draw();
			Enemy.draw();}
			else{GUI.drawBackground();}
			checkInput();
			Display.update();
			Display.sync(60);
		}
        Display.destroy();
        System.exit(1);
	}
	private static void setCamera() {
		//clear screen
		glClear(GL_COLOR_BUFFER_BIT);
		//modify projection matrix
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Width, 0, Height, 1, -1);
        
        //modify model view matrix
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        if (Player.x >= Width / 2){
        	if (Player.y >= Height / 2) {
        		glTranslated(-Player.x + Width / 2, -Player.y + Height / 2, 0);
        	} else {
        		glTranslated(-Player.x + Width / 2, 0, 0);
        	}
        }
        glEnable(GL_TEXTURE_2D);
	}
	private static void checkInput() {
        switch (state) {
            case MAIN_MENU:
                if (Mouse.getY() >= Height / 2 - 100 && Mouse.getY() <= Height / 2 && Mouse.getX() >= Width / 2 - 128 && Mouse.getX() <= Width / 2 + 128 && Mouse.isButtonDown(0)) {
                	state = State.STAGE_SWAP;
                	GUI.drawBackground();
                }
                if (Mouse.getY() >= Height / 2 - 200 && Mouse.getY() <= Height / 2 - 100&& Mouse.getX() >= Width / 2 - 128 && Mouse.getX() <= Width / 2 + 128 && Mouse.isButtonDown(0)) {
                	Display.destroy();
                    System.exit(1);
                }
                break;
            case STAGE_SWAP:
                if (Keyboard.isKeyDown(28) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                    if (level == 1.2){
                    	state = State.GAME;
                    	try {
							MainGame.main(null);
						} catch (Exception e) {
							e.printStackTrace();
						}
                    }if (level == 10){
                    	level = 1.1;
                    	state = State.MAIN_MENU;
                    } else {
                    	state = State.GAME;
                        break; 
                    }
                }
        	}
        }
    }
