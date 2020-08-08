import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Pong {
	public static final int GAME_WIDTH = 1024;
	public static final int GAME_HEIGHT = 512;

	private Paddle p1;
	private Paddle p2;
	private Ball ball;

	private JFrame frame;
	private JPanel panel;
	private Timer timer;
	
	private int leftScore = 0;
	private int rightScore = 0;
	
	public Pong() {
		p1 = new Paddle(10, GAME_HEIGHT/2-28, 4, 56, 2, true);
		p2 = new Paddle(GAME_WIDTH-14, GAME_HEIGHT/2-28, 4, 56, 2, false);
		ball = new Ball(GAME_WIDTH/2, GAME_HEIGHT/2, 5, 5, 20);

		frame = new JFrame();

		frame.setBounds(100, 100, GAME_WIDTH, GAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Pong");
		
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				p1.paint(g);
				p2.paint(g);
				ball.paint(g);
				g.drawLine(512, 0, 512, 512);
			}
		};
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed W"), "W");
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"), "released1");
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed S"), "S");
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"), "released1");
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed UP"), "UP");
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "released2");
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed DOWN"), "DOWN");
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "released2");
		panel.getActionMap().put("W", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				p1.setDirection(-1);
			}
		});
		panel.getActionMap().put("S", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p1.setDirection(1);
			}
		});
		panel.getActionMap().put("released1", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p1.setDirection(0);
			}
		});
		panel.getActionMap().put("UP", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p2.setDirection(-1);
			}
		});
		panel.getActionMap().put("DOWN", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p2.setDirection(1);
			}
		});
		panel.getActionMap().put("released2", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p2.setDirection(0);
			}
		});
		panel.setBackground(Color.BLACK);
		
		frame.add(panel);
		frame.setVisible(true);
		
		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p1.move();
				p2.move();
				ball.move();
				frame.repaint();
			}
		});
		timer.start();
		
		
		
		panel.setLayout(null);
		
		

		if(ball.getX() == 0) {
			leftScore++;
		}
		else if(ball.getX() == GAME_WIDTH) {
			rightScore++;
		}

		JLabel labelL = new JLabel(leftScore + "");
		JLabel labelR = new JLabel(rightScore + "");
		labelL.setForeground(Color.WHITE);	
		labelR.setForeground(Color.WHITE);
		panel.add(labelL);
		panel.add(labelR);
		labelL.setLocation(412, 0);
		labelL.setSize(100, 100);
		labelL.setFont(new Font("Serif", Font.PLAIN, 100));
		labelR.setLocation(562, 0);
		labelR.setSize(100, 100);
		labelR.setFont(new Font("Serif", Font.PLAIN, 100));
	}
	
	public static void main(String[] args) {
		new Pong();
	}

}