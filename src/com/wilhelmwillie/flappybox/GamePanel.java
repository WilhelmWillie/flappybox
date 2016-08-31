package com.wilhelmwillie.flappybox;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1550616787891545241L;
	
	public Game game;

	public GamePanel(Game _game) {
		game = _game;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(187, 236, 240));
		g.fillRect(0, 0, this.getWidth()-1, this.getHeight()-1);
		
		g.setColor(new Color(230, 138, 138));
		g.fillRect((int)game.player.x, (int)game.player.y, 32, 32);
		g.setColor(new Color(191, 48, 48));
		g.drawRect((int)game.player.x, (int)game.player.y, 32, 32);
		
		/* This piece of code replaces the box with an image of a friend, this is in here as a joke 
		Image image = null;
		try {
			image = ImageIO.read(new File("brian.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.drawImage(image, (int) game.player.x, (int) game.player.y, (ImageObserver) this);
		*/
		
		// Time to loop through each obstacle! So fun!
		Iterator<Entry<Integer, Obstacle>> it = game.obstacles.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<Integer, Obstacle> pairs = (Entry<Integer, Obstacle>)it.next ();
			 
			Obstacle temp = (Obstacle) pairs.getValue ();
			 
			if (temp.x > -50) {
				g.setColor(new Color(162,222,106));
				g.fillRect((int)temp.x, (int)temp.y, (int)temp.width, (int)temp.height);
				g.setColor(new Color(110, 158, 65));
				g.drawRect((int)temp.x, (int)temp.y, (int)temp.width, (int)temp.height);
			}
		}
		
		// Draw score in top left corner
		if (!game.gameOver) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Verdana", Font.BOLD, 12));
			g.drawString("Score: " + game.score, 5, 15);
		}
		
		if (game.gameOver) {
			g.setColor(new Color(0, 0, 0, 0.5F));
			g.fillRect(0, 0, this.getWidth()-1, this.getHeight()-1);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Verdana", Font.BOLD, 32));
			g.drawString("Game Over!", this.getWidth () / 2 - 105, this.getHeight() / 2 - 30);
			g.drawString("Score: " + game.score, this.getWidth () / 2 - 70, this.getHeight() / 2 + 10);
			g.setFont(new Font("Verdana", Font.ITALIC, 20));
			g.drawString("Press space to play again!", this.getWidth () / 2 - 122, this.getHeight() / 2 + 40);
		}
	}
	
	public void render () {
		repaint ();
	}
}
