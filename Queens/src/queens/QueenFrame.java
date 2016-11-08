package queens;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class QueenFrame {
	
	public static final int PANEL_WIDTH = 750;
	public static final int PANEL_HEIGHT = 750;
	
	public static void main(String[] args) {
		QueensSolverEngine engine = new QueensSolverEngineImpl();
		QueensViewImpl controller = new QueensViewImpl(engine);
		JFrame frame = new JFrame("Queens Solver");
		
		frame.setLayout(new FlowLayout());
		
		frame.getContentPane().add(controller);
		frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
	}

}
