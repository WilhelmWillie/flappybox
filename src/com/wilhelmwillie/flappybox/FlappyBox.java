package com.wilhelmwillie.flappybox;

public class FlappyBox {

	public static void main(String[] args) {
		// FONT ANTI-ALIAS STUFF :-)
		System.setProperty("awt.useSystemAAFontSettings", "lcd");
		System.setProperty("swing.aatext", "true");
		
		Game game = new Game ();
		game.begin ();
	}

}
