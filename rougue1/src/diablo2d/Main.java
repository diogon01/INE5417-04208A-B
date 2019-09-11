package diablo2d;


import javax.swing.JFrame;

import dominioProblema.GameLogic;
import interfaceUsuario.InterfaceJogo;


public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static InterfaceJogo interfaceJogo;

	public static void main(String[] args) {
		interfaceJogo = new InterfaceJogo();
		interfaceJogo.frame.setVisible(true);
		GameLogic.escutarTeclado(interfaceJogo.frame);
	}
//		Recurso.init();
//		Janela.criarJanela();
//		Janela.tornarVisivel();
//		GameLogic.iniciarJogo();
//		
//		System.out.println("[Main]: Jogo Iniciado");
//	

}
