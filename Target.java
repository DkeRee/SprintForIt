import java.awt.*;

class DeathParticle extends Particle {
	static final int side = 20;
	private int x;
	private int y;
	private Color color;
	
	private int xV = Math.random() > 0.5 ? (int)(Math.random() * 20) : -((int)(Math.random() * 20)) ;
	private int yV = -(int)(Math.random() * 30);
	
	public DeathParticle(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void update() {
		this.x += this.xV;
		this.y += this.yV;
		this.yV++;
		
		if (this.y > Model.HEIGHT) {
			super.remove = true;
		}
	}
	
	public void render(Graphics2D ctx) {
		ctx.setColor(this.color);
		Rectangle rectangle = new Rectangle(this.x, this.y, side, side);
		ctx.draw(rectangle);
		ctx.fill(rectangle);
	}
}

public class Target {
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	private int xV;
	private int vY;
	private boolean kill;
	
	public Target(int x, int y, int width, int height, int speed, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		
		int pivot = (int)(Math.random() * 4);
		
		switch (pivot) {
			case 0:
				this.xV = 0;
				this.vY = speed;
				break;
			case 1:
				this.xV = 0;
				this.vY = -speed;
				break;
			case 2:
				this.xV = speed;
				this.vY = 0;
				break;
			case 3:
				this.xV = -speed;
				this.vY = 0;
				break;
		}
	}
	
	public void update() {
		this.x += this.xV;
		this.y += this.vY;
		
		if (this.x < 0 || this.x > Model.WIDTH || this.y < 0 || this.y > Model.HEIGHT) {
			this.kill = true;
		}
	}
	
	public void render(Graphics2D ctx) {
		ctx.setColor(this.color);
		Rectangle rectangle = new Rectangle(this.x, this.y, this.width, this.height);
		ctx.draw(rectangle);
		ctx.fill(rectangle);
	}
	
	public void createDeathParticles() {
		for (int i = 0; i < 50; i++) {
			Model.particles.add(new DeathParticle(this.x, this.y, this.color));
		}
	}
	
	public boolean collide(int x, int y) {
		if (x > this.x
			&& x < this.x + this.width
			&& y > this.y
			&& y < this.y + this.height) {
			this.kill = true;
			this.createDeathParticles();
			return true;
		}
		
		return false;
	}
	
	public boolean getKill () {
		return this.kill;
	}
}