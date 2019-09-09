package diablo2d;

import java.awt.EventQueue;

import javax.swing.JFrame;

import interfaceUsuario.InterfaceJogo;
import util.Recurso;

public class Main extends JFrame {

	private static InterfaceJogo interfaceJogo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					Recurso.init();
					interfaceJogo = new InterfaceJogo();
					interfaceJogo.frame.setVisible(true);
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println("[ERRO]"+ e.toString());
				}
				
			}
		});
	}
//		Recurso.init();
//		Janela.criarJanela();
//		Janela.tornarVisivel();
//		GameLogic.iniciarJogo();
//		
//		System.out.println("[Main]: Jogo Iniciado");
//	

}
