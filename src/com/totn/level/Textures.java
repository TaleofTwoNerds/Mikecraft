package com.totn.level;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.totn.mikecraft.MainGame;

/*	Instead of loading every texture at launch, load them for each level
		Level designers can use any textures in the res folder and have 
		them created by the level constructor
	
	Textures that already exists won't have to be reloaded, but next ones
		can be pushed and old ones popped from the active textures array
		
	Create and active textures array to hold all currently active textures
		When the world is constructed it will generate from this array
		Then each cycle can run through this array running logic for each texture
*/

public class Textures extends MainGame
{	
	public static void t()
	{
		levelName[1]="World 1-1";
		levelName[2]="World 1-2";
		levelName[3]="World 1-3";
		
		//characters
		SteveChar = textureLoader("PNG","res/char/William1-1.png");
		MikeChar = textureLoader("PNG","res/char/Mike1-4.png");
		MineChar = textureLoader("PNG","res/char/SteveSideXPos.png");
		if(MainGame.Char == 2){PlayerSkin = MikeChar;;}
		else if(MainGame.Char == 3){PlayerSkin = MineChar;}
		else {PlayerSkin = SteveChar;}
		Steve2 = textureLoader("PNG","res/char/SteveNew1.png");
		
		//environment 
		Sky = textureLoader("PNG","res/blocks/mushroom_block_inside.png");
		Grass = textureLoader("PNG","res/blocks/grass_side.png");
		Planks_oak = textureLoader("PNG","res/blocks/planks_oak.png");
		Stone = textureLoader("PNG","res/blocks/stone.png");
		Dirt = textureLoader("PNG","res/blocks/dirt.png");
		Emerald = textureLoader("PNG","res/items/emerald.png");
		Gold = textureLoader("PNG","res/blocks/gold_block.png");
		Brick = textureLoader("PNG","res/blocks/brick.png");
		Tnt = textureLoader("PNG","res/blocks/tnt_side.png");
		Redstone = textureLoader("PNG","res/blocks/redstone_block.png");
		Flag = textureLoader("PNG","res/images/flag.png");
		Wheat7 = textureLoader("PNG","res/blocks/wheat_stage_7.png");

		//menus
		Title = textureLoader("PNG","res/images/Mikecraft.png");
		button = textureLoader("PNG","res/images/button.png");
		buttonHover = textureLoader("PNG","res/images/buttonHover.png");
		Button[1] = button;
		Button[2] = button;
		Button[3] = button;
		Button[4] = button;
		TitleBack = textureLoader("PNG","res/images/well.png");
		TitleBackSwap = textureLoader("PNG","res/images/estate3.png");
		TitleBackOption = textureLoader("PNG","res/images/danealue.png");
	}
	
	private static Texture textureLoader(String fileType, String location)
	{
		Texture value = null;
		try {
			value = TextureLoader.getTexture(fileType, new FileInputStream(new File(location)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;

	}
}
