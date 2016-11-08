package queue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class QueueFrame {
	
	public static final int PANEL_WIDTH = 750;
	public static final int PANEL_HEIGHT = 400;
	
	public static void main(String[] args) {
		QueueEngine engine = new QueueEngineImpl();
		QueueViewImpl controller = new QueueViewImpl(engine);
		JFrame frame = new JFrame("Queue");
		
		frame.setLayout(new FlowLayout());
		
		frame.getContentPane().add(controller);
		frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
	}

}

