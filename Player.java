import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {


	public Player(int x, int y) {
		super(x, y, ID.PLAYER);
		velX = 16;
	}

	public void tick() {
		x += velX;
		y += velY;
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 16, 16);
	}
}
