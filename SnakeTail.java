import java.awt.Color;
import java.awt.Graphics;

public class SnakeTail {
	int x, y, lastX, lastY;	

	public SnakeTail(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 16, 16);
		g.setColor(Color.darkGray);
		g.drawRect(x, y, 16, 16);
	}

	public void move(int x, int y) {
		this.lastX = this.x;
		this.lastY = this.y;
		this.x = x;
		this.y = y;
	}
}
