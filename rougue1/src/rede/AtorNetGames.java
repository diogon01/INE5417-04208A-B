package rede;

import javax.swing.JOptionPane;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import dominioProblema.Caverna;
import interfaceUsuario.AtorJogador;

public class AtorNetGames implements OuvidorProxy {

	private static final long serialVersionUID = 1L;
	protected Proxy proxy;
	protected Caverna caverna;
	protected AtorJogador interfaceGrafica;

	public AtorNetGames(AtorJogador interfaceGraf) {
		super();
		this.interfaceGrafica = interfaceGraf;
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}

	public void setCaverna(Caverna caverna) {
		this.caverna = caverna;
	}

	public boolean conectar(String servidor, String nome) {

		try {
			proxy.conectar(servidor, nome);
			return true;
		} catch (JahConectadoException e) {
			e.printStackTrace();
			// return "Voce ja esta conectado";
			return false;
		} catch (NaoPossivelConectarException e) {
			e.printStackTrace();
			// return "Nao foi possivel conectar";
			return false;
		} catch (ArquivoMultiplayerException e) {
			e.printStackTrace();
			// return "Voce esqueceu o arquivo de propriedades";
			return false;
		}

	}

	public boolean desconectar() {
		try {
			proxy.desconectar();
			return true;
		} catch (NaoConectadoException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String iniciarPartida() {
		try {
			proxy.iniciarPartida(new Integer(2));
		} catch (NaoConectadoException e) {
			e.printStackTrace();
			return "Falha ao iniciar o NET-Games";
		}
		return "Sucesso: solicitacao de inicio enviada a Netgames Server";
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		JOptionPane.showMessageDialog(null, "Recebida mensagem de nova partida");

	}

	@Override
	public void finalizarPartidaComErro(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receberJogada(Jogada jogada) {

	}

	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub

	}
}
