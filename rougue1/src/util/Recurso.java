package util;

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

}
