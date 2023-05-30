public class Runner {
	//Updater and Renderer
	private Model model = new Model();
	private Controller controller;
	private View view = new View(this.model);
		
	public Runner() {
		this.controller = new Controller(this.model);
		this.view.setFocusable(true);
		this.view.requestFocus();
		this.view.addKeyListener(controller);
		this.view.addMouseListener(controller);
		this.view.addMouseMotionListener(controller);
	}
	
	//Deltatime
	private double accTime = 0.0;
	private double lastTime = 0.0;
	
	public void start() {
		while (true) {
			double time = (double)System.currentTimeMillis();
			accTime += (time - lastTime) / 1000.0;
			
			while (accTime > Model.deltaTime) {
				if (accTime > 1) {
					accTime = Model.deltaTime;
				}
				
				model.update();
				accTime -= Model.deltaTime;
			}
			lastTime = time;
			view.repaint();
		}
	}
	
	public View getView() {
		return view;
	}
}