package com.wilhelmwillie.flappybox;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Game {
	public final String gameName = "Flappy Box";
	
	// Difference between gameRunning and gameBegin:
	// gameRunning - Used to see if the actual game (render, update, event listeners, etc.) is running
	// isFlapping - Used to see if the game is actually flapping
	public boolean gameRunning = true; 
	public boolean gameOver = false;
	public boolean isFlapping = false; 
	
	public FlappyDisplay display;
	
	public PlayerBox player;
	public HashMap<Integer,Obstacle> obstacles = new HashMap<Integer,Obstacle>();
	
	private long lastCreatedObstacle;
	private final long OBSTACLE_CYCLE_TIME = 3;
	
	// Private variables used for running the game loop
	@SuppressWarnings("unused")
	private int fps;
	private long lastFpsTime;

	public int score = 0;
	
	public void begin () {
		display = new FlappyDisplay ();
		display.init (this);
		
		player = new PlayerBox (display.gamePanel.getWidth (), display.gamePanel.getHeight ());
		
		lastCreatedObstacle = System.currentTimeMillis();
		
		display.setTitle("FlappyBox");
		
		theLoop();
	}
	
	public void theLoop () {
		// This is the code used for maintaining the game loop
		long lastLoopTime = System.nanoTime ();
		final int TARGET_FPS = 30;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		
		while (gameRunning) {
			long now = System.nanoTime ();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double)OPTIMAL_TIME);
			
			lastFpsTime += updateLength;
			fps++;
			
			if (lastFpsTime >= 1000000000) {
				// System.out.println("(FPS: " + fps + ")");
				lastFpsTime = 0;
				fps = 0;
			}
			
			// update
			update (delta);
			
			// render
			display.render ();
			
			try {
				Thread.sleep(Math.abs((lastLoopTime - System.nanoTime() + OPTIMAL_TIME)/1000000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
 
	public void update(double delta) {
		if (isFlapping) {
			if (!gameOver) {
				player.update(delta);
			}
			
			if (player.y >= display.gamePanel.getHeight () - 16) {
				gameOver ();
			}
			
			if (System.currentTimeMillis() - lastCreatedObstacle >= OBSTACLE_CYCLE_TIME * 1000) {
				int offset = -90 + (int)(Math.random() * ((90 - (-90)) + 1));
				Obstacle top = new Obstacle (this);
				top.x = display.gamePanel.getWidth ();
				top.y = 0 + offset - 210 ;
				top.isTop = true;
				
				Obstacle bot = new Obstacle (this);
				bot.x = display.gamePanel.getWidth ();
				bot.y = display.gamePanel.getHeight () - bot.height + offset + 210;
				obstacles.put(obstacles.size() + 1, top);
				obstacles.put(obstacles.size() + 1, bot);
				
				lastCreatedObstacle = System.currentTimeMillis();
			}
			
			Iterator<Entry<Integer, Obstacle>> it = obstacles.entrySet().iterator();
			
			while (it.hasNext()) {
				Entry<Integer, Obstacle> pairs = (Entry<Integer, Obstacle>)it.next ();
				 
				Obstacle temp = (Obstacle) pairs.getValue ();
				
				if (temp.isTop) {
					if (player.x + 16 >= temp.x + 25) {
						score++;
						temp.isTop = false;
					}
				}
				
				if (temp.x > -50 && isFlapping) {
					temp.update(delta);
				}
			}
		}
	}

	public void gameOver() {
		isFlapping = false;
		gameOver = true;
	}

	public void registerFlap() {
		player.flap ();
	}
}
