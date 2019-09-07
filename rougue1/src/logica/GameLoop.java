package logica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;



import gui.Teclado;

/**
 * Classe responsável pelo loop do jogo
 * @author diogo
 *
 */
public class GameLoop implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// Pressionou para cima W
		if (Teclado.teclaPressionada(KeyEvent.VK_W)) {
			System.out.println(" [GameEvento]: Apertou tecla para cima...");
			GameLogic.moverJogador(0, -1);

		}
		// Pressionou para baixo S
		if (Teclado.teclaPressionada(KeyEvent.VK_S)) {
			System.out.println(" [GameEvento]: Apertou tecla para baixo...");
			GameLogic.moverJogador(0, 1);
		}
		// Pressionou para Esquerda A
		if (Teclado.teclaPressionada(KeyEvent.VK_A)) {
			System.out.println(" [GameEvento]: Apertou tecla para esquerda...");
			GameLogic.moverJogador(-1, 0);
		}
		// Pressionou para Direita D
		if (Teclado.teclaPressionada(KeyEvent.VK_D)) {
			System.out.println(" [GameEvento]: Apertou tecla para direita...");
			GameLogic.moverJogador(1, 0);
		}
	}

}
