import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	private int x;
	private int y;
	private int vx;
	private int vy;
	private int width;
	private int height;

	public Ball(int x, int y, int vx, int vy, int width, int height) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.width = width;
		this.height = height;
	}

	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, width, height);
	}

	public void move() {
		x += vx;
		y += vy;

		if (y <= 0) {
			y = 0;
			vy *= -1;
		} else if (y + height >= Pong.GAME_HEIGHT) {
			y = Pong.GAME_HEIGHT - height;
			vy *= -1;
		}
	}

	public void checkCollision(Paddle p) {
		if (p.isLeft()) {
			if (x + width >= p.getX() && x <= p.getX() + p.getWidth()
					&& y <= p.getY() + p.getHeight() && y + height >= p.getY()) {
				vx *= -1;
			}
		} else {
			if (x <= p.getX() + p.getWidth() && x + width >= p.getX()
					&& y <= p.getY() + p.getHeight() && y + height >= p.getY()) {
				vx *= -1;
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setVx(int vx) {
		this.vx = vx;
	}
	
	public void setVy(int vy) {
		this.vy = vy;
	}
	
}
