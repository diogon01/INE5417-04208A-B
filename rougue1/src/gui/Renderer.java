package gui;

import java.awt.Graphics;
import java.awt.Window;
import java.awt.image.BufferedImage;
import logica.entidade.EntidadeBase;
import logica.nivel.Level;
import util.Recurso;

public class Renderer {

	private int tamanhoZoom;
	
	
	private static final int ZOOM_LEVEL = 3;
	
	public static void renderEntidade(EntidadeBase entidadeData, Graphics graphics) {
		BufferedImage sprite = Recurso.getSprite(entidadeData.getNome());
		graphics.drawImage(sprite, entidadeData.getPosX(), entidadeData.getPosY(),
				sprite.getWidth()/ZOOM_LEVEL, sprite.getHeight()/ZOOM_LEVEL, null);
		
	}
	

	/*
	 * public void Renderer() { this.tamanhoZoom = 3; }
	 * 
	 *//**
		 * Desenha o jogador no meio da tela
		 * 
		 * @param entidade
		 * @param graficos
		 */
	/*
	 * 
	 * public void desenharJogador(Graphics graficos) { BufferedImage desenho =
	 * Recurso.getSprite("player");
	 * 
	 * int desenhoPosX = (Window.WIDTH / 2) - (desenho.getWidth() / 2) *
	 * tamanhoZoom; int desenhoPosY = (Window.HEIGHT / 2) - (desenho.getHeight() /
	 * 2) * tamanhoZoom;
	 * 
	 * graficos.drawImage(desenho, desenhoPosX, desenhoPosY, desenho.getWidth() *
	 * tamanhoZoom, desenho.getHeight() * tamanhoZoom, null);
	 * 
	 * }
	 * 
	 *//**
		 * Adiciona o nivel na posicao correta.
		 * 
		 * @param levelData
		 * @param jogador
		 * @param graficos
		 *//*
			 * public void desenharLevel(Level levelData, EntidadeBase jogador, Graphics
			 * graficos) { for (int y = 0; y < levelData.getSizeY(); y++) { for (int x = 0;
			 * x < levelData.getSizeX(); x++) { BufferedImage desenho =
			 * Recurso.getSprite(levelData.getEntidadeAt(x, y).getNome());
			 * 
			 * int desenhoPosX = levelData.getEntidadeAt(x, y).getPosX() * tamanhoZoom +
			 * ((Window.WIDTH / 2) - jogador.getPosX() * tamanhoZoom - (desenho.getWidth() /
			 * 2) * tamanhoZoom); int desenhoPosY = levelData.getEntidadeAt(x, y).getPosY()
			 * * tamanhoZoom + ((Window.WIDTH / 2) - jogador.getPosY() * tamanhoZoom -
			 * (desenho.getHeight() / 2) * tamanhoZoom); graficos.drawImage(desenho,
			 * desenhoPosX, desenhoPosY, desenho.getWidth() * tamanhoZoom,
			 * desenho.getHeight() * tamanhoZoom, null); } } }
			 */
}
