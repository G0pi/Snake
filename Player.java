import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player extends GameObject {
	
	public static final int vel = 16;

	private ArrayList<SnakeTail> tail = new ArrayList<> ();
	private int startSize = 4;
	private Game game;
	private boolean canMove;

	public Player(int x, int y, Game game) {
		super(x, y, ID.PLAYER);
		this.game = game;
		for(int i = 1; i < startSize; i++) {
			tail.add(new SnakeTail(x-16*i, y));
		}
		velX = vel;
		canMove = true;
	}

	@Override
	public void tick() {
		tail.get(0).move(x, y);
		x += velX;
		y += velY;
		canMove = true;

		for(SnakeTail s : tail) {
			if(overlap(s.x, s.y)) {
				game.gameOver();	
			}
		}

		Handler handler = game.getHandler();
		for(GameObject g : handler.object) {
			if(g.getId() == ID.FOOD) {
				if(overlap(g.getX(), g.getY()))
					eatFood(g);
			}
		}

		// Going out of bounds check
		// higherBoundry = WIDTH - barrier width
		int higherBoundry = 640 - 16;
		int lowerBoundry = 16;
		if(x > higherBoundry) x = lowerBoundry;
		if(x < lowerBoundry) x = higherBoundry;
		if(y < lowerBoundry) y = higherBoundry;
		if(y > higherBoundry) y = lowerBoundry;

		for (int i = 1; i < tail.size(); i++) {
			tail.get(i).move(tail.get(i-1).lastX, tail.get(i-1).lastY);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 16, 16);
		g.setColor(Color.darkGray);
		g.drawRect(x, y, 16, 16);
		for(SnakeTail t : tail) {
			t.render(g);
		}
	}

	public boolean overlap(int x, int y) {
		if(this.x == x && this.y == y) {
			return true;
		}
		return false;
	}

	public void eatFood(GameObject food) {
		Handler handler = game.getHandler();
		int x = tail.get(tail.size() - 1).lastX;
		int y = tail.get(tail.size() - 1).lastY;
		tail.add(new SnakeTail(x, y));
		handler.removeObject(food);
	}

	public boolean getCanMove() {
		return canMove;
	}

	public void setCanMove(boolean b) {
		canMove = b;	
	}
}
