package dominioProblema;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import logica.entidade.EntidadeBase;
import logica.nivel.Level;
import util.Recurso;

public class EscutarCaverna {

	private static Timer tempo;

	private static EntidadeBase jogador;

	private static Level levelAtual;

	/**
	 * Chamado pelo Man quando o jogo *e iniciado
	 */
	public static void iniciarJogo() {

		jogador = new EntidadeBase("jogador", 64, 64);

		levelAtual = new Level(new String[] { "##########", "#...#....#", "#...#....#", "##.##....#", "#...#....#",
				"#........#", "#...#....#", "##########" });

		tempo = new Timer(200, new GameLoop());
		tempo.start();

	}

	/**
	 * Altera a posicao do jogadores
	 * 
	 * @param dirX Move o jogador no eixo X
	 * @param dirY Move o jogador no eixo Y
	 */
	public static void moverJogador(int dirX, int dirY) {
		jogador.setPosition(jogador.getPosX() + dirX, jogador.getPosY() + dirY);
	}

	public static EntidadeBase getJogador() {
		return jogador;
	}
	
	public static void escutarMouse(JFrame caverna) {
		
	}

	/**
	 * Recbe a interface usuario e esculta qualquer movimento do teclado
	 * @param caverna
	 */
	public static void escutarTeclado(JFrame caverna) {
		System.out.println("[Teclado: Escutando o teclado!");
		caverna.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				String alvo = String.format("[KeyListener][Tecla Pressionada]: Tecla presionada:%s", e.getKeyCode());
				System.out.println(alvo);
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					JOptionPane.showMessageDialog(null, "seta para cima pressionada");
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					JOptionPane.showMessageDialog(null, "seta para direita pressionada");
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					JOptionPane.showMessageDialog(null, "seta para baixo pressionada");
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					JOptionPane.showMessageDialog(null, "seta para esquerda pressionada");
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		;

	}

}
