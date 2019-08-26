package view;

import java.util.Random;


import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.textcomponents.DialogBox;

/**
 *
 * @author Diogo Henrique
 * @author Bianca
 * @author Alex
 */
public class DiabloMenuScreenAjuda {

	ConsoleSystemInterface mainInterface;
	DialogBox dialog_box;


	public DiabloMenuScreenAjuda(ConsoleSystemInterface csi) {
		this.mainInterface = csi;
		this.dialog_box = new DialogBox(csi, 5, "Teste 01");
		printScreen();
		mostrarAjuda();
		idleLoop();
	}

	/**
	 *  Método vai verificar a entrada do jogador e chamar o método que verifica o que foi introduzido
	 * @param nulo
	 * @return vazio
	 */
	private void idleLoop() {
		CharKey actionKey = new CharKey();
		do {
			printScreen();
			actionKey = mainInterface.inkey();
			takeAction(actionKey);
		} while (true);
	}
	/**
	 * @return Vazio
	 * @param thisKey
	 */
	private void takeAction(CharKey thisKey) {
		// isso irá alimentar o controle para o método apropriado baseado na entrada do jogador
		if (thisKey.isUpArrow() || (thisKey.code == CharKey.k)) {
		} else if (thisKey.isUpRightArrow() || (thisKey.code == CharKey.u)) {
		} else if (thisKey.isRightArrow() || (thisKey.code == CharKey.l)) {
		} else if (thisKey.isDownRightArrow() || (thisKey.code == CharKey.n)) {
		} else if (thisKey.isDownArrow() || (thisKey.code == CharKey.j)) {
		} else if (thisKey.isDownLeftArrow() || (thisKey.code == CharKey.b)) {
		} else if (thisKey.isLeftArrow() || (thisKey.code == CharKey.h)) {
		} else if (thisKey.isUpLeftArrow() || (thisKey.code == CharKey.y)) {
		} else if (thisKey.isSelfArrow() || (thisKey.code == CharKey.DOT)) {
		}

		switch (thisKey.code) {
		case CharKey.ESC:
		case CharKey.q:
		case CharKey.Q:
			exit();
			break;
		case CharKey.QUESTION:
			mostrarAjuda();
			break;
		}
	}

	private void exit() {
		mainInterface.refresh();
		System.exit(0);
	}

	private void mostrarAjuda() {
		String textoAjuda[] = {
				"Welcome to the Swing Tutorial for libjcsi!",
				"",
				"Commands:",
				"7 8 9  y k u  Move cursor in given direction",
				"4   6  h   l  The arrow keys can also be used",
				"1 2 3  b j n",
				"",
				"Q / q / ESC -- Exit the game",
				"",
				"Enter - Make selection",
				"ESC - Cancel selection",
				"? - Open this screen",
				"",
				"",
				"Press Any Key To Exit This Screen"
		};
		mainInterface.saveBuffer();
		mainInterface.cls();
		for (int i = 0; i < textoAjuda.length; i++) {
			mainInterface.print(0, i, textoAjuda[i]);
		}
		dialog_box.setText("Testando");
		dialog_box.draw();
		mainInterface.refresh();
		mainInterface.inkey();
		mainInterface.restore();
		mainInterface.refresh();
	}

	private void printScreen() {
		mainInterface.cls();
		mainInterface.refresh();
	}

}