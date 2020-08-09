import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	private int x;
	private int y;
	private int vx;
	private int vy;
	private int radius;

	public Ball(int x, int y, int vx, int vy, int radius) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.radius = radius;
	}

	public void move() {
		x += vx;
		y += vy;

		// make it so when ball hits paddel ball bounces off of paddel 
		// also ren is straight 

		if (y <= 0) {
			y = radius;
			vy *= -1;
		} else if (y >= Pong.GAME_HEIGHT - radius - 25) {
			y = Pong.GAME_HEIGHT - radius - 25;
			vy *= -1;
		}
		
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, radius, radius);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadius() {
		return x;
	}

	public boolean isInContact() {
		return false;
	}
	
}
