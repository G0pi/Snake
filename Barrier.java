import java.awt.Color;
import java.awt.Graphics;

public class Barrier extends GameObject {

	private int barrierWidth = 16;

	public Barrier() {
		super(0, 0, ID.WALL);
	}

	public void tick() {

	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		// Top barrier
		g.fillRect(0, 0, Game.WIDTH, barrierWidth);
		// Left barrier
		g.fillRect(0, 0, barrierWidth, Game.HEIGHT);
		// Bottom barrier
		g.fillRect(0, Game.HEIGHT - barrierWidth, Game.WIDTH, barrierWidth);
		// Right Barrier
		g.fillRect(Game.WIDTH - barrierWidth, 0, barrierWidth, Game.HEIGHT);
	}
}
