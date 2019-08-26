package diablo2d;

import gui.Janela;
import util.Recurso;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("[Main]: Iniciando o jogo...");
		
		Janela.criarJanela();
		Janela.tornarVisivel();
		Recurso.init();
		
		System.out.println("[Main]: Jogo Iniciado");
	
		

	}

}
