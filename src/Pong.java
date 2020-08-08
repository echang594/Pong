import java.awt.*;
import javax.swing.*;

public class Pong {

	public static final int GAME_WIDTH = 1024;
	public static final int GAME_HEIGHT = 512;

	private JFrame frame;
	private JPanel panel;


	private Paddle p1;
	private Paddle p2;
	private Ball ball;

	private int leftScore;
	private int rightScore;


	public Pong() {
		p1 = new Paddle(40, 228, 30, 90, 2, true);
		p2 = new Paddle(954, 228, 30, 90, 2, false);
		ball = new Ball(512, 256, 30);

		frame = new JFrame();

		frame.setBounds(50, 50, GAME_WIDTH, GAME_HEIGHT);


		frame = new JFrame("Pong");

		frame.setBounds(100, 100, GAME_WIDTH, GAME_HEIGHT);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		frame.setVisible(true);



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
		
		panel.setBackground(Color.BLACK);
		frame.getContentPane().add(panel);
		frame.setVisible(true);


		
		panel.setLayout(null);
		
		leftScore = 0;

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
