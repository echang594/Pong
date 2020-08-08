import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	int x, y, radius;
	
	public Ball(int x, int y, int radius) {
	
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	 
	public void paint(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillOval(x,  y,  radius, radius);
		
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
