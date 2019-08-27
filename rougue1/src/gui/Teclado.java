package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

	private static boolean[] keys = new boolean[100];

	public Teclado() {
		keys = new boolean[100];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = false;

	}
	
	// Verifica se a tecla *esta pressionado
	public static boolean teclaPressionada(int key) {
		return keys[key];
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
