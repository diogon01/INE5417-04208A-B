package dominioProblema;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import interfaceUsuario.InterfaceDiablo2d;
import logica.itens.Item;

public class Caverna {

	// TODO: Vericar melhor as posicoes
	private Posicao[][] posicoes;
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

	public Caverna() {
		zerarPosicoesAfetadas();
	}

	public int click(int linha, int coluna) {
		boolean vez = jogador1.informarDaVez();
		int resultado;
		if (vez) {
			resultado = this.tratarLance(jogador1, linha, coluna);
		} else {
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
		for (int linha = 0; linha < InterfaceDiablo2d.informarLinhas(); ++linha) {
			for (int coluna = 0; coluna < InterfaceDiablo2d.informaColunas(); ++coluna) {
				// limpa o conteúdo da célula
				posicoes[linha][coluna].esvaziar();
			}
		}
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
		posicoes = new Posicao[InterfaceDiablo2d.informarLinhas()][InterfaceDiablo2d.informaColunas()];
		for (int linha = 0; linha < InterfaceDiablo2d.informarLinhas(); ++linha) {
			for (int coluna = 0; coluna < InterfaceDiablo2d.informaColunas(); ++coluna) {
				// Acerta a posicao do Jogo
				posicoes[linha][coluna] = new Posicao(linha, coluna);
			}
		}
	}

	/*
	 * public Posicao getPosicaoJogador(ObjetosCaverna jogador) { for (int linha =
	 * 0; linha < InterfaceDiablo2d.informarLinhas(); ++linha) { for (int coluna =
	 * 0; coluna < InterfaceDiablo2d.informaColunas(); ++coluna) { // Acerta a
	 * posicao do Jogo if (informaPosicao(linha, coluna).objeto = jogador) { return
	 * posicoes[linha][coluna]; } } } return null; }
	 */

	public void setEmAndamento(boolean partidaEmAndamento) {
		this.partidaEmAndamento = partidaEmAndamento;
	}

	public Posicao getPosicao(int linha, int coluna) {
		return informaPosicao(linha, coluna);
	}

	public String getIdVencedor() {
		return jogador1.informarVencedor() ? jogador1.informarNome() : jogador2.informarNome();
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

	public void atribuirPosicao(int linha, int coluna, ObjetosCaverna objeto) {
		this.posicoes[linha][coluna].objeto = objeto;
	}

	public void pintar(Graphics g) {

		// Pintando a linha preta
		int linhas = InterfaceDiablo2d.informarLinhas();
		int colunas = InterfaceDiablo2d.informaColunas();
		int tamanhoDaCelula = InterfaceDiablo2d.informarTamanhoDacelula();
		int larguraDaCaverna = InterfaceDiablo2d.informaLarguraDaCaverna();
		int alturaDaCaverna = InterfaceDiablo2d.informarAlturaDaCaverna();
		int larguraDaGrade = InterfaceDiablo2d.informarLarguraDaGrade();
		int meioDaGrade = InterfaceDiablo2d.informarMeioDagrade();

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
				posicoes[linha][coluna].pintar(g);
			}
		}
	}

}
