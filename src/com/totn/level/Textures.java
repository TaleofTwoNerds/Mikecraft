package com.totn.level;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;

import com.totn.mikecraft.MainGame;

public class Textures extends MainGame{
	
	public static void t()
	{
		levelName[1]="World 1-1";
		levelName[2]="World 1-2";
		levelName[3]="World 1-3";
		
		try 
		{
			//characters
            SteveChar = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/William1-1.png")));
            MikeChar = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/Mike1-4.png")));
            MineChar = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/SteveSideXPos.png")));
            if(MainGame.Char == 2){PlayerSkin = MikeChar;;}
            else if(MainGame.Char == 3){PlayerSkin = MineChar;}
            else {PlayerSkin = SteveChar;}
	        Steve2 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/SteveNew1.png")));
            
	        //environment
	        Sky = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/air.png")));
            Grass = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/grass_side.png")));
            Planks_oak = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/planks_oak.png")));
            Stone = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/stone.png")));
            Dirt = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/dirt.png")));
            Emerald = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/items/emerald.png")));
            Gold = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/gold_block.png")));
            Brick = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/brick.png")));
            Tnt = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/tnt_side.png")));
            Redstone = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/redstone_block.png")));
            Flag = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/flag.png")));
            Wheat7 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/blocks/wheat_stage_7.png")));

            //menus
            Title = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/Mikecraft.png")));
            Button[1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/button.png")));
            Button[2] = Button[1];
            Button[3] = Button[2];
            Button[4] = Button[3];
            TitleBack = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/panorama_1.png")));
		} catch (IOException e) 
		{
            e.printStackTrace();
            System.out.println("MISSING TEXTURE!");
            Display.destroy();
            System.exit(1);
        }
	}
}
