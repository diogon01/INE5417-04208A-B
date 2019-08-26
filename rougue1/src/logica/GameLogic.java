package logica;

import javax.swing.Timer;

import logica.entidade.Entidade;
import util.Recurso;

public class GameLogic {

	private static Timer tempo;

	private static Entidade jogador;

	public static void iniciarJogo() {

		Recurso.init();

		jogador = new Entidade("jogador", 200, 200);

		tempo = new Timer(20, new GameLoop());
		tempo.start();
	}

	/**
	 * Altera a posicao do jogadores
	 * 
	 * @param dirX Move o jogador no eixo X
	 * @param dirY Move o jogador no eixo Y
	 */
	public static void moverJogador(int dirX, int dirY) {
		jogador.setPosition(jogador.getPosX() + dirX, jogador.getPosY() + dirY);
	}

	public static Entidade getJogador() {
		return jogador;
	}

}
