package dominioProblema;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import interfaceUsuario.InterfaceDiablo2d;

public class Posicao {

	public ObjetosCaverna objeto;

	int linha, coluna;

	protected int ocupante;

	/**
	 * Construtor para inicializar Posição com a linha e a coluna especificadas
	 * 
	 * @param linha
	 * @param coluna
	 */
	public Posicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public void esvaziar() {
		objeto = objeto.VAZIO;
		// Inicia com o piso do chao
	}

	public boolean verificarOcupada() {
		return ocupante != 0;
	}

	public void setOcupacao(int simbolo) {
		this.ocupante = simbolo;
	}

	public void desativar() {
		this.ocupante = 5;
	}

	public int informarOcupante() {
		return this.ocupante;
	}

	/**
	 * Desenha na interfaceDiablo2D, dado o contexto Gráfico
	 * 
	 * @param g
	 */
	public void pintar(Graphics g) {
		// Use Graphics2D que permite definir o linha preta da grade
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(
				new BasicStroke(InterfaceDiablo2d.larguraLinhaPreta(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		// Desenhe os elementos do jogo
		int x1 = this.coluna * InterfaceDiablo2d.informarTamanhoDacelula() + InterfaceDiablo2d.informarPaddingCelula();
		int y1 = this.linha * InterfaceDiablo2d.informarTamanhoDacelula() + InterfaceDiablo2d.informarPaddingCelula();
		// Verifica se o objeto e o Jogador1
		if (objeto == ObjetosCaverna.JOGADOR1) {
			// Pinta o jogador de vermelho
			g2d.setColor(Color.RED);
			int x2 = (this.coluna + 1) * InterfaceDiablo2d.informarTamanhoDacelula()
					- InterfaceDiablo2d.informarPaddingCelula();
			int y2 = (this.linha + 1) * InterfaceDiablo2d.informarTamanhoDacelula()
					- InterfaceDiablo2d.informarPaddingCelula();
			g2d.drawLine(x1, y1, x2, y2);
			g2d.drawLine(x2, y1, x1, y2);
		} else if (objeto == ObjetosCaverna.JOGADOR2) {
			g2d.setColor(Color.BLUE);
			g2d.drawOval(x1, y1, InterfaceDiablo2d.informarTamanhoJogador(),
					InterfaceDiablo2d.informarTamanhoJogador());
		} else if (objeto == ObjetosCaverna.PISO) {
			g2d.setColor(Color.GREEN);
			g2d.drawOval(x1, y1, InterfaceDiablo2d.informarTamanhoJogador(),
					InterfaceDiablo2d.informarTamanhoJogador());
		} else if (objeto == ObjetosCaverna.PAREDE) {
			g2d.setColor(Color.RED);
			int x2 = (this.coluna + 1) * InterfaceDiablo2d.informarTamanhoDacelula()
					- InterfaceDiablo2d.informarPaddingCelula();
			int y2 = (this.linha + 1) * InterfaceDiablo2d.informarTamanhoDacelula()
					- InterfaceDiablo2d.informarPaddingCelula();
			g2d.drawLine(x1, y1, x2, y2);
			g2d.drawLine(x2, y1, x1, y2);
		}

	}

}