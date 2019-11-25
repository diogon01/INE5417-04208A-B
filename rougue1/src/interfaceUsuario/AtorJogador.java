package interfaceUsuario;

import dominioProblema.Caverna;
import rede.AtorNetGames;

public class AtorJogador {

	protected Caverna tab;
	protected AtorNetGames rede;
	protected InterfaceDiablo2d janela;
	protected String idUsuario;

	public AtorJogador(InterfaceDiablo2d jan) {
		super();
		rede = new AtorNetGames(this);
		janela = jan;
		tab = new Caverna(InterfaceDiablo2d.informarLinhas(), InterfaceDiablo2d.informaColunas());
		tab.iniciar(InterfaceDiablo2d.informarLinhas(), InterfaceDiablo2d.informaColunas());
	}

	public int conectar() {
		boolean conectado = tab.informarConectado();
		if (!conectado) {
			String servidor = janela.solicitarServidor();
			idUsuario = janela.obterIdServidor();
			boolean exito = rede.conectar(servidor, idUsuario);
			if (exito) {
				tab.estabelecerConectado();
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

	public int desconectar() {
		boolean conectado = tab.informarConectado();
		if (conectado) {
			boolean exito = rede.desconectar();
			if (exito) {
				tab.estabelecerDesconectado();
				return 3;
			} else {
				return 5;
			}
		} else {
			return 4;
		}
	}

	public void tratarIniciarPartida(Integer posicao) {
		System.out.println("[AtorJogador][Iniciar Partida]: Inicia a partida na interface gráfica]");
		janela.iniciarMapa();
		System.out.println("[Chamando o REpaint][sads]: dasdadsa]");
		janela.pintaMapa();
	}

}
