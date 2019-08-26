package logica;

import logica.entidade.Entidade;
import util.Recurso;

public class RegraDoJogo {

	private static Entidade jogador;
	
	public static void inicializarJogo() {
		Recurso.init();
		
		jogador = new Entidade("jogador", 200, 20);
	}

	/**
	 * Chamar depois de carregar o Main e inicilizar o objetos
	 */
	public static Entidade getJogador() {
		return jogador;
	}
}
