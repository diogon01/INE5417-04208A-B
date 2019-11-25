package util;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Recurso {
	
	private static HashMap<String, BufferedImage> sprites;
	
	public static void init() {
		
		sprites = new HashMap<String, BufferedImage>();
		
		File folder = new File("res/entidades");
		
		for (File file: folder.listFiles()) {
			try {
				sprites.put(file.getName().replaceAll(".png", ""), ImageIO.read(file));
			}catch (IOException error) {
				System.err.println("[Util][Gerenciador de recursos]: Leitor de exceção" + file.getName());
			}
		}
		System.out.println("[Util][Gerenciador de recursos]: terminou de ler os arquivos de sprite");
	}
	
	public static BufferedImage getSprite(String nome) {
		return sprites.get(nome);
	}
	
	public int geradorDeNumeroAleatorios(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public  Image ajusteTmanhoImagem(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}


}

