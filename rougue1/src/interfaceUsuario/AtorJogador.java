package interfaceUsuario;


import dominioProblema.Caverna;
import rede.AtorNetGames;

public class AtorJogador {
	
	protected Caverna tab;
	protected AtorNetGames atorRede;
	protected InterfaceJogo janela;
	protected String idusuario;
	
	public AtorJogador (InterfaceJogo jan){
		super();
		atorRede = new AtorNetGames(this);
		janela = jan;
		tab = new Caverna();
		tab.iniciar();
	}
	public String conectar() {
		String servidor = janela.solicitarServidor();
		String nome = janela.solicitarNome();
		return atorRede.conectar(servidor, nome);
	}
	public String desconectar() {
		return atorRede.desconectar();
	}
	public String iniciarPartida() {
		return atorRede.iniciarPartida();
	}

}
