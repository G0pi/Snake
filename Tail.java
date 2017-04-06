import java.awt.Color;
import java.awt.Graphics;

public class Tail {
	int x, y, lastX, lastY;	

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 16, 16);
	}
}
