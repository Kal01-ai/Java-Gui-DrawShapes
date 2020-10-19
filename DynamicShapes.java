package gui.pbl1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DynamicShapes extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Object> shapes = new ArrayList<>();
	private Random random = new Random();
	
	private DynamicShapes(String starColor, String circleColor, int i, String shape) {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(400, 400));
		
		switch(starColor) {  // Color option for star
		case "Red":
			setStarColor("Red");
			break;
			
		case "Blue":
			setStarColor("Blue");
			break;
		}
		
		switch(circleColor) {   // Color option for circle
		case "Red":
			setCircleColor("Red");
			break;
			
		case "Blue":
			setCircleColor("Blue");
			break;
		}
		
		switch(shape) {
		case "Circles":
			for(int j = 0; j < i; j++) {
				addCircle(390, 390);
			}
			break;
			
		case "Stars":
			for(int j = 0; j < i; j++) {
				addStar(380, 380);
			}
			break;
			
		case "Both":
			int mid = i / 2;
			for (int j = 0; j < i; j++) {
				addCircle(390, 390);
			}
			for (int j = mid; j < i; j++) {
				addStar(380, 380);
			}
			break;
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Object s : shapes) {
			if (s instanceof Circle) {
				((Circle) s).draw(g);          // Draw circle and apply selected color
				g.setColor(circleChosenColor);
			} else if(s instanceof Star) {
				((Star) s).draw(g);            // Draw star and apply selected color
				g.setColor(starChosenColor);
			}
		}
	}
	
	Color circleChosenColor, starChosenColor;        // Color variables
	public Color setCircleColor(String circleColor) {
		
		if(circleColor == "Red") {
			return circleChosenColor = Color.RED;
		}
		else if(circleColor == "Blue") {
			return circleChosenColor = Color.BLUE;
		}
		return circleChosenColor;           // Get selected color for circle
	}
	public Color setStarColor(String starColor) {
		
		if(starColor == "Red") {
			return starChosenColor = Color.RED;
		}
		else if(starColor == "Blue") {
			return starChosenColor = Color.BLUE;
		}
		return starChosenColor;             // Get selected color for star
	}
	
	public void addCircle(int maxX, int maxY) {
		shapes.add(new Circle(random.nextInt(maxX), random.nextInt(maxY)));
		repaint();
	}
	public void addStar(int maxX, int maxY) {
		shapes.add(new Star(random.nextInt(maxX), random.nextInt(maxY)));
		repaint();
	}
	
	public static void main(String[] args) {
		String circleColors[] = {"Red", "Blue"};                    // Menu for circle
		String circleColor = (String) JOptionPane.showInputDialog(null,
				"Pick your color for circle", "Random Shapes...",
				JOptionPane.PLAIN_MESSAGE, null, circleColors, circleColors[0]);
		
		String starColors[] = {"Red", "Blue"};                       // Menu for star
		String starColor = (String) JOptionPane.showInputDialog(null,
				"Pick your color for star", "Random Shapes...",
				JOptionPane.PLAIN_MESSAGE, null, starColors, starColors[0]);
		
		String shapeAmount = JOptionPane.showInputDialog(null, 
				"How many shapes?", "Random Shapes...", JOptionPane.PLAIN_MESSAGE);
		int amount = Integer.parseInt(shapeAmount);
		
		String shapes[] = {"Stars", "Circles", "Both"};
        String shape = (String) JOptionPane.showInputDialog(null,
                "Pick the shape you want", "Random Shapes...",
                JOptionPane.PLAIN_MESSAGE, null, shapes, shapes[0]);

        JFrame frame = new JFrame();
        frame.add(new DynamicShapes(starColor, circleColor, amount, shape));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
}
