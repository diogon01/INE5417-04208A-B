package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;

import javax.swing.JPanel;

import util.Recurso;

public class GameScreen extends JPanel {
	
	public GameScreen() {
		super();
		this.setFocusable(true);
		
		System.out.println("[GUI][Gerenciador de exibição]: Tela do jogo criada!");
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		graphics.setColor(Color.WHITE);
		graphics.fillRect(30, 30, 30, 30);
		graphics.fillRect(30, 30, 50, 50);
		graphics.fillRect(50, 100, 40, 40);
		
		graphics.drawImage(Recurso.getSprite("player"), 200, 200, 32*3, 32*3, null);
		
		repaint();
	}

}
