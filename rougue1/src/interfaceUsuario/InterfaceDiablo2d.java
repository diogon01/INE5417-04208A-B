package interfaceUsuario;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.Recurso;

public class InterfaceDiablo2d extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AtorJogador jogo;

	public JFrame frame;
	private final Action action_conectar = new SwingAction_Conectar();
	private final Action action_desconectar = new SwingAction_Desconectar();
	private final Action action_iniciar = new SwingAction_Iniciar();
	private GridLayout gridlayout;

	protected JLabel player1;
	protected JLabel player2;
	protected JLabel[][] matriz;

	/**
	 * Iniciando a interface do jogo
	 */
	public InterfaceDiablo2d() {
		System.out.println("[GUI][Gerenciador de exibi√ß√£o]: Tela do jogo criada!");
		this.incializar();

	}

	private void incializar() {
		jogo = new AtorJogador(this);

		frame = new JFrame();
		frame.setBounds(1, 1, 100, 100);
		frame.setTitle("Diablo2D Temporada 18");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gridlayout = new GridLayout(9, 11, 1, 1);
		frame.getContentPane().setLayout(gridlayout);

		matriz = new JLabel[9][11];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 11; j++) {
				matriz[i][j] = new JLabel();
				frame.getContentPane().add(new JPanel().add(matriz[i][j]));
			}
		}

		matriz[0][0].setText("Tempo");
		matriz[0][10].setText("Fenda");
		matriz[8][0].setText("Jogador1");
		matriz[8][10].setText("Jogador2");

		for (int i = 0; i < 9; i++) {
			for (int j = 1; j < 11; j++) {

				matriz[i][j].setIcon(new ImageIcon(getClass().getResource("floor_" + Recurso.geradorDeNumeroAleatorios(1, 8) + ".png")));
			}
		}

		matriz[6][3].setIcon(new ImageIcon(getClass().getResource("knight_m_idle_anim_f2.png")));

		matriz[6][7].setIcon(new ImageIcon(getClass().getResource("wizzard_m_idle_anim_f0.png")));

		frame.pack();

		this.renderizar_menu();

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

	/*
	 * @Override protected void paintComponent(Graphics graphics) {
	 * super.paintComponent(graphics);
	 * 
	 * graphics.setColor(Color.BLACK); graphics.fillRect(0, 0, Window.WIDTH,
	 * Window.HEIGHT); graphics.setColor(Color.WHITE); graphics.fillRect(30, 30, 30,
	 * 30); graphics.fillRect(30, 30, 50, 50); graphics.fillRect(50, 100, 40, 40);
	 * 
	 * Renderer.renderEntidade(GameLogic.getJogador(), graphics);
	 * 
	 * repaint(); }
	 */

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
			conectar();
		}
	}
	
	public void conectar() {
		int resultado = jogo.conectar();
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
    		case 0:  JOptionPane.showMessageDialog(this, "Conex„o efetuada com exito"); break;        	
    		case 1:  JOptionPane.showMessageDialog(this, "Tentativa de conex„o com conex„o previamente estabelecida"); break;
        	case 2:  JOptionPane.showMessageDialog(this, "Tentativa de conexao falhou"); break;
        	case 3:  JOptionPane.showMessageDialog(this, "Desonex„o efetuada com exito"); break;
        	case 4:  JOptionPane.showMessageDialog(this, "Tentativa de desconexao sem conexao previamente estabelecida"); break;
        	case 5:  JOptionPane.showMessageDialog(this, "Tentativa de desconexao falhou"); break;
        	case 6:  JOptionPane.showMessageDialog(this, "SolicitaÁ„o de inicio procedida com Íxito"); break;
        	case 7:  JOptionPane.showMessageDialog(this, "Tentativa de inicio sem conexao previamente estabelecida"); break;
        	case 8:  JOptionPane.showMessageDialog(this, "N„o È a sua vez"); break;
        	case 9:  JOptionPane.showMessageDialog(this, "Partida encerrada"); break;
        	case 10: JOptionPane.showMessageDialog(this, "Lance OK"); break;
        	case 11: JOptionPane.showMessageDialog(this, "PosiÁ„o ocupada"); break;
        	case 12: JOptionPane.showMessageDialog(this, "PosiÁ„o ilegal"); break;
        	case 13: JOptionPane.showMessageDialog(this, "Partida corrente n„o interrompida"); break;
		};
	}
	

}