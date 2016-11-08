package queue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;




public class QueueViewImpl extends JPanel {
	
	private ChangeListener changeListener;
	private QueueEngine engine;
	
	private JButton addBtn;
	private JButton removeBtn;
	private JButton clearBtn;

	
	private JTextField countField;
	private String currentCount;
	
	private JPanel buttonPanel;
	private QueuePanel qPanel;
	
	
	public QueueViewImpl(QueueEngine qEngine) {
		
		super();
		this.setPreferredSize(new Dimension(QueueFrame.PANEL_WIDTH-20, QueueFrame.PANEL_HEIGHT - 100));
		this.setLayout(new BorderLayout());
		this.engine = qEngine;
		
		changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				update();
			}
		};
		engine.addChangeListener(changeListener);
		init();
	}
	
	public void init() {
		
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(2,2));
		
		this.currentCount = "Items in queue: 0";
		
		this.addBtn = new JButton("Add");
		this.removeBtn = new JButton("Remove");
		this.clearBtn = new JButton("Clear");

		this.countField = new JTextField(currentCount);
		
		this.qPanel = new QueuePanel();
		
		buttonPanel.add(addBtn);
		buttonPanel.add(clearBtn);
		buttonPanel.add(removeBtn);
		buttonPanel.add(countField);
		
		initButtons();
		
		countField.setBackground(null);
		countField.setEditable(false);
		
		this.add(buttonPanel, BorderLayout.NORTH);
		this.add(qPanel, BorderLayout.CENTER);
		
	}
	
	private void initButtons() {
		
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String item = JOptionPane.showInputDialog(null, "Enter value to add to the queue", "");
				if(item==null) {}
					//actionText="Push cancelled...no push";
				else
				{
					try {
						engine.add(item);
						String status = "Just pushed " + item + " into the queue.";
						JOptionPane.showMessageDialog(null, status, "InfoBox: " + "Queue", JOptionPane.INFORMATION_MESSAGE);
					}
					catch (RuntimeException error) {
						JOptionPane.showMessageDialog(null, "Queue is full.", "InfoBox: " + "Queue", JOptionPane.INFORMATION_MESSAGE);
					}
				}				
				
				update();
			}
		});
		
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int count = engine.size();
				
				if (count == 0) {
					JOptionPane.showMessageDialog(null, "The queue is empty.", "InfoBox: " + "Queue", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String ret = engine.remove();
					JOptionPane.showMessageDialog(null, "Item Removed: " + ret, "InfoBox: " + "Queue", JOptionPane.INFORMATION_MESSAGE);
				}
				
				update();
	
			}
		});
		
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int count = engine.size();
				
				if (count == 0) {
					JOptionPane.showMessageDialog(null, "The queue is already empty.", "InfoBox: " + "Queue", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					engine.clear();
					JOptionPane.showMessageDialog(null, "Queue Cleared. ", "InfoBox: " + "Queue", JOptionPane.INFORMATION_MESSAGE);
				}
				
				update();
	
			}
		});
		
	}
	
	private void update() {
		
		int count = engine.size();
		String countStr = Integer.toString(count);
		currentCount = "Items in queue: " + countStr;
		countField.setText(currentCount);
		
		repaint();
	}
	
	private class QueuePanel extends JPanel
	{
		final int MYWIDTH=QueueFrame.PANEL_WIDTH / QueueEngine.MAX;
		final int MYHEIGHT=QueueFrame.PANEL_HEIGHT-150;
		public QueuePanel()
		{ 
			super();
	
			this.setPreferredSize(new Dimension(MYWIDTH, MYHEIGHT));
			this.setBackground(Color.RED);
			this.repaint();
		}
		public void paintComponent(Graphics g)
		{
			String theStack = engine.toString();
			Scanner input = new Scanner(theStack);
			g.setFont(new Font("default", Font.BOLD, 16));
			int size = engine.size();
			
			if(size == 0)
			{
				g.drawString("Queue is EMPTY", 20,  20);
				return;
			}
			
			int xpos = (QueueFrame.PANEL_WIDTH / QueueEngine.MAX) / QueueEngine.MAX;
			int ypos = MYHEIGHT/3;
			int width = (QueueFrame.PANEL_WIDTH / QueueEngine.MAX) - 30;
			int height = width;
			
			
			for(int i=0; i<QueueEngine.MAX; i++)
			{   
				g.drawRect(xpos, ypos, width, height);
				xpos = xpos + (width);
			}
			
			xpos = ((QueueFrame.PANEL_WIDTH / QueueEngine.MAX) / QueueEngine.MAX) * 2;
			width = (QueueFrame.PANEL_WIDTH / QueueEngine.MAX) - 30;
			for(int i=0; i<size; i++)
			{  
			
				String out = input.nextLine();
				g.drawString(out, xpos+20, ypos*2);
				
				
				if (engine.size() >= 1 && i == 0) {
					String first = "First";
					g.drawString(first, xpos+20, ypos - 10);
				}
				
				if (engine.size() >= 2  && i == (engine.size() - 1)) {
					String last = "Last";
					g.drawString(last, xpos+20, ypos - 10);
				}
				
				xpos = xpos + width;
			}
			
		}

	}

}


