package mikecraft;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public final class Item {

    private final ItemType type;
    private Texture texture;
    private final float x;
    private final float y;

    public Item(ItemType type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
        try {
            this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(type.location)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bind() {
        texture.bind();
    }

    public void draw() {
    	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glPushMatrix();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(0, 0);
        glTexCoord2f(1, 0);
        glVertex2f(MainGame.BlockSize, 0);
        glTexCoord2f(1, 1);
        glVertex2f(MainGame.BlockSize, MainGame.BlockSize);
        glTexCoord2f(0, 1);
        glVertex2f(0, MainGame.BlockSize);
        glEnd();
        glPopMatrix();
    }

    public ItemType getType() {
        return type;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
