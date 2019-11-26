package dominioProblema;

import java.awt.Color;

import java.awt.Graphics;
import java.util.Random;
import java.util.Vector;
import util.Recurso;

public class Caverna {

	// TODO: Vericar melhor as posicoes
	public Posicao[][] posicoes;
	protected Jogador jogadorLocal;
	protected Jogador jogadorConvidado;
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
			for (int coluna = 0; coluna < colunas; ++coluna) {
				if (linha > 0 && linha < (linhas - 1) && (coluna > 0 && coluna < (colunas - 1))) {
					this.atribuirPosicao(linha, coluna, ObjetosCaverna.PISO);
				} else {
					this.atribuirPosicao(linha, coluna, ObjetosCaverna.PAREDE);
				}

			}
		}

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
		return jogadorLocal.informarNome().equals(idJogador) ? jogadorLocal : jogadorConvidado;
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
		return jogadorLocal.informarVencedor() ? jogadorLocal.informarNome() : jogadorConvidado.informarNome();
	}

	public int tratarLance(Jogador jogador, int linha, int coluna, ObjetosCaverna objeto) {

		Posicao pose = this.informaPosicao(jogadorLocal.getLinha() + linha, jogadorLocal.getColuna() + coluna);
		if (pose.objeto == ObjetosCaverna.JOGADOR2 || pose.objeto == ObjetosCaverna.JOGADOR1) {
			return 11;
		}
		if (pose.objeto == ObjetosCaverna.PAREDE) {
			return 12;
		}
		
		if (pose.objeto == ObjetosCaverna.TESOURO) {
			return 9;
		}

		int linhaMovimento = jogador.getLinha() + linha;
		int colunaMovimento = jogador.getColuna() + coluna;

		if (jogador.daVez) {
			this.atribuirPosicao(jogadorLocal.getLinha(), jogadorLocal.getColuna(), ObjetosCaverna.PISO);
			jogadorLocal.setLinha(linhaMovimento);
			jogadorLocal.setColuna(colunaMovimento);

			String alvo = String.format("[Altura]:%s [Largura]:%s", linhaMovimento, colunaMovimento);
			System.out.println(alvo);
			this.atribuirPosicao(jogadorLocal.getLinha(), jogadorLocal.getColuna(), jogador.objeto);

		}

		return 10;
	}

	public int tratarLanceConvidado(Jogador jogador, int linha, int coluna, ObjetosCaverna objeto) {

		Posicao pose = this.informaPosicao(jogadorLocal.getLinha() + linha, jogadorLocal.getColuna() + coluna);
		if (pose.objeto == ObjetosCaverna.JOGADOR2 || pose.objeto == ObjetosCaverna.JOGADOR1) {
			return 11;
		}
		if (pose.objeto == ObjetosCaverna.PAREDE) {
			return 12;
		}

		int linhaMovimento = jogadorConvidado.getLinha() + linha;
		int colunaMovimento = jogadorConvidado.getColuna() + coluna;

		this.atribuirPosicao(jogadorConvidado.getLinha(), jogadorConvidado.getColuna(), ObjetosCaverna.PISO);

		jogadorConvidado.setLinha(linhaMovimento);
		jogadorConvidado.setColuna(colunaMovimento);

		String alvo = String.format("[Mudando do convidado]:%s [Mudando]:%s", linhaMovimento, colunaMovimento);
		System.out.println(alvo);
		this.atribuirPosicao(jogadorConvidado.getLinha(), jogadorConvidado.getColuna(), jogadorConvidado.informObjeto());

		return 10;
	}

	public void finalizarPartida() {
		int placar1 = this.informarPlacar(jogadorLocal);
		int placar2 = this.informarPlacar(jogadorConvidado);
		partidaEmAndamento = false;
		jogadorLocal.desabilitar();
		jogadorConvidado.desabilitar();
		if (placar1 > placar2) {
			jogadorLocal.assumirVencedor();
		} else if (placar2 > placar1) {
			jogadorConvidado.assumirVencedor();
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
		boolean vez = jogadorLocal.informarDaVez();
		int resultado;
		if (vez) {
			resultado = this.tratarLance(jogadorLocal, linha, coluna, objeto);
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
		this.jogadorLocal = null;
		this.jogadorConvidado = null;
		this.estadoJogo = EstadoJogo.NOT_ANDAMENTO;
	}

	/**
	 * Cria o usuario com o ID Recebido do NetGames
	 * 
	 * @param idUsuario
	 */
	public void criarJogador(String idJogador) {
		if (jogadorLocal == null) {
			jogadorLocal = new Jogador();
			jogadorLocal.iniciar();
			jogadorLocal.assumirNome(idJogador);
		} else {
			jogadorConvidado = new Jogador();
			jogadorConvidado.iniciar();
			jogadorConvidado.assumirNome(idJogador);
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
			jogadorLocal.habilitar();
			jogadorLocal.atribuirObjeto(ObjetosCaverna.JOGADOR1);
			jogadorLocal.setLinha(14);
			jogadorLocal.setColuna(8);
			jogadorLocal.assumirSimbolo(true);
			
			jogadorConvidado.atribuirObjeto(ObjetosCaverna.JOGADOR2);
			jogadorConvidado.assumirSimbolo(false);
			jogadorConvidado.setLinha(14);
			jogadorConvidado.setColuna(12);
			

		} else {
			jogadorConvidado.habilitar();
			jogadorLocal.atribuirObjeto(ObjetosCaverna.JOGADOR2);
			jogadorLocal.setLinha(14);
			jogadorLocal.setColuna(12);
			jogadorLocal.assumirSimbolo(false);

			jogadorConvidado.atribuirObjeto(ObjetosCaverna.JOGADOR1);
			jogadorConvidado.assumirSimbolo(true);
			jogadorConvidado.setLinha(14);
			jogadorConvidado.setColuna(8);

		}
	}

	public void receberJogada(Lance jogada) {

		int linha = jogada.informarLinha();
		int coluna = jogada.informarColuna();
		boolean vez = jogadorLocal.informarDaVez();

		String getObjetos = null;

		if (jogada.informarObjeto() == jogadorLocal.informObjeto()) {
			getObjetos = String.format("[Caverna][Habilitar][Jogador: LOCAL]:%s", jogadorLocal.informObjeto());
			jogadorLocal.desabilitar();
			jogadorConvidado.habilitar();
			System.out.println(getObjetos);
		}
		if (jogada.informarObjeto() == jogadorConvidado.informObjeto()) {
			getObjetos = String.format("[Caverna][Habilitar][Jogador: CONVIDADO]:%s", jogadorLocal.informObjeto());
			jogadorConvidado.desabilitar();
			jogadorLocal.habilitar();
			System.out.println(getObjetos);
			
		}
		int tratar_Lance =this.tratarLanceConvidado(jogadorConvidado, linha, coluna, jogada.objeto);
		getObjetos = String.format("[Caverna][tratarLanceConvidado][Resultado]:%s", tratar_Lance);

	}

	public boolean informavezJogador1() {
		// TODO Auto-generated method stub
		return jogadorLocal.daVez;
	}

	public ObjetosCaverna informarObjetoJogador() {
		return jogadorLocal.objeto;
	}

}
