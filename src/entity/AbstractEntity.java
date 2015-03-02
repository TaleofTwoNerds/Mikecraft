package entity;

import java.awt.Rectangle;

import org.newdawn.slick.opengl.Texture;

import entity.Entity;

public abstract class AbstractEntity implements Entity {

	protected double x, y, height, width;
	protected Texture t;
	protected Rectangle hitbox = new Rectangle();
	
	
	public AbstractEntity(Texture t, double x, double y, double height, double width) {
		this.x = x;
		this.y = y;
		this.t = t;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void setX(double x) {
		this.x = x;

	}

	@Override
	public void setY(double y) {
		this.y = y;

	}
	
	public void setT(Texture t) {
		this.t = t;

	}

	@Override
	public void setWidth(double width) {
		this.width = width;

	}

	@Override
	public void setHeight(double height) {
		this.height = height;

	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public boolean intersects(Entity other) {
		hitbox.setBounds((int) x, (int) y, (int) width, (int) height);
		return hitbox.intersects(other.getX(), other.getY(), other.getWidth(), other.getHeight());
	}
}
