import java.awt.Color;
import java.awt.Graphics;

class Food extends GameObject {
	
	public Food(int x, int y) {
		super(x, y, ID.FOOD);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 16, 16);
	}
}
