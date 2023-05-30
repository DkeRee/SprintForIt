import java.util.ArrayList;
import java.awt.*;

public class Model {
	//Screen Stats
	static final int WIDTH = 910;
	static final int HEIGHT = 700;
	
	//Deltatime
	static final double deltaTime = 1.0 / 60.0;
	
	private int spawn = -50;
	private int spawnCounter = 0;
		
	static ArrayList<Particle> particles = new ArrayList<Particle>();
	private ArrayList<Target> targets = new ArrayList<Target>();
	
	public void update() {
		//spawn
		if (this.spawnCounter < 0) {
			this.spawnCounter++;
		} else {
			this.spawnCounter = this.spawn;
			
			int randX = ((int)(Math.random() * Model.WIDTH) - 100) + 100;
			int randY = ((int)(Math.random() * Model.HEIGHT) - 100) + 100;
			int randSide = (int)((Math.random() * 40) + 30);
			int randSpeed = (int)((Math.random() * 4) + 1);
			Color randColor = View.colors[(int)(Math.random() * View.colors.length)];
			Target target = new Target(randX, randY, randSide, randSide, randSpeed, randColor);
		
			targets.add(target);
		}
		
		for (int i = 0; i < particles.size(); i++) {
			Particle p = particles.get(i);
			
			if (p.remove) {
				particles.remove(i);
				i--;
				continue;
			}
			
			p.update();
		}
		
		for (int i = 0; i < targets.size(); i++) {
			Target target = targets.get(i);
			
			if (target.getKill()) {
				targets.remove(i);
				i--;
				continue;
			}
			
			target.update();
		}
	}
	
	public void checkKill(int x, int y) {
		for (int i = 0; i < targets.size(); i++) {
			Target target = targets.get(i);
			
			if (target.collide(x, y)) {
				break;
			}
		}
	}
	
	public ArrayList<Target> getTargets() {
		return this.targets;
	}
}