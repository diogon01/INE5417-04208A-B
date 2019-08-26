package gui;

import javax.swing.JFrame;

public class Janela {

	private static final int LARGURA = 900;
	private static final int ALTURA = 600;

	private static JFrame janela;
	private static GameScreen screen;

	public static void criarJanela() {

		janela = new JFrame("Diablo 2D");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setBounds(20, 20, LARGURA, ALTURA);
		janela.setResizable(false);

		screen = new GameScreen();

		janela.add(screen);

		System.out.println("[GUI][Gerenciador de exibição]: Tela do jogo iniciada!");

	}

	public static void tornarVisivel() {
		if (janela != null)
			janela.setVisible(true);
		System.out.println("[GUI][Gerenciador de exibição]: Janela visivel!");

	}

}