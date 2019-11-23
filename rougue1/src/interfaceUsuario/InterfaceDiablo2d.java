package interfaceUsuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dominioProblema.Caverna;
import dominioProblema.EstadoJogo;
import dominioProblema.ObjetosCaverna;
import dominioProblema.Posicao;
import util.Recurso;

public class InterfaceDiablo2d extends JPanel {

	private static final long serialVersionUID = 1L;

	private AtorJogador jogo;
	// Objeto caverna
	private Caverna caverna;
	// Posicoes da matriz 2D de LINHAS por COLUNAS
	private Posicao[][] posicoes;

	public JFrame frame;

	// Estados em que o jodo pode assumir
	private EstadoJogo estadoJogo;
	// Objetos da caverna
	private ObjetosCaverna jogadorLance;
	// Barra de estatos de comunicacao do jogo
	private JLabel barraDeEstatus;

	// Total de linhas da grade do Jogo
	private static final int linhas = 3;
	// Total de colunas da grade do Jogo
	private static final int colunas = 3;
	// Tamanho que vai ocupar cada celula da grid
	private static final int tamanhoDacelula = 100;
	// Largura da grade do Jogo
	protected static final int larguraDaGrade = 8;

	// Nome do jogo para aparecer na Janaela do Jogo
	protected static final String nomeDoJogo = "Diablo 2D";
	// Tamanho da celula dentro do Layout Grid

	// Lagura da janela do jogo
	protected static final int larguraDaCaverna = informarTamanhoDacelula() * informaColunas();
	// Altura da janela do jogo
	protected static final int alturaDaCaverna = informarTamanhoDacelula() * informarLinhas();

	// Meio da Grade
	protected static final int GRID_WIDHT_HALF = larguraDaGrade / 2;
	// Espacamento interno da celula
	private static final int paddingCelula = informarTamanhoDacelula() / 6;
	// Tamanho da imagem do Jogador
	private static final int tamanhoJogador = informarTamanhoDacelula() - informarPaddingCelula() * 2;
	// pen's stroke width
	private static final int larguraLinhaPreta = 8; // pen's stroke width

	private final Action action_conectar = new SwingAction_Conectar();
	private final Action action_desconectar = new SwingAction_Desconectar();
	private final Action action_iniciar = new SwingAction_Iniciar();

	private class SwingAction_Desconectar extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SwingAction_Desconectar() {
			putValue(NAME, "desconectar");
			putValue(SHORT_DESCRIPTION, "desconectar de Netgames Server");
		}

		public void actionPerformed(ActionEvent e) {
			String mensagem = jogo.desconectar();
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}

	private class SwingAction_Iniciar extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SwingAction_Iniciar() {
			putValue(NAME, "iniciar partida");
			putValue(SHORT_DESCRIPTION, "iniciar partida do seu jogo");
		}

		public void actionPerformed(ActionEvent e) {
			iniciarPartida();
		}
	}

	private class SwingAction_Conectar extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SwingAction_Conectar() {
			putValue(NAME, "conectar");
			putValue(SHORT_DESCRIPTION, "Conectar no servidor");
		}

		public void actionPerformed(ActionEvent e) {
			conectar();
		}
	}

	public InterfaceDiablo2d() {

		System.out.println("[GUI][Gerenciador de exibibicao]: Tela do jogo criada!");
		jogo = new AtorJogador(this);

		frame = new JFrame();
		frame.setBounds(1, 1, 100, 100);
		frame.setTitle(nomeDoJogo);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());

		// Configurando a barra de status (JLabel) para exibir a mensagem de status do
		barraDeEstatus = new JLabel("     ");
		barraDeEstatus.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
		barraDeEstatus.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		barraDeEstatus.setOpaque(true);
		barraDeEstatus.setBackground(Color.LIGHT_GRAY);

		frame.getContentPane().add(barraDeEstatus, BorderLayout.PAGE_END);
		frame.getContentPane().setPreferredSize(new Dimension(larguraDaCaverna, alturaDaCaverna + 30));

		System.out.println("[MouseEvent][Click do Mouse]: Tela do jogo criada!");
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();
				// Get na linha e na coluna da caverna
				int linhaSelecionada = mouseY / informarTamanhoDacelula();
				int colunaSelecionada = mouseX / informarTamanhoDacelula();
				String alvo = String.format("[Altura]:%s [Largura]:%s", mouseY, mouseX);
				String grid = String.format("[Linha]:%s [Coluna]:%s", linhaSelecionada, colunaSelecionada);
				System.out.println(grid);
				System.out.println(alvo);
				if (estadoJogo == EstadoJogo.PARTIDA_EM_ANDAMENTO) {
					if (linhaSelecionada >= 0 && linhaSelecionada < informarLinhas() && colunaSelecionada >= 0
							&& colunaSelecionada < informaColunas() && caverna.informaPosicao(linhaSelecionada,
									colunaSelecionada).objeto == ObjetosCaverna.PISO) {
						// Atribui a posicao a caverna no jogo
						caverna.atribuirPosicao(linhaSelecionada, colunaSelecionada, jogadorLance);
						// Atualiza o jogo
						atualizarJogo(jogadorLance, linhaSelecionada, colunaSelecionada);
						// Switch player
						jogadorLance = (jogadorLance == ObjetosCaverna.JOGADOR1) ? ObjetosCaverna.JOGADOR2
								: ObjetosCaverna.JOGADOR1;
					}
				}
				repaint();
			}
		});

		this.incializar();
		this.renderizar_menu();

		frame.pack();
	}

	/**
	 * Envia jogada para a objeto Caverna tratar a jogada
	 * 
	 * @param jogadorLance
	 * @param linhaSelecionada
	 * @param colunaSelecionada
	 */
	protected void atualizarJogo(ObjetosCaverna jogadorLance, int linhaSelecionada, int colunaSelecionada) {
		// TODO Auto-generated method stub

	}

	private void incializar() {

		// Inicialize o conteúdo do tabuleiro de jogo e o estado atual

		/*
		 * matriz = new JLabel[9][11]; for (int i = 0; i < 9; i++) { for (int j = 0; j <
		 * 11; j++) { matriz[i][j] = new JLabel(); frame.getContentPane().add(new
		 * JPanel().add(matriz[i][j])); } }
		 * 
		 * matriz[0][0].setText("Tempo"); matriz[0][10].setText("Fenda");
		 * matriz[8][0].setText("Jogador1"); matriz[8][10].setText("Jogador2");
		 * 
		 * for (int i = 0; i < 9; i++) { for (int j = 1; j < 11; j++) {
		 * 
		 * matriz[i][j].setIcon(new ImageIcon( getClass().getResource("floor_" +
		 * Recurso.geradorDeNumeroAleatorios(1, 8) + ".png"))); } }
		 * 
		 * matriz[6][3].setIcon(new
		 * ImageIcon(getClass().getResource("knight_m_idle_anim_f2.png")));
		 * 
		 * matriz[6][7].setIcon(new
		 * ImageIcon(getClass().getResource("wizzard_m_idle_anim_f0.png")));
		 * 
		 */

	}

	private void renderizar_menu() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Jogo");
		menuBar.add(mnNewMenu);

		JMenuItem mntmConectar = new JMenuItem("conectar");
		mntmConectar.setAction(action_conectar);
		mnNewMenu.add(mntmConectar);

		JMenuItem mntmDesconectar = new JMenuItem("desconectar");
		mntmDesconectar.setAction(action_desconectar);
		mnNewMenu.add(mntmDesconectar);

		JMenuItem mntmIniciarPartida = new JMenuItem("iniciar partida");
		mntmIniciarPartida.setAction(action_iniciar);
		mnNewMenu.add(mntmIniciarPartida);
	}

	public void paint(Graphics g) {
		// Desenhando as linhas da grade do jogo
		g.setColor(Color.GRAY);
		for (int linha = 1; linha < InterfaceDiablo2d.informarLinhas(); ++linha) {
			g.fillRoundRect(0, InterfaceDiablo2d.informarTamanhoDacelula() * linha - InterfaceDiablo2d.GRID_WIDHT_HALF,
					InterfaceDiablo2d.larguraDaCaverna - 1, InterfaceDiablo2d.larguraDaGrade,
					InterfaceDiablo2d.larguraDaGrade, InterfaceDiablo2d.larguraDaGrade);
		}
		for (int coluna = 1; coluna < InterfaceDiablo2d.informaColunas(); ++coluna) {
			g.fillRoundRect(InterfaceDiablo2d.informarTamanhoDacelula() * coluna - InterfaceDiablo2d.GRID_WIDHT_HALF, 0,
					InterfaceDiablo2d.larguraDaGrade, InterfaceDiablo2d.alturaDaCaverna - 1,
					InterfaceDiablo2d.larguraDaGrade, InterfaceDiablo2d.larguraDaGrade);
		}

		// Desenha todas as células
		for (int linha = 0; linha < InterfaceDiablo2d.informarLinhas(); ++linha) {
			for (int coluna = 0; coluna < InterfaceDiablo2d.informaColunas(); ++coluna) {
				// ask the cell to paint itself
				posicoes[linha][coluna].pintar(g);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) { // invoke via repaint()
		super.paintComponent(g); // fill background
		setBackground(Color.WHITE); // set its background color

		this.paint(g); // ask the game board to paint itself

		// Print status-bar message
		if (this.estadoJogo == EstadoJogo.PARTIDA_EM_ANDAMENTO) {
			barraDeEstatus.setForeground(Color.BLACK);
			if (this.jogadorLance == ObjetosCaverna.JOGADOR1) {
				this.barraDeEstatus.setText("Vez do jogador 1");
			} else {
				this.barraDeEstatus.setText("Vez do jogador 2");
			}
		} else if (this.estadoJogo == EstadoJogo.EMPATE) {
			this.barraDeEstatus.setForeground(Color.RED);
			this.barraDeEstatus.setText("É um empate! Clique para jogar novamente.");
		} else if (this.estadoJogo == EstadoJogo.JOGADOR1_VENCEU) {
			this.barraDeEstatus.setForeground(Color.RED);
			barraDeEstatus.setText("'Jogador 1' ganhou! Clique para jogar novamente.");
		} else if (this.estadoJogo == EstadoJogo.JOGADOR2_VENCEU) {
			this.barraDeEstatus.setForeground(Color.RED);
			this.barraDeEstatus.setText("'Jogador 2' ganhou! Clique para jogar novamente.");
		}
	}

	public void conectar() {
		int resultado = jogo.conectar();
		this.notificarResultado(resultado);
	}

	public void iniciarPartida() {
		int resultado = jogo.iniciarPartida();
		this.notificarResultado(resultado);
	}

	public String solicitarServidor() {
		// TODO Auto-generated method stub
		return JOptionPane.showInputDialog(null, "Servidor:", "localhost");
	}

	public String obterIdServidor() {
		// TODO Auto-generated method stub
		return JOptionPane.showInputDialog("Nome:");
	}

	public void notificarResultado(int codigo) {
		switch (codigo) {
		case 0:
			JOptionPane.showMessageDialog(this, "Conexão efetuada com exito");
			break;
		case 1:
			JOptionPane.showMessageDialog(this, "Tentativa de conexão com conexão previamente estabelecida");
			break;
		case 2:
			JOptionPane.showMessageDialog(this, "Tentativa de conexao falhou");
			break;
		case 3:
			JOptionPane.showMessageDialog(this, "Desonexão efetuada com exito");
			break;
		case 4:
			JOptionPane.showMessageDialog(this, "Tentativa de desconexao sem conexao previamente estabelecida");
			break;
		case 5:
			JOptionPane.showMessageDialog(this, "Tentativa de desconexao falhou");
			break;
		case 6:
			JOptionPane.showMessageDialog(this, "Solicitação de inicio procedida com êxito");
			break;
		case 7:
			JOptionPane.showMessageDialog(this, "Tentativa de inicio sem conexao previamente estabelecida");
			break;
		case 8:
			JOptionPane.showMessageDialog(this, "Não é a sua vez");
			break;
		case 9:
			JOptionPane.showMessageDialog(this, "Partida encerrada");
			break;
		case 10:
			JOptionPane.showMessageDialog(this, "Lance OK");
			break;
		case 11:
			JOptionPane.showMessageDialog(this, "Posição ocupada");
			break;
		case 12:
			JOptionPane.showMessageDialog(this, "Posição ilegal");
			break;
		case 13:
			JOptionPane.showMessageDialog(this, "Partida corrente não interrompida");
			break;
		}
		;
	}

	public static int larguraLinhaPreta() {
		return larguraLinhaPreta;
	}

	public static int informarTamanhoDacelula() {
		return tamanhoDacelula;
	}

	public static int informarPaddingCelula() {
		return paddingCelula;
	}

	public static int informarTamanhoJogador() {
		return tamanhoJogador;
	}

	public static int informaColunas() {
		return colunas;
	}

	public static int informarLinhas() {
		return linhas;
	}

}