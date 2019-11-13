package dominioProblema;

public class Jogador {

	protected String nome;
	private int cor;
	protected boolean vencedor;
	protected boolean simbolo;
	private int linha;
	private int coluna;
	protected boolean daVez;

	public Jogador(String nome, int cor, int linha, int coluna, boolean daVez) {
		super();
		this.nome = nome;
		this.cor = cor;
		this.linha = linha;
		this.coluna = coluna;
		this.daVez = daVez;
	}

	public boolean informarDaVez() {
		return daVez;
	}

	public String informarNome() {
		return nome;
	}

	public boolean informarVencedor() {
		return vencedor;
	}

	public void setVencedor() {
		this.vencedor = true;
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

	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

}