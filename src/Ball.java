import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	private int x;
	private int y;
	private int vx;
	private int vy;
	private int radius;
	private int diameter;

	public Ball(int x, int y, int vx, int vy, int radius) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.radius = radius;
		diameter = radius * 2;
	}

	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, diameter, diameter);
	}

	public void move() {
		x += vx;
		y += vy;

		if (y <= 0) {
			y = 0;
			vy = -vy;
		} else if (y + diameter >= Pong.GAME_HEIGHT) {
			y = Pong.GAME_HEIGHT - diameter;
			vy = -vy;
		}
	}

	public void checkCollision(Paddle p) {
		int bx = x + radius;
		int by = y + radius;
		int cx = Math.max(p.getX(), Math.min(p.getX() + p.getWidth(), bx));
		int cy = Math.max(p.getY(), Math.min(p.getY() + p.getHeight(), by));
		int dx = bx - cx;
		int dy = by - cy;
		if(dx * dx + dy * dy <= radius * radius) {
			vx = -vx;
			if(vx > 0) {
				x = p.getX() + p.getWidth() + 1;
			} else {
				x = p.getX() - diameter - 1;
			}
		}
	}
	
	public void reset(int x, int y, int vx, int vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
