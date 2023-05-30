import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame("Sprint For It");
		Runner runner = new Runner();
		
        window.setContentPane(runner.getView());
        window.pack();  
        //****this centers the window on the users screen
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation( (screensize.width - Model.WIDTH)/2,
                (screensize.height - Model.HEIGHT)/80 );
		window.setSize(Model.WIDTH + 15, Model.HEIGHT + 35);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setResizable(false);  
        window.setVisible(true);
                
		runner.start();
	}
}