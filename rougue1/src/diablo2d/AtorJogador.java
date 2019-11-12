package diablo2d;


import dominioProblema.Caverna;
import interfaceUsuario.InterfaceJogo;
import rede.AtorNetGames;

public class AtorJogador {
	
	protected Caverna tab;
	protected AtorNetGames rede;
	protected InterfaceJogo janela;
	protected String idusuario;
	
	public AtorJogador (InterfaceJogo jan){
		super();
		rede = new AtorNetGames(this);
		janela = jan;
		tab = new Caverna();
		tab.iniciar();
	}
	public String conectar() {
		String servidor = janela.solicitarServidor();
		String nome = janela.solicitarNome();
		return rede.conectar(servidor, nome);
	}
	public String desconectar() {
		return rede.desconectar();
	}
	public String iniciarPartida() {
		return rede.iniciarPartida();
	}

}
