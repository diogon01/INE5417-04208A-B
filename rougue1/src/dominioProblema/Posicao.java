package dominioProblema;

public class Posicao {

	protected Jogador ocupante;

	public boolean informarOcupada() {
		return (ocupante != null);
	}

	public Jogador informarOcupante() {
		return ocupante;
	}

	public void esvaziar() {
		ocupante = null;
	}
	
	public void alocarPeao(Jogador umJogador) {
		ocupante = umJogador;
	}


}