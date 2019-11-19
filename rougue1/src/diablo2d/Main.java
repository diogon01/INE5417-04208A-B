package diablo2d;


import javax.swing.JFrame;

import dominioProblema.GameLogic;
import interfaceUsuario.InterfaceDiablo2d;


public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static InterfaceDiablo2d interfaceJogo;

	public static void main(String[] args) {
		interfaceJogo = new InterfaceDiablo2d();
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
