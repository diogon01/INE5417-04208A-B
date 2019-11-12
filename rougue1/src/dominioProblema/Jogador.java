package dominioProblema;

public class Jogador {

	protected String nome;
	protected boolean simbolo;
	protected boolean daVez;
	protected boolean vencedor;

	public boolean informarDaVez() {
		return daVez;
	}

	public String informarNome() {
		return nome;
	}

	public boolean informarVencedor() {
		return vencedor;
	}

	public boolean informarSimbolo() {
		return simbolo;
	}

	public void iniciar() {
		daVez = false;
		vencedor = false;
	}

	public void assumirNome(String idJogador) {
		nome = idJogador;
	}

	public void habilitar() {
		daVez = true;
	}

	public void assumirSimbolo(boolean umSimbolo) {
		simbolo = umSimbolo;
	}

	public void desabilitar() {
		daVez = false;
	}

	public void assumirVencedor() {
		vencedor = true;
	}

}