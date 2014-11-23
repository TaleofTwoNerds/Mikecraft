package entity;

import org.newdawn.slick.opengl.Texture;

public abstract class AbstractMoveableEntity extends AbstractEntity implements MoveableEntity {

	protected double dx ,dy;
	
	public AbstractMoveableEntity(Texture t, double x, double y, double height,
			double width) {
		super(t, x, y, height, width);
		this.dx = 0;
		this.dy = 0;
		
	}
	
	@Override
	public void update(int delta) {
		this.x += delta * dx;
		this.y += delta * dy;
	}
	
	public double getDX() {
		return dx;
	}
	public double getDY(){
		return dy;
	}
	public void setDX(double dx) {
		this.dx = dx;
	}
	public void setDY(double dy) {
		this.dy = dy;
	}

}
