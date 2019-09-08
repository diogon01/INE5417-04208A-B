package diablo2d;

import interfaceUsuario.Janela;
import logica.GameLogic;
import util.Recurso;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("[Main]: Iniciando o jogo...");
		Recurso.init();
		Janela.criarJanela();
		Janela.tornarVisivel();
		GameLogic.iniciarJogo();
		
		System.out.println("[Main]: Jogo Iniciado");
	
		

	}

}
