package queens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class QueensViewImpl extends JPanel {
	
	//Buttons
	private JButton startButton;
	private JButton stopButton;
	private JButton resetButton;
	
	private JComboBox numQueensBox;
	
	private JTextField solutionsTextArea;
	private JTextArea allSolutionsTextArea;
	
	private JScrollPane scroll;
	
	private String solutionsText;
	private String allSolutionsText;
	
	private JPanel buttonPanel;
	private JPanel allSolutionsPanel;
	
	private Thread queensThread;
	private ChangeListener changeListener;
	
	//private int numQueens;
	MyChessBoard chessBoard;
	QueensSolverEngine engine;
	
	private int currentSolutions;
	
	public QueensViewImpl(QueensSolverEngine queenEngine) {
		
		super();
		this.setPreferredSize(new Dimension(QueenFrame.PANEL_WIDTH-20, QueenFrame.PANEL_HEIGHT - 100));
		this.setLayout(new BorderLayout());
		this.engine = queenEngine;
		
		changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				update();
			}
		};
		engine.addChangeListener(changeListener);
		init();
	}
	
	public void init() {
		
		this.solutionsText = "Solutions: 0";
		this.allSolutionsText = "No Solutions Yet     ";
		
		this.startButton = new JButton("Start");
		this.stopButton = new JButton("Stop");
		this.resetButton = new JButton("Reset");
		
		setupButtonListeners();
		
        String []queens = {"4", "5", "6", "7", "8", "9", "10", "11", "12"};
        this.numQueensBox = new JComboBox(queens);
        this.numQueensBox.setSelectedIndex(0);
		
		this.solutionsTextArea = new JTextField(solutionsText);
		this.solutionsTextArea.setEditable(false);
		this.solutionsTextArea.setBackground(null);
		
		this.allSolutionsTextArea = new JTextArea(allSolutionsText);
		this.allSolutionsTextArea.setAlignmentX(LEFT_ALIGNMENT);
		this.allSolutionsTextArea.setEditable(false);
		this.allSolutionsTextArea.setBackground(null);
		
		scroll = new JScrollPane(allSolutionsTextArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		//solutionsTextArea.setFont(new Font("default", Font.BOLD, 16));
		
		this.buttonPanel = new JPanel();
		this.allSolutionsPanel = new JPanel();
		this.chessBoard = new MyChessBoard();
		
		
		this.buttonPanel.setLayout(new GridLayout(1,4));
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(numQueensBox);
		buttonPanel.add(solutionsTextArea);

		
		this.add(buttonPanel, BorderLayout.NORTH);
		this.add(chessBoard, BorderLayout.CENTER);
	
		
		this.allSolutionsPanel.setLayout(new GridLayout(1,1));
		allSolutionsPanel.add(scroll);
		
		
		
		this.add(scroll, BorderLayout.EAST);
		
	}
	
	private int getNumQueens() {
		int nQueens = Integer.parseInt((String)numQueensBox.getSelectedItem());
		return nQueens;

	}
	
	private void setupButtonListeners () {
		
		startButton.addActionListener(new ActionListener() {
			
			public void actionPerformed (ActionEvent e) {
				if (queensThread == null || !queensThread.isAlive()) {
					queensThread = new Thread(new Runnable()  {
						public void run () {
							int nq = getNumQueens();
							engine.resetSolver(nq);
							chessBoard.setPosition(engine.getQueenPositions(), nq);
							
							currentSolutions = 0;
							allSolutionsText = "";
							allSolutionsTextArea.setText(allSolutionsText);
							
							update();
							engine.solveAll();
						}
					});
					queensThread.start();
				}
			}
		});
		
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.setStopFlag(true);
			}
		});
		
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nq = getNumQueens();
				if (queensThread != null || queensThread.isAlive()) {
					engine.setStopFlag(true);
					chessBoard.setPosition(engine.getQueenPositions(), nq);
				    numQueensBox.setSelectedIndex(0);
					
					solutionsText = "Solutions: 0";
					solutionsTextArea.setText(solutionsText);
					
					currentSolutions = 0;
					allSolutionsText = "";
					allSolutionsTextArea.setText(allSolutionsText);
					update();
					
				}
			}
		});

	}
					
	
	private void update() {
		try {
			Thread.sleep(0);
		}
		catch (InterruptedException e) {
			
		}
		solutionsText = "Solutions: " + Integer.toString(engine.getNumSolutions());
		solutionsTextArea.setText(solutionsText);
		
		chessBoard.setPosition(engine.getQueenPositions(), getNumQueens());
		this.repaint();
		chessBoard.repaint();
		
		if (currentSolutions != engine.getNumSolutions()) {
			currentSolutions = engine.getNumSolutions();
			allSolutionsText += engine.toString();
			allSolutionsTextArea.setText(allSolutionsText);
		}
	}
	
	
	
	private class MyChessBoard extends JPanel {

		private int position[];  //array of ints containing column position of queens in each row (index)
		private int nq;
		
		private Image img;
		
		private static final int MY_WIDTH = QueenFrame.PANEL_WIDTH - 200;
		private static final int MY_HEIGHT = QueenFrame.PANEL_HEIGHT - 50;

		public MyChessBoard() {
			
			super();
			
			//this.img = new ImageIcon("./queens/queen.png").getImage();
			this.img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/queens/queen.png"));
			
			this.setPreferredSize(new Dimension(MY_WIDTH, MY_HEIGHT));
			this.nq = 4;
			this.position = new int[nq];
			
			for (int i = 0; i < nq; i++) {
				position[i] = -1;
			}
		
	 	}
		
		public void setPosition(int pos[], int numqueens) {
			this.position = pos;
			this.nq = numqueens;
		}
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			int xpos = 0;
			int ypos = 0;
			int width = MY_WIDTH/nq;
			
			for (int i = 0; i < nq; i++) {
				
				for (int k = 0; k < nq; k++) {
					
					if ((i+k) % 2 == 0) {  //even
						g.setColor(Color.BLACK);
					}
					else {
						g.setColor(Color.WHITE);
					}
					g.fillRect(xpos, ypos, width, width);
					
					if (this.position[i] == k) {
						//g.setColor(Color.cyan);
						//g.fillOval(xpos, ypos, (MY_WIDTH/nq), (MY_WIDTH/nq));
						g.drawImage(img, xpos, ypos, width, width, null);
					}
				
					xpos = xpos + width;
					
				}
				ypos = ypos + width;
				xpos = 0;
			}
		
		}
		
	}
}
