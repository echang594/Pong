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

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, radius, radius);
	}

	public void move() {
		x += vx;
		y += vy;

		if (y - radius <= 0) {
			y = radius;
			vy *= -1;
		} else if (y + radius >= Pong.GAME_HEIGHT) {
			y = Pong.GAME_HEIGHT - radius;
			vy *= -1;
		}
	}

	public void checkCollision(Paddle p) {
		if (p.isLeft()) {
			if (x + 2 * radius >= p.getX() && x <= p.getX() + p.getWidth()
					&& y <= p.getY() + p.getHeight() && y + 2 * radius >= p.getY()) {
				vx *= -1;
			}
		} else {
			if (x <= p.getX() + p.getWidth() && x + 2 * radius >= p.getX()
					&& y <= p.getY() + p.getHeight() && y + 2 * radius >= p.getY()) {
				vx *= -1;
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

	public int getRadius() {
		return x;
	}

	public boolean isInContact() {
		return false;
	}

}
