import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle implements KeyListener {
	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;
	private int direction;
	private boolean left;
	
	public Paddle(int x, int y, int w, int h, int s, boolean l) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		speed = s;
		direction = 0;
		left = l;
		
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
			direction = -1;
		} else if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
			direction = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		direction = 0;
	}
	
	public void move() {
		y += speed * direction;
		if(y < 0) {
			y = 0;
		} else if(y > Pong.GAME_HEIGHT) {
			y = Pong.GAME_HEIGHT;
		}
	}
	
	public boolean isLeft() {
		return left;
	}
}
