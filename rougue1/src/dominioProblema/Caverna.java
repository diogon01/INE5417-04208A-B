package dominioProblema;


import java.util.Vector;


import logica.itens.Item;

public class Caverna {
	
	//TODO: Vericar melhor as posicoes
	protected Posicao posicoes[][] = new Posicao[9][11];
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
	
	public void criarJogador1(String idJogador) {
		this.jogador1 = new Jogador(idJogador, 1, 7, 3, true);
	}
	
	public void criarJogador2(String idJogador) {
		this.jogador2 = new Jogador(idJogador, 2, 0, 4, false);
	}
	
	public Jogador getJogador(String idJogador) {
		return jogador1.informarNome().equals(idJogador) ? jogador1 : jogador2;
	}
	
	public void zerarPosicoesAfetadas() {
		posicoesAfetadas = new Vector<Posicao>();
	}
	
	private Posicao recuperarPosicao(int linha, int coluna) {
		return (posicoes[(linha-1)][(coluna-1)]);
	}
	

	
	public int tratarLance(Jogador jogador, int linha, int coluna) {
		return 10;
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
