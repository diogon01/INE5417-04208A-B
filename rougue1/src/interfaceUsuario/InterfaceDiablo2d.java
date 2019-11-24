package interfaceUsuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
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
import javax.swing.SwingUtilities;

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
	// Barra que mostra o placar do jogo:
	private JLabel barraDePlacar;
	// Total de linhas da grade do Jogo
	private static final int linhas = 10;
	// Total de colunas da grade do Jogo
	private static final int colunas = 10;
	// Tamanho que vai ocupar cada celula da grid
	private static final int tamanhoDacelula = 32;
	// Largura da grade do Jogo
	private static final int larguraDaGrade = 2;

	// Nome do jogo para aparecer na Janaela do Jogo
	protected static final String nomeDoJogo = "Diablo 2D";
	// Tamanho da celula dentro do Layout Grid

	// Lagura da janela do jogo
	private static final int larguraDaCaverna = tamanhoDacelula * colunas;
	// Altura da janela do jogo
	private static final int alturaDaCaverna = tamanhoDacelula * linhas;

	// Meio da Grade
	private static final int meioDaGrade = larguraDaGrade / 2;
	// Espacamento interno da celula
	private static final int paddingCelula = tamanhoDacelula / 6;
	// Tamanho da imagem do Jogador
	private static final int tamanhoJogador = tamanhoDacelula - paddingCelula * 2;
	// pen's stroke width
	private static final int larguraLinhaPreta = 8; // pen's stroke width

	private JMenuBar jMenuBar1 = null;

	private JMenu menuJogo = null;

	private JMenuItem jMenuItem1 = null;

	private JMenuItem jMenuItem2 = null;

	private JMenuItem jMenuItem3 = null;

	public InterfaceDiablo2d(JFrame frameDiablo2D) {
		posicoes = new Posicao[linhas][colunas];
		System.out.println("[MouseEvent][Click do Mouse]: Escutando o clique do mouse!");
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();
				// Get na linha e na coluna da caverna
				int linhaSelecionada = mouseY / tamanhoDacelula;
				int colunaSelecionada = mouseX / tamanhoDacelula;
				String alvo = String.format("[Altura]:%s [Largura]:%s", mouseY, mouseX);
				String grid = String.format("[Linha]:%s [Coluna]:%s", linhaSelecionada, colunaSelecionada);
				System.out.println(grid);
				System.out.println(alvo);
				if (estadoJogo == EstadoJogo.PARTIDA_EM_ANDAMENTO) {
					if (linhaSelecionada >= 0 && linhaSelecionada < linhas && colunaSelecionada >= 0
							&& colunaSelecionada < colunas && caverna.informaPosicao(linhaSelecionada,
									colunaSelecionada).objeto == ObjetosCaverna.PISO) {
						// Atribui a posicao a caverna no jogo
						caverna.atribuirPosicao(linhaSelecionada, colunaSelecionada, jogadorLance);
						// Atualiza o jogo
						atualizarJogo(jogadorLance, linhaSelecionada, colunaSelecionada);
						// Mudar de jogador
						jogadorLance = (jogadorLance == ObjetosCaverna.JOGADOR1) ? ObjetosCaverna.JOGADOR2
								: ObjetosCaverna.JOGADOR1;
					}
				}
				repaint();
			}
		});

		System.out.println("[GUI][Gerenciador de exibibicao]: Tela do jogo criada!");
		// Configurando a barra de status (JLabel) para exibir a mensagem de status do
		barraDeEstatus = new JLabel("     ");
		barraDeEstatus.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
		barraDeEstatus.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		barraDeEstatus.setOpaque(true);
		barraDeEstatus.setBackground(Color.LIGHT_GRAY);
		
		barraDePlacar = new JLabel("Placar:     ");
		barraDePlacar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
		barraDePlacar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		barraDePlacar.setOpaque(true);
		barraDePlacar.setBackground(Color.LIGHT_GRAY);

		setLayout(new BorderLayout());
		// Adicinando a barra de estatus na borda
		add(barraDeEstatus, BorderLayout.PAGE_END);
		add(barraDePlacar, BorderLayout.PAGE_START);
		setPreferredSize(new Dimension(larguraDaCaverna, alturaDaCaverna + 30));

		jMenuBar1 = new JMenuBar();
		jMenuBar1.add(getMenu());
		frameDiablo2D.setJMenuBar(jMenuBar1);

		// account for statusBar in height

		// Iniciando o ator jogador
		jogo = new AtorJogador(this);
		// Iniciando a Caverna
		caverna = new Caverna();

		// Inicializa as variáveis do jogo
		this.incializar();

	}

	/**
	 * Envia jogada para a objeto Caverna tratar a jogada
	 * 
	 * @param jogadorLance
	 * @param linhaSelecionada
	 * @param colunaSelecionada
	 */
	protected void atualizarJogo(ObjetosCaverna jogadorLance, int linhaSelecionada, int colunaSelecionada) {
		if (caverna.jogadorVenceu(jogadorLance, linhaSelecionada, colunaSelecionada)) {
			// Verifica Qual jogador Venceu
			estadoJogo = (jogadorLance == ObjetosCaverna.JOGADOR1) ? EstadoJogo.JOGADOR1_VENCEU
					: EstadoJogo.JOGADOR2_VENCEU;
		} // Verifica se deu empate
		else if (caverna.jogoEmpatou()) {
			estadoJogo = EstadoJogo.EMPATE;
		}

	}

	private void incializar() {
		for (int linha = 0; linha < linhas; ++linha) {
			for (int coluna = 0; coluna < colunas; ++coluna) {
				// Inicia todas as células vazias
				caverna.posicoes[linha][coluna].objeto = ObjetosCaverna.VAZIO;
			}
		}

		// Muda o estado de jogo para em andamento
		estadoJogo = EstadoJogo.PARTIDA_EM_ANDAMENTO;
		// Jogador 1 joga primeiro inicialmente
		jogadorLance = ObjetosCaverna.JOGADOR1;

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

	private JMenu getMenu() {
		if (menuJogo == null) {
			menuJogo = new JMenu();
			menuJogo.setText("Jogo");
			menuJogo.setBounds(new Rectangle(1, 0, 57, 21));
			menuJogo.add(getJMenuItem1());
			menuJogo.add(getJMenuItem2());
			menuJogo.add(getJMenuItem3());

		}
		return menuJogo;
	}

	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("iniciar nova partida");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					iniciarPartida();
				}
			});
		}
		return jMenuItem1;
	}

	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("conectar");
			jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					conectar();
				}
			});
		}
		return jMenuItem2;
	}

	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("desconectar");
			jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					desconectar();
				}
			});
		}
		return jMenuItem3;
	}

	public void conectar() {
		int resultado = jogo.conectar();
		this.notificarResultado(resultado);
	}

	public void desconectar() {
		int resultado = jogo.desconectar();
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

	public static int informarMeioDagrade() {
		return meioDaGrade;
	}

	public static int informaLarguraDaCaverna() {
		return larguraDaCaverna;
	}

	public static int informarLarguraDaGrade() {
		return larguraDaGrade;
	}

	public static int informarAlturaDaCaverna() {
		return alturaDaCaverna;
	}

	/** Custom painting codes on this JPanel */
	@Override
	public void paintComponent(Graphics g) {
		// Invocado via repaint ()
		System.out.println("[Atualizando][Pintando a Tela]: Pintar as posicoes!");
		super.paintComponent(g);

		// preencher background de branco
		setBackground(Color.WHITE); // set its background color
		// Envia mensagem para pintar o tabuleiro
		caverna.pintar(g);

		// Print status-bar message
		if (estadoJogo == EstadoJogo.PARTIDA_EM_ANDAMENTO) {
			barraDeEstatus.setForeground(Color.BLACK);
			if (jogadorLance == ObjetosCaverna.JOGADOR1) {
				barraDeEstatus.setText("É o turno do jogador1: Paladino");
			} else {
				barraDeEstatus.setText("É o turno do jogador2: Mago");
			}
		} else if (estadoJogo == EstadoJogo.EMPATE) {
			barraDeEstatus.setForeground(Color.RED);
			barraDeEstatus.setText("É um empate! Clique para jogar novamente.");
		} else if (estadoJogo == EstadoJogo.JOGADOR1_VENCEU) {
			barraDeEstatus.setForeground(Color.RED);
			barraDeEstatus.setText("Jogador 1' ganhou! Clique para jogar novamente.");
		} else if (estadoJogo == EstadoJogo.JOGADOR2_VENCEU) {
			barraDeEstatus.setForeground(Color.RED);
			barraDeEstatus.setText("'Jogador 2' ganhou! Clique para jogar novamente.");
		}
	}
}