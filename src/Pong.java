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

	private JLabel labelL;
	private JLabel labelR;

	private int leftScore = 0;
	private int rightScore = 0;

	public Pong() {
		p1 = new Paddle(10, GAME_HEIGHT/2-28, 4, 56, 4, true);
		p2 = new Paddle(GAME_WIDTH-14, GAME_HEIGHT/2-28, 4, 56, 4, false);
		ball = new Ball(GAME_WIDTH/2, GAME_HEIGHT/2, 5, 5, 20, 20);

		frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(100, 100);
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
				g.setColor(Color.WHITE);
				g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
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
		panel.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		panel.setLayout(null);

		labelL = new JLabel(leftScore + "");
		labelR = new JLabel(rightScore + "");

		labelL.setForeground(Color.WHITE);
		labelL.setLocation(412, 25);
		labelL.setSize(100, 100);
		labelL.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
		labelR.setForeground(Color.WHITE);
		labelR.setLocation(562, 25);
		labelR.setSize(100, 100);
		labelR.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));

		panel.add(labelL);
		panel.add(labelR);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ball.move();
				p1.move();
				p2.move();
				ball.checkCollision(p1);
				ball.checkCollision(p2);

				if(ball.getX() <= 0) {
					rightScore++;
					labelR.setText(rightScore + "");
					ball.setX(GAME_WIDTH/2);
					ball.setY(GAME_HEIGHT/2);
					ball = new Ball(GAME_WIDTH/2, GAME_HEIGHT/2, -(int)(Math.random()*3) - 5, (int)(Math.random()*3) + 5, 20, 20);
				}
				else if(ball.getX() >= GAME_WIDTH) {
					leftScore++;
					labelL.setText(leftScore + "");
					ball.setX(GAME_WIDTH/2);
					ball.setY(GAME_HEIGHT/2);
					ball = new Ball(GAME_WIDTH/2, GAME_HEIGHT/2, (int)(Math.random()*3) + 5, (int)(Math.random()*3) + 5, 20, 20);
				}

				frame.repaint();
			}
		});
		timer.start();
	}

	public static void main(String[] args) {
		new Pong();
	}

}