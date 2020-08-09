import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle {
	public int x;
	public int y;
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
	
	public void move() {
		y += speed * direction;
		if(y < 0) {
			y = 0;
		} else if(y > Pong.GAME_HEIGHT) {
			y = Pong.GAME_HEIGHT;
		}
	}
	
	public void setDirection(int d) {
		direction = d;
	}
	
	public boolean isLeft() {
		return left;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
