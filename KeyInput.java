import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		// Code for handling input
		for(GameObject obj : handler.object) {
			if(obj.getId() == ID.PLAYER) {
				Player player = (Player) obj;
				int vel = 0;
				if(obj.getVelX() != 0 && player.getCanMove()) {
					vel = Math.abs(obj.getVelX());
					if(key == 38) { /* Up arrow */
						obj.setVelY(-vel);
						obj.setVelX(0);
						player.setCanMove(false);
					} else if(key == 40) { /* Down arrow */
						obj.setVelY(vel);
						obj.setVelX(0);
						player.setCanMove(false);
					}
				} else if (obj.getVelY() != 0 && player.getCanMove()) {
					vel = Math.abs(obj.getVelY());
					if(key == 39) { /* Right arrow */
						obj.setVelX(vel);
						obj.setVelY(0);
						player.setCanMove(false);
					} else if(key == 37) { /* Left arrow */
						obj.setVelX(-vel);
						obj.setVelY(0);
						player.setCanMove(false);
					}
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
	}

}
