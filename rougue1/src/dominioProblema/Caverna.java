package dominioProblema;

import java.awt.Color;

import java.awt.Graphics;
import java.util.Random;
import java.util.Vector;
import util.Recurso;

public class Caverna {

	// TODO: Vericar melhor as posicoes
	public Posicao[][] posicoes;
	protected Jogador jogador1;
	protected Jogador jogador2;
	protected boolean partidaEmAndamento;
	protected boolean conectado;
	protected EstadoJogo estadoJogo;
	private EstadoServidorNetgames estadoServidorNG;
	protected Vector<Posicao> posicoesAfetadas = new Vector<Posicao>();
	protected Recurso recurso = new Recurso();

	public boolean informarConectado() {
		return estadoServidorNG == EstadoServidorNetgames.CONECTADO;
	}

	public void estabelecerConectado() {
		this.estadoServidorNG = EstadoServidorNetgames.CONECTADO;
	}

	public void estabelecerDesconectado() {
		this.estadoServidorNG = EstadoServidorNetgames.DESCONECTADO;
	}

	public boolean informarEmAndamento() {
		return estadoJogo == EstadoJogo.PARTIDA_EM_ANDAMENTO;
	}

	public Caverna(int linhas, int colunas) {
		zerarPosicoesAfetadas(linhas, colunas);
	}

	public Lance informarJogada(ObjetosCaverna objeto, int linha, int coluna) {
		Lance lance = new Lance();
		lance.assumir(objeto, linha, coluna);
		return lance;
	}

	public void iniciar(int linhas, int colunas) {
		for (int linha = 0; linha < linhas; ++linha) {
			for (int coluna = 0; coluna < colunas; ++coluna) {
				// limpa o conteúdo da célula
				posicoes[linha][coluna].esvaziar();
			}
		}
	}

	/**
	 * 
	 * @param linhas
	 * @param colunas
	 */
	public void iniciarMapa(int linhas, int colunas) {
		System.out.println("[Caverna][iniciarMapa][Piso]: Adicionando piso ao mapa");
		for (int linha = 0; linha < linhas; ++linha) {
			String alvo = null;
			for (int coluna = 0; coluna < colunas; ++coluna) {
				if (linha > 0 && linha < (linhas - 1) && (coluna > 0 && coluna < (colunas - 1))) {
					alvo = String.format("[AtribuirPosicao][PISO]:[Linha]:%s [Coluna]:%s", linha, coluna);
					this.atribuirPosicao(linha, coluna, ObjetosCaverna.PISO);
				} else {
					alvo = String.format("[AtribuirPosicao][PAREDE]:[Linha]:%s [Coluna]:%s", linha, coluna);
					this.atribuirPosicao(linha, coluna, ObjetosCaverna.PAREDE);
				}
				System.out.println(alvo);

			}
		}

		int[] r;
		r = new int[] { 1, 2, 3 };
		System.out.println("[Caverna][iniciarMapa][Jogador1]: Criando Jogador 1 Paladino");

		this.atribuirPosicao(14, 8, ObjetosCaverna.JOGADOR1);

		System.out.println("[Caverna][iniciarMapa][Jogador2]: Criando Jogador 2 Mago");
		this.atribuirPosicao(14, 12, ObjetosCaverna.JOGADOR2);

		System.out.println("[Caverna][iniciarMapa][TESOURO]: Criando o Tesouro");
		this.atribuirPosicao(6, 10, ObjetosCaverna.TESOURO);

	}

	public static int getRandom(int[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

	public boolean jogoEmpatou() {
		/*
		 * for (int row = 0; row < GameMain.ROWS; ++row) { for (int col = 0; col <
		 * GameMain.COLS; ++col) { if (cells[row][col].content == Seed.EMPTY) { return
		 * false; // an empty seed found, not a draw, exit } } }
		 */
		return false; // no empty cell, it's a draw
	}

	public Jogador getJogador(String idJogador) {
		return jogador1.informarNome().equals(idJogador) ? jogador1 : jogador2;
	}

	public void zerarPosicoesAfetadas(int linhas, int colunas) {
		posicoes = new Posicao[linhas][colunas];
		for (int linha = 0; linha < linhas; ++linha) {
			for (int coluna = 0; coluna < colunas; ++coluna) {
				// Acerta a posicao do Jogo
				posicoes[linha][coluna] = new Posicao(linha, coluna);
			}
		}
	}

	public void setEmAndamento(boolean partidaEmAndamento) {
		this.estadoJogo = EstadoJogo.PARTIDA_EM_ANDAMENTO;
	}

	public Posicao getPosicao(int linha, int coluna) {
		return informaPosicao(linha, coluna);
	}

	public String getIdVencedor() {
		return jogador1.informarVencedor() ? jogador1.informarNome() : jogador2.informarNome();
	}

	public int tratarLance(Jogador jogador, int linha, int coluna, ObjetosCaverna objeto) {
		Posicao pose = this.informaPosicao(linha, coluna);
		if (pose.objeto == ObjetosCaverna.JOGADOR2 || pose.objeto == ObjetosCaverna.JOGADOR1) {
			return 11;
		}
		if (pose.objeto == ObjetosCaverna.PAREDE) {
			return 12;
		}
		return 10;
	}

	public void finalizarPartida() {
		int placar1 = this.informarPlacar(jogador1);
		int placar2 = this.informarPlacar(jogador2);
		partidaEmAndamento = false;
		jogador1.desabilitar();
		jogador2.desabilitar();
		if (placar1 > placar2) {
			jogador1.assumirVencedor();
		} else if (placar2 > placar1) {
			jogador2.assumirVencedor();
		}
	}

	public int informarPlacar(Jogador jogador) {

		return 0;
	}

	public void ativarPosicoesIniciais() {

	}

	public Posicao informaPosicao(int linha, int coluna) {
		return posicoes[linha][coluna];
	}

	public int jogada(int linha, int coluna, ObjetosCaverna objeto) {
		boolean vez = jogador1.informarDaVez();
		int resultado;
		if (vez) {
			resultado = this.tratarLance(jogador1, linha, coluna, objeto);
			if (resultado == 10 || (resultado == 9)) {

			}
		} else {
			resultado = 8;
		}
		return resultado;
	}

	public void atribuirPosicao(int linha, int coluna, ObjetosCaverna objeto) {
		this.posicoes[linha][coluna].objeto = objeto;
	}

	public void pintar(Graphics g, int tamanhoDaCelula, int meioDaGrade, int larguraDaCaverna, int larguraDaGrade,
			int larguralinhapreta, int paddingcelula, int tamanhojogador, int alturaDaCaverna, int linhas,
			int colunas) {

		g.setColor(Color.GRAY);
		for (int linha = 1; linha < linhas; ++linha) {
			g.fillRoundRect(0, tamanhoDaCelula * linha - meioDaGrade, larguraDaCaverna - 1, larguraDaGrade,
					larguraDaGrade, larguraDaGrade);
		}
		for (int coluna = 1; coluna < colunas; ++coluna) {
			g.fillRoundRect(tamanhoDaCelula * coluna - meioDaGrade, 0, larguraDaGrade, alturaDaCaverna - 1,
					larguraDaGrade, larguraDaGrade);
		}

		// Desenha todas as células
		for (int linha = 0; linha < linhas; ++linha) {
			for (int coluna = 0; coluna < colunas; ++coluna) {
				// Pintando a celula
				posicoes[linha][coluna].desenharPosicao(g, tamanhoDaCelula, meioDaGrade, larguraDaCaverna,
						larguraDaGrade, larguralinhapreta, paddingcelula, tamanhojogador, alturaDaCaverna, linhas);
			}
		}
	}

	public boolean jogadorVenceu(ObjetosCaverna jogadorLance, int linhaSelecionada, int colunaSelecionada) {
		// TODO Auto-generated method stub
		return false;
	}

	public EstadoServidorNetgames informarEstadoServidorNG() {
		return estadoServidorNG;
	}

	public void estabelecerEstadoServidorNG(EstadoServidorNetgames estadoServidorNG) {
		this.estadoServidorNG = estadoServidorNG;
	}

	// Metodo que esvazia a posicoes inicias do jogo
	public void esvaziar() {
		System.out.println("[Caverna][Esvaziar]: Reiniciando o estado da caverna!");
		this.jogador1 = null;
		this.jogador2 = null;
		this.estadoJogo = EstadoJogo.NOT_ANDAMENTO;
	}

	/**
	 * Cria o usuario com o ID Recebido do NetGames
	 * 
	 * @param idUsuario
	 */
	public void criarJogador(String idJogador) {
		if (jogador1 == null) {
			jogador1 = new Jogador();
			jogador1.iniciar();
			jogador1.assumirNome(idJogador);
		} else {
			jogador2 = new Jogador();
			jogador2.iniciar();
			jogador2.assumirNome(idJogador);
		}
	}

	public void estabelecerPartidaEmAndamento() {
		// TODO Auto-generated method stub
		this.estadoJogo = EstadoJogo.PARTIDA_EM_ANDAMENTO;
	}

	public EstadoJogo informarEstadoDoJogo() {
		// TODO Auto-generated method stub
		return this.estadoJogo;
	}

	/**
	 * Posicao recebida do NET-Games
	 * 
	 * @param posicao
	 */
	public void habilitarJogadores(Integer posicao) {
		if (posicao == 1) {
			jogador1.habilitar();
			jogador1.assumirSimbolo(true);
			jogador2.assumirSimbolo(false);

		} else {
			jogador2.habilitar();
			jogador2.assumirSimbolo(true);
			jogador1.assumirSimbolo(false);
		}
	}

	public void receberJogada(Lance jogada) {
		int linha = jogada.informarLinha();
		int coluna = jogada.informarColuna();
		boolean vez = jogador1.informarDaVez();
		
		this.atribuirPosicao(linha, coluna, jogada.informarObjeto());
		
		int resultado;
		if (vez) {
			resultado = this.tratarLance(jogador1, linha, coluna, jogada.informarObjeto());
		} else {
			resultado = this.tratarLance(jogador2, linha, coluna, jogada.informarObjeto());
		}
		if (resultado == 9) {
			this.finalizarPartida();
		}

	}

	public boolean informavezJogador1() {
		// TODO Auto-generated method stub
		return jogador1.daVez;
	}

}
