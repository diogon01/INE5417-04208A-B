package logica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;



import gui.Teclado;

public class GameLoop implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// Pressionou para cima W
		if (Teclado.teclaPressionada(KeyEvent.VK_W)) {
			GameLogic.moverJogador(0, -1);

		}
		// Pressionou para baixo S
		if (Teclado.teclaPressionada(KeyEvent.VK_S)) {
			GameLogic.moverJogador(0, 1);
		}
		// Pressionou para Esquerda A
		if (Teclado.teclaPressionada(KeyEvent.VK_A)) {
			GameLogic.moverJogador(-1, 0);
		}
		// Pressionou para Direita D
		if (Teclado.teclaPressionada(KeyEvent.VK_D)) {
			GameLogic.moverJogador(1, 0);
		}
	}

}
