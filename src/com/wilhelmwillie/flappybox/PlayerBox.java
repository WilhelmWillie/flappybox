package com.wilhelmwillie.flappybox;

public class PlayerBox {
	public float x = 0;
	public float y = 0;

	private float yVelocity;
	private float gravity;
	
	public PlayerBox (int width, int height) {
		this.x = width / 2 -16;
		this.y = height / 2 -16;
		
		yVelocity = 0F;
		gravity = 0.655F;
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void update(double delta) {
		y += yVelocity * delta;
		
		yVelocity += gravity * delta;
	}

	public void flap() {
		if (y >= 16) {
			yVelocity = -9F;
		}
	}
}
