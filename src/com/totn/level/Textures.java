package com.totn.level;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.totn.mikecraft.MainGame;

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
		Button[1] = textureLoader("PNG","res/images/button.png");
		button = textureLoader("PNG","res/images/button.png");
		buttonHover = textureLoader("PNG","res/images/buttonHover.png");
		Button[2] = Button[1];
		Button[3] = Button[2];
		Button[4] = Button[3];
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
