import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
	private Model model;
	
	static Color DARK = Color.decode("#A2D149");
	static Color LIGHT = Color.decode("#AAD751");
	
	static Color[] colors = {
		Color.decode("#966A4B"),
		Color.decode("#3AB02E"),
		Color.decode("#B82A55"),
		Color.decode("#154734"),
		Color.decode("#DEC951"),
		Color.decode("#224ACF"),
		Color.decode("#DBDBDB")
	};
	
	public View(Model model) {
		this.model = model;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, Model.WIDTH, Model.HEIGHT);
		Graphics2D ctx = (Graphics2D)g;
		
        GradientPaint gp = new GradientPaint(0, 0, View.DARK, Model.WIDTH / 2, Model.HEIGHT / 2, View.LIGHT);
		
		ctx.setPaint(gp);
		g.fillRect(0, 0, Model.WIDTH, Model.HEIGHT);
		
		for (int i = 0; i < Model.particles.size(); i++) {
			Model.particles.get(i).render(ctx);
		}
	
		ArrayList<Target> targets = this.model.getTargets();
		for (int i = 0; i < targets.size(); i++) {
			targets.get(i).render(ctx);
		}
	}
}