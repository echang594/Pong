import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Ball {
	private int x;
	private int y;
	private int vx;
	private int vy;
	private int radius;
	private int diameter;
	private ImageIcon imageIcon;

	public Ball(int x, int y, int vx, int vy, int radius, String filename) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.radius = radius;
		diameter = radius * 2;
		Image image = new ImageIcon(filename).getImage();
		imageIcon = new ImageIcon(image.getScaledInstance(diameter, diameter, Image.SCALE_DEFAULT));
	}

	public void paint(Graphics g, Component c) {
		g.setColor(Color.WHITE);
		imageIcon.paintIcon(c, g, x, y);
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
		if (dx * dx + dy * dy <= radius * radius) {
			vx = -vx;
			if (vx > 0) {
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

	public int getRadius() {
		return radius;
	}

	public int getDiameter() {
		return diameter;
	}
}
