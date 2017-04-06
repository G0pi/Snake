import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 2017_04_06_001L;

	// Aspect ratio: HEIGHT = WIDTH / 16 * 9
	public static final int WIDTH = 640, HEIGHT = WIDTH;

	private Thread thread;
	private boolean running = false;

	private Handler handler;
	private Random random;


	public Game() {
		handler = new Handler();
		random = new Random();
		this.addKeyListener(new KeyInput(handler));

		new Window(WIDTH, HEIGHT, "Snake", this);
		
		// Objects
		handler.addObject(new Player(16, 16, this));
		handler.addObject(new Barrier());
	}

	// Normal game loop
	public void run() {
		long lastTime = System.nanoTime();
		// ticks/s
		// This will be kind of a difficulity slider in Snake
		double amountOfTicks = 10.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while(delta >= 1) {
				tick();
				delta--;
			}

			if(running)
				render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		// Ticks every GameObject.
		handler.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Renders every GameObject.
		handler.render(g);

		g.dispose();
		bs.show();
	}

	public void gameOver() {
		// TODO: Make better gameOver method, that doesn't just exit app
		System.exit(0);
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Game();
	}

	public Handler getHandler() {
		return handler;
	}
} 
