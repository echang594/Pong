import java.awt.Graphics;

public class Paddle {
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean left;
	
	public Paddle(int x, int y, int w, int h, boolean l) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		left = l;
	}
	
	public void paint(Graphics g) {
		g.fillRect(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isLeft() {
		return left;
	}
}
