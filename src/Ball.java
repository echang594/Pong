import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	int x, y, radius, vx, vy;
	
	public Ball(int x, int y, int vx, int vy, int radius) {
	
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.radius = radius;
	}
	
	public void move(int width, int height) {
		
		x += vx;
		y += vy; 
		
		if (x <= radius) {
			x=radius;
			vx*=-1;
		} else if (x >= width - radius){
			x = width - radius;
			vx *= -1;
		}
		
		if (y <= radius) {
			y = radius;
			vy *= -1;
		} else if (y >= height - radius) {
			y = height - radius;
			vy *= -1;
		}
		
	}
	
	public void draw(Graphics g) {
		
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
