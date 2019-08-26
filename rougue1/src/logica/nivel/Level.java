package logica.nivel;

import logica.entidade.Entidade;
import logica.entidade.Pavimento;

public class Level {

	private Entidade[][] andar;
	
	public Level(String[] andarInfo) {
		
		andar = new Entidade[andarInfo.length][];
		
		for (int y=0; y < andarInfo.length; y++) {
			andar[y] = new Entidade[andarInfo[y].length()];
			
			for (int x=0; x < andarInfo[y].length(); x++) {
				switch(andarInfo[y].charAt(x)) {
				case '#':
					andar[y][x] = new Pavimento("muro", x*32, y*32);
					break;
				case '.':
					andar[y][x] =  new Pavimento("passagem", x*32, y*32);
				
				}
			}
		}
		
	}
	
	public int getSizeX() {
		return andar[0].length;
	}
	
	public int getSizeY() {
		return andar.length;
	}
	
	public Entidade getEntidadeAt(int x, int y) {
		return andar[y][x];
	}
	
	
}
