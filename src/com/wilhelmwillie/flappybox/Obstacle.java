package com.wilhelmwillie.flappybox;

import java.awt.Rectangle;

public class Obstacle {
	public int id = 0;
	public float x = 0;
	public float y = 0;
	public float width = 50;
	public float height = 380;
	public boolean isTop = false;
	
	private Game g;
	private PlayerBox p;
	
	public Obstacle (Game game) {
		g = game;
		p = g.player;
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void update(double delta) {
		x -= 1.75F * delta;
		
		if (collidesWithPlayer()) {
			g.gameOver ();
		}
	}
	
	private boolean collidesWithPlayer () {
		Rectangle obst = new Rectangle((int)x, (int)y, (int)width, (int)height);
		Rectangle play = new Rectangle((int)p.x, (int)p.y, 32, 32);
		return obst.intersects(play);
	}

}
