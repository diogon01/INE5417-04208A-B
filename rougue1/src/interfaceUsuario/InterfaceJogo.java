package interfaceUsuario;

import java.awt.Color;

import logica.GameLogic;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import diablo2d.AtorJogador;
import interfaceUsuario.Renderer;
import util.Recurso;

public class InterfaceJogo extends JPanel {

	private AtorJogador atorJogador;

	public JFrame frame;
	private final Action action_conectar = new SwingAction_Conectar();
	private final Action action_desconectar = new SwingAction_Desconectar();
	private final Action action_iniciar = new SwingAction_Iniciar();
	private GridLayout gridlayout;
	

	private ConsoleSystemInterface csi;
	private int a, b;

	protected JLabel player1;
	protected JLabel player2;
	protected JLabel[][] matriz;
	
	/**
	 * Iniciando a interface do jogo
	 */
	public InterfaceJogo() {
		System.out.println("[GUI][Gerenciador de exibição]: Tela do jogo criada!");
		this.incializar();

	}

	private void incializar() {
		atorJogador = new AtorJogador(this);

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Snake");
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
			putValue(SHORT_DESCRIPTION, "conectar a Netgames Server");
		}

		public void actionPerformed(ActionEvent e) {
			// Necess�rio definir endere�o do servidor e nome do jogador
			String mensagem = atorJogador.conectar();
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}

	private class SwingAction_Desconectar extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SwingAction_Desconectar() {
			putValue(NAME, "desconectar");
			putValue(SHORT_DESCRIPTION, "desconectar de Netgames Server");
		}

		public void actionPerformed(ActionEvent e) {
			String mensagem = atorJogador.desconectar();
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
			String mensagem = atorJogador.iniciarPartida();
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}

	public String solicitarServidor() {
		// TODO Auto-generated method stub
		return JOptionPane.showInputDialog(null, "Servidor:", "localhost");
	}

	public String solicitarNome() {
		// TODO Auto-generated method stub
		return JOptionPane.showInputDialog("Nome:");
	}

}