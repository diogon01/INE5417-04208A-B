package dominioProblema;

public class Posicao {

	protected int ocupante;

	public Posicao() {
		this.ocupante = 0;
	}

	public boolean verificarOcupada() {
		return ocupante != 0;
	}

	public void setOcupacao(int simbolo) {
		this.ocupante = simbolo;
	}

	public void esvaziar() {
		ocupante = 0;
	}

	public void desativar() {
		this.ocupante = 5;
	}
	
	public int informarOcupante() {
		return this.ocupante;
	}

}