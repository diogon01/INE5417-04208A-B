package interfaceUsuario;

import java.awt.Color;

import logica.GameLogic;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import diablo2d.AtorJogador;
import interfaceUsuario.Renderer;
import util.Recurso;

public class InterfaceJogo extends JPanel {
	
	
	private AtorJogador  atorJogador;
	
	private JFrame frame;
	private final Action action_conectar = new SwingAction_Conectar();
	private final Action action_desconectar = new SwingAction_Desconectar();
	private final Action action_iniciar = new SwingAction_Iniciar();
	private GridLayout gridlayout;
	
	protected JLabel player1;
	protected JLabel player2;
	
	
	public InterfaceJogo() {
		super();
		this.setFocusable(true);
		
		System.out.println("[GUI][Gerenciador de exibição]: Tela do jogo criada!");
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
	
