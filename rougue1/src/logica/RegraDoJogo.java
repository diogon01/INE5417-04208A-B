package logica;

import logica.entidade.EntidadeBase;
import util.Recurso;

public class RegraDoJogo {

	private static EntidadeBase jogador;
	
	public static void inicializarJogo() {
		Recurso.init();
		
		jogador = new EntidadeBase("jogador", 200, 20);
	}

	/**
	 * Chamar depois de carregar o Main e inicilizar o objetos
	 */
	public static EntidadeBase getJogador() {
		return jogador;
	}
}
