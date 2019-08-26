package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import logica.entidade.Entidade;
import util.Recurso;

public class Renderer {

	private static final int ZOOM_LEVEL = 3;

	public static void renderEntidade(Entidade entidade, Graphics graphics) {
		BufferedImage sprite = Recurso.getSprite(entidade.getNome());
		graphics.drawImage(sprite, entidade.getPosX(), entidade.getPosY(), sprite.getWidth() * ZOOM_LEVEL,
				sprite.getHeight() * ZOOM_LEVEL, null);
	}

}
