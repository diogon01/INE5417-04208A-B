package logica;

import javax.swing.Timer;

import logica.entidade.EntidadeBase;
import logica.nivel.Level;
import util.Recurso;

public class GameLogic {

	private static Timer tempo;

	private static EntidadeBase jogador;
	
	private static Level levelAtual;

	/**
	 * Chamado pelo Man quando o jogo *e iniciado
	 */
	public static void iniciarJogo() {


		jogador = new EntidadeBase("jogador", 64, 64);
		
		levelAtual = new Level(new String[] {
			"##########",
			"#...#....#",
			"#...#....#",
			"##.##....#",
			"#...#....#",
			"#........#",
			"#...#....#",
			"##########"
		});

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

	public static EntidadeBase getJogador() {
		return jogador;
	}

}
