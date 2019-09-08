package diablo2d;

import interfaceUsuario.InterfaceJogo;
import rede.AtorNetGames;

public class AtorJogador {
	
	protected AtorNetGames ngServer;
	protected InterfaceJogo interfaceJogo;
	
	public AtorJogador(InterfaceJogo interfaceJogo) {
		
		this.interfaceJogo = interfaceJogo;
		ngServer= new AtorNetGames();
		
	}
	
	public String conectar() {
		String servidor = interfaceJogo.solicitarServidor();
		String nome = interfaceJogo.solicitarNome();
		return ngServer.conectar(servidor, nome);
	}
	public String desconectar() {
		return ngServer.desconectar();
	}
	public String iniciarPartida() {
		return ngServer.iniciarPartida();
	}

}
