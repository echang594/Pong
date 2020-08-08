import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Start implements ActionListener {

	private JFrame frame;
	private JButton button;
	private JPanel panel;
	
	public Start() {
		frame = new JFrame();
		frame.setBounds(50, 50, 1000, 600);
		
		button = new JButton("Start Game");
		button.addActionListener(this);
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 40, 50));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(button);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Pong");
		frame.add(button, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		new Pong();
		
	}
	
	

}
