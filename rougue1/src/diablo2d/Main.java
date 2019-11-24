package diablo2d;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import dominioProblema.EscutarCaverna;
import interfaceUsuario.InterfaceDiablo2d;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static InterfaceDiablo2d interfaceJogo;

	public static void main(String[] args) {	
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Diablo2");
				// Set the content-pane of the JFrame to an instance of main JPanel
				frame.setContentPane(new InterfaceDiablo2d(frame));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null); // center the application window
				frame.setVisible(true); // show it
				EscutarCaverna.escutarTeclado(frame);
			}
		});
				
	}
}
