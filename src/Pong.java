import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class Pong {
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 450;
	private static final int POINTS_TO_WIN = 5;

	private Paddle p1;
	private Paddle p2;
	private Ball ball;

	private JFrame frame;
	private JPanel panel;
	private Timer timer;

	private JLabel labelL;
	private JLabel labelR;

	private int leftScore;
	private int rightScore;
	private boolean started;
	private boolean ended;
	private String winner;

	public Pong() {
		leftScore = 0;
		rightScore = 0;
		started = false;
		ended = false;

		p1 = new Paddle(20, GAME_HEIGHT / 2 - 40, 8, 80, 4);
		p2 = new Paddle(GAME_WIDTH - 28, GAME_HEIGHT / 2 - 40, 8, 80, 4);
		int vx = (ThreadLocalRandom.current().nextBoolean() ? 1 : -1) * ThreadLocalRandom.current().nextInt(4, 6 + 1);
		int vy = (ThreadLocalRandom.current().nextBoolean() ? 1 : -1)
				* ThreadLocalRandom.current().nextInt(4, Math.abs(vx) + 1);
		ball = new Ball(GAME_WIDTH / 2 - 10, GAME_HEIGHT / 2 - 10, vx, vy, 20, "coronavirus.png");

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
				if (!started && !ended) {
					g.setColor(Color.WHITE);
					g.setFont(new Font("Comic Sans MS", Font.PLAIN, 150));
					g.drawString("Pong", GAME_WIDTH / 3, GAME_HEIGHT / 2 - 40);

					g.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
					g.drawString("Deflect the virus and don't get infected", GAME_WIDTH / 4 - 60, GAME_HEIGHT / 2 + 45);

					g.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
					g.drawString("Press SPACE to start", GAME_WIDTH / 5, GAME_HEIGHT * 2 / 3 + 70);
				} else {
					p1.paint(g);
					p2.paint(g);
					ball.paint(g, panel);
					g.setColor(Color.WHITE);
					g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
				}
				if (started && ended) {
					g.setColor(Color.WHITE);
					g.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
					g.drawString(winner + " wins!", GAME_WIDTH / 4, GAME_HEIGHT / 4 + 20);
					g.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
					g.drawString("Press SPACE to restart", GAME_WIDTH / 5, GAME_HEIGHT * 2 / 3 + 20);
				}
			}
		};
		panel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "START");
		panel.getInputMap().put(KeyStroke.getKeyStroke("pressed W"), "W");
		panel.getInputMap().put(KeyStroke.getKeyStroke("released W"), "released1");
		panel.getInputMap().put(KeyStroke.getKeyStroke("pressed S"), "S");
		panel.getInputMap().put(KeyStroke.getKeyStroke("released S"), "released1");
		panel.getInputMap().put(KeyStroke.getKeyStroke("pressed UP"), "UP");
		panel.getInputMap().put(KeyStroke.getKeyStroke("released UP"), "released2");
		panel.getInputMap().put(KeyStroke.getKeyStroke("pressed DOWN"), "DOWN");
		panel.getInputMap().put(KeyStroke.getKeyStroke("released DOWN"), "released2");
		panel.getActionMap().put("START", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				started = true;
				ended = false;

				p1.reset(20, GAME_HEIGHT / 2 - 40);
				p2.reset(GAME_WIDTH - 28, GAME_HEIGHT / 2 - 40);

				int nvx = ThreadLocalRandom.current().nextInt(4, 6 + 1);
				int nvy = (ThreadLocalRandom.current().nextBoolean() ? 1 : -1)
						* ThreadLocalRandom.current().nextInt(4, nvx + 1);

				if (leftScore == POINTS_TO_WIN) {
					ball.reset(GAME_WIDTH / 2 - 10, GAME_HEIGHT / 2 - 10, nvx, nvy);
				} else if (rightScore == POINTS_TO_WIN) {
					ball.reset(GAME_WIDTH / 2 - 10, GAME_HEIGHT / 2 - 10, -nvx, nvy);
				}

				leftScore = 0;
				rightScore = 0;

				labelL.setText(leftScore + "");
				labelR.setText(rightScore + "");

				labelL.setVisible(true);
				labelR.setVisible(true);
			}
		});
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
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		labelL = new JLabel(leftScore + "");
		labelR = new JLabel(rightScore + "");

		labelL.setForeground(Color.WHITE);
		labelL.setPreferredSize(new Dimension(100, 100));
		labelL.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
		labelL.setVisible(false);
		labelR.setForeground(Color.WHITE);
		labelR.setPreferredSize(new Dimension(100, 100));
		labelR.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
		labelR.setVisible(false);

		Box b = Box.createHorizontalBox();
		b.add(labelL);
		b.add(Box.createRigidArea(new Dimension(100, 0)));
		b.add(labelR);

		panel.add(b);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (started && !ended) {
					ball.move();
					p1.move();
					p2.move();
					ball.checkCollision(p1);
					ball.checkCollision(p2);

					if (ball.getX() + ball.getDiameter() <= 0) {
						rightScore++;
						labelR.setText(rightScore + "");

						if (rightScore == POINTS_TO_WIN) {
							ended = true;
							winner = "Right side";
						} else {
							int nvx = ThreadLocalRandom.current().nextInt(4, 6 + 1);
							int nvy = (ThreadLocalRandom.current().nextBoolean() ? 1 : -1)
									* ThreadLocalRandom.current().nextInt(4, nvx + 1);
							ball.reset(GAME_WIDTH / 2 - 10, GAME_HEIGHT / 2 - 10, -nvx, nvy);
						}
					} else if (ball.getX() >= GAME_WIDTH) {
						leftScore++;
						labelL.setText(leftScore + "");

						if (leftScore == POINTS_TO_WIN) {
							ended = true;
							winner = "Left side";
						} else {
							int nvx = ThreadLocalRandom.current().nextInt(4, 6 + 1);
							int nvy = (ThreadLocalRandom.current().nextBoolean() ? 1 : -1)
									* ThreadLocalRandom.current().nextInt(4, nvx + 1);
							ball.reset(GAME_WIDTH / 2 - 10, GAME_HEIGHT / 2 - 10, nvx, nvy);
						}
					}
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