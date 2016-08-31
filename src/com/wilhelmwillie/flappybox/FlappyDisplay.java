package com.wilhelmwillie.flappybox;

import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlappyDisplay extends JFrame implements KeyListener {
	public FlappyDisplay() {
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 6869192532969196078L;

	public Game game;
	
	public JPanel gamePanel;
	
	public void init(Game _game) {
		game = _game;
		
		gamePanel = new GamePanel(game);
		
		
		setTitle(game.gameName);
		getContentPane().setLayout(null);
		
		getContentPane().add(gamePanel);
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		Insets insets = getInsets();
		gamePanel.setBounds(0 + insets.left, 0 + insets.top, 320 + insets.left, 480 + insets.top);
		setSize(320,480);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public void render() {
		((GamePanel) gamePanel).render ();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (game.gameOver) {
				game.score = 0;
				
				if (game.isFlapping == false) { 
					game.player.x = gamePanel.getWidth () / 2 - 16;
					game.player.y = gamePanel.getHeight () / 2 - 16;
					game.gameOver = !game.gameOver;
					game.obstacles.clear();
				}
			} else {
				if (game.isFlapping == false) { 
					game.isFlapping = true; 
				}
				
				game.registerFlap();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
