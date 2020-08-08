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


	public Pong() {
		p1 = new Paddle(10, GAME_HEIGHT/2-28, 4, 56, 2, true);
		p2 = new Paddle(GAME_WIDTH-14, GAME_HEIGHT/2-28, 4, 56, 2, false);
		ball = new Ball(GAME_WIDTH/2, GAME_HEIGHT/2, 10, 10, 40);
		
		Paddle paddle = new Paddle(10, 10, 10, 10, 10, true);

		
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
				ball.draw(g);
			}
		};
		panel.setBackground(Color.BLACK);
		frame.getContentPane().add(panel);

		frame.setVisible(true);
		
		while (true) {
			
			ball.move(1024, 512);
			
			frame.repaint();
			try {
				Thread.currentThread().sleep(20);
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Pong();
		
	}

}