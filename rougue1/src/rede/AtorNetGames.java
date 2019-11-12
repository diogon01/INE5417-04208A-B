package rede;

import javax.swing.JOptionPane;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import diablo2d.AtorJogador;
import dominioProblema.Caverna;

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

	public String conectar(String servidor, String nome) {

		try {
			proxy.conectar(servidor, nome);
		} catch (JahConectadoException e) {
			e.printStackTrace();
			return "Voce ja esta conectado";
		} catch (NaoPossivelConectarException e) {
			e.printStackTrace();
			return "Nao foi possivel conectar";
		} catch (ArquivoMultiplayerException e) {
			e.printStackTrace();
			return "Voce esqueceu o arquivo de propriedades";
		}
		return "Sucesso: conectado a Netgames Server";
	}

	public String desconectar() {
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			e.printStackTrace();
			return "Voce nao esta conectado";
		}
		return "Sucesso: desconectado de Netgames Server";
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
