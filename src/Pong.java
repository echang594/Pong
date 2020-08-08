import java.awt.*;
import javax.swing.*;

public class Pong {

	public static final int gameWidth = 1000;
	public static final int gameHeight = 600;
	
	private JFrame frame;
	private JPanel panel; 
	
	public Pong() {
		
		frame = new JFrame();
		frame.setBounds(50, 50, gameWidth, gameHeight);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Pong");
	
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Pong();
	}

}