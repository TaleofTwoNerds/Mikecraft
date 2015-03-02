package levels;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import mikecraft.MainGame;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;

public class Textures extends MainGame{
	
	public static void t()
	{
		levelName[1]="World 1-1";
		levelName[2]="World 1-2";
		levelName[3]="World 1-3";
		
		try 
		{
			//characters
            if(MainGame.Char == 1){PlayerSkin = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/Steve1-3.png")));}
            SteveChar = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/Steve1-3.png")));
            if(MainGame.Char == 2){PlayerSkin = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/Mike1-2.png")));}
            MikeChar = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/Mike1-2.png")));
	        if(MainGame.Char == 3){PlayerSkin = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/SteveSideXPos.png")));}
	        MineChar = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/char/SteveSideXPos.png")));
	        
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
            QuitButton = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/quit.png")));
            StartButton = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/start.png")));
            Button = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/button.png")));
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
