package interfaceUsuario;

import dominioProblema.Caverna;
import rede.AtorNetGames;

public class AtorJogador {

	protected Caverna tab;
	protected AtorNetGames rede;
	protected InterfaceDiablo2d janela;
	protected String idusuario;

	public AtorJogador(InterfaceDiablo2d jan) {
		super();
		rede = new AtorNetGames(this);
		janela = jan;
		tab = new Caverna();
		tab.iniciar();
	}

	public int conectar() {
		boolean conectado = tab.informarConectado();
		if (!conectado) {
			String servidor = janela.solicitarServidor();
			idusuario = janela.obterIdServidor();
			boolean exito = rede.conectar(servidor, idusuario);
			if (exito) {
				tab.estabelecerConectado(true);
				return 0;
			} else {
				return 2;
			}
		} else {
			return 1;
		}

	}

	public int iniciarPartida() {
		boolean conectado = false;
		boolean interromper = false;
		boolean emAndamento = tab.informarEmAndamento();
		if (emAndamento) {
			interromper = this.avaliarInterrupcao();
		} else {
			conectado = tab.informarConectado();
		}
		if (interromper || (!emAndamento && conectado)) {
			rede.iniciarPartida();
			return 6;
		}
		if (!conectado) {
			return 7;
		}
		return 13;
	}

	public boolean avaliarInterrupcao() {
		return true;
	}

	public String desconectar() {
		return rede.desconectar();
	}

}
