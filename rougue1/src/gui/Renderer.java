package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

import logica.entidade.EntidadeBase;
import util.Recurso;

public class Renderer {

	private static final int ZOOM_LEVEL = 3;

	/**
	 * Renderiza o jogador e posiicona na tela
	 * @param entidade 
	 * @param graphics
	 */
	public void renderEntidade(EntidadeBase entidade, Graphics graphics) {
		BufferedImage sprite = Recurso.getSprite(entidade.getNome());
		graphics.drawImage(sprite, entidade.getPosX(), entidade.getPosY(), sprite.getWidth() * ZOOM_LEVEL,
				sprite.getHeight() * ZOOM_LEVEL, null);
	}
	

}
