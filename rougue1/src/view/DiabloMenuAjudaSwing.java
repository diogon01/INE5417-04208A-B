package view;


import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 *
 * @author Diogo
 * @author Bianca
 * @author Alex
 */
public class DiabloMenuAjudaSwing {

    public static void main(String[] args) {
        try {
            ConsoleSystemInterface mainInterface = new WSwingConsoleInterface("Diablo2D Menu Ajuda");
            new DiabloMenuScreenAjuda(mainInterface);
        } catch (ExceptionInInitializerError eiie) {
            System.out.println("Fatal Error Initializing Swing Console Box");
            eiie.printStackTrace();
            System.exit(-1);
        }
    }
}