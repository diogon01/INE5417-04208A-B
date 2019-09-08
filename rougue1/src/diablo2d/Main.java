package diablo2d;

import java.awt.EventQueue;

import interfaceUsuario.InterfaceJogo;

public class Main {

	private static InterfaceJogo interfaceJogo;

	public static void main(String[] args) {
		System.out.println("[Main]: Iniciando o jogo...");
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					interfaceJogo = new InterfaceJogo();
					interfaceJogo.frame.setVisible(true);

				} catch (Exception e) {
					System.out.println("[Erro]: " + e.toString());
					e.printStackTrace();
				}
			}
		});
//		Recurso.init();
//		Janela.criarJanela();
//		Janela.tornarVisivel();
//		GameLogic.iniciarJogo();
//		
//		System.out.println("[Main]: Jogo Iniciado");
//	

	}

}
