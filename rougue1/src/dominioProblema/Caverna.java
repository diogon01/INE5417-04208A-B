package dominioProblema;


import java.util.Vector;


import logica.itens.Item;

public class Caverna {
	
	//TODO: Vericar melhor as posicoes
	protected Posicao posicoes[][] = new Posicao[8][8];
	protected Jogador jogador1;
	protected Jogador jogador2;
	
	protected boolean partidaEmAndamento;
	protected boolean conectado;
	protected Vector<Posicao> posicoesAfetadas = new Vector<Posicao>();

	
	public boolean informarConectado() {
		return conectado;
	}

	public void estabelecerConectado(boolean valor) {
		conectado = valor;
	}

	public boolean informarEmAndamento() {
		return partidaEmAndamento;
	}
	
	public int click(int linha, int coluna) {
		boolean vez = jogador1.informarDaVez();
		int resultado;
		if(vez) {
			resultado = this.tratarLance(jogador1, linha, coluna);
		}else {
			resultado = 8;
		}
		return resultado;
	}
	
	public Lance informarJogada(int linha, int coluna) {
		Lance lance = new Lance();
		lance.assumir(linha, coluna);
		return lance;
	}
		
	public void iniciar() {
		for (int linha=1; linha<9; linha++){
			for (int coluna=1; coluna<9; coluna++){
				posicoes[(linha-1)][(coluna-1)] = new Posicao();				
			};
		};	
	}
	
	public void criarJogador(String idJogador) {
		if(jogador1 == null) {
			jogador1 = new Jogador();
			jogador1.iniciar();
			jogador1.assumirNome(idJogador);
		}else {
			jogador2 = new Jogador();
			jogador2.iniciar();
			jogador2.assumirNome(idJogador);
		}
	}
	
	public void zerarPosicoesAfetadas() {
		posicoesAfetadas = new Vector<Posicao>();
	}
	
	private Posicao recuperarPosicao(int linha, int coluna) {
		return (posicoes[(linha-1)][(coluna-1)]);
	}
	
	public boolean verificarOcupada(int linha, int coluna) {
		Posicao posicao = this.recuperarPosicao(linha, coluna);
		return (posicao.informarOcupada());		
	}
	
	public int tratarLance(Jogador jogador, int linha, int coluna) {
		boolean ocupada = this.verificarOcupada(linha, coluna);
		boolean lancePossivel = false;
		boolean vez;
		if(ocupada) {
			return 11;
		} else {
			return 10;
		}
	}
	
	public void finalizarPartida() {
		int placar1 = this.informarPlacar(jogador1);
		int placar2 = this.informarPlacar(jogador2);
		partidaEmAndamento = false;
		jogador1.desabilitar();
		jogador2.desabilitar();
		if(placar1 > placar2) {
			jogador1.assumirVencedor();
		} else if (placar2 > placar1) {
			jogador2.assumirVencedor();
		}
	}
	
	public int informarPlacar(Jogador jogador) {
		Item[] itens = new Item[0];
		return itens.length;
	}

}
