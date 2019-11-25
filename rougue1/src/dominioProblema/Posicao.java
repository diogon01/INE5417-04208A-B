package dominioProblema;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import interfaceUsuario.InterfaceDiablo2d;
import util.Recurso;

public class Posicao {

	public ObjetosCaverna objeto;
	protected Recurso recurso = new Recurso();

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
	 * @param linhas
	 * @param alturaDaCaverna
	 * @param tamanhoDoJogador
	 * @param paddingDaCelula
	 * @param larguraDaLinhaPreta
	 * @param larguraDaGrade
	 * @param larguraDaCaverna
	 * @param meioDaGrade
	 * @param tamanhoDaCelula
	 */
	public void desenharPosicao(Graphics g, int tamanhoDaCelula, int meioDaGrade, int larguraDaCaverna,
			int larguraDaGrade, int larguraDaLinhaPreta, int paddingDaCelula, int tamanhoDoJogador, int alturaDaCaverna,
			int linhas) {

		// Use Graphics2D que permite definir o linha preta da grade
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(larguraDaLinhaPreta, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2d.setBackground(Color.BLUE);

		// Desenhe os elementos do jogo
		int x1 = this.coluna * tamanhoDaCelula + paddingDaCelula;
		int y1 = this.linha * tamanhoDaCelula + paddingDaCelula;

		Image img = null;
		try {
			// Verifica se o objeto e o Jogador 1
			if (objeto == ObjetosCaverna.JOGADOR1) {
				img = ImageIO.read(getClass().getResource("/interfaceUsuario/knight_m_idle_anim_f2.png"));
			} // Verifica se o objeto e o Jogador 2
			else if (objeto == ObjetosCaverna.JOGADOR2) {
				img = ImageIO.read(getClass().getResource("/interfaceUsuario/wizzard_m_idle_anim_f0.png"));
			} else if (objeto == ObjetosCaverna.PISO) {
				img = ImageIO.read(getClass().getResource("/interfaceUsuario/floor_1.png"));
			} else if (objeto == ObjetosCaverna.PAREDE) {
				img = ImageIO.read(getClass().getResource("/interfaceUsuario/wall_mid.png"));
			} else if (objeto == ObjetosCaverna.TESOURO) {
				img = ImageIO.read(getClass().getResource("/interfaceUsuario/chest_full_open_anim_f2.png"));
			}
			g2d.drawImage(img, x1, y1, tamanhoDaCelula, tamanhoDaCelula, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}