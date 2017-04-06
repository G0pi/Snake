import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

// Handler will handle every GameObject
public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();	
	Random random;

	public Handler() {
		random = new Random();
	}

	public void tick() {
		// GameObject ticks
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}

		// Food handler:
		if(!existsFood()) {
			int x = random.nextInt(36) * 16 + 16;
			int y = random.nextInt(36) * 16 + 16;
			addObject(new Food(x, y));
		}
	}

	public void render(Graphics g) {
		for(int  i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public boolean existsFood() {
		boolean found = false;
		for(GameObject g : object) {
			if(g.getId() == ID.FOOD) {
				found = true;
				break;
			}
		}

		return found;
	}

}
