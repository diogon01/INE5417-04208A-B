package dominioProblema;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Lance implements Jogada {

	private static final long serialVersionUID = 1L;
	protected int linha;
	protected int coluna;
	protected ObjetosCaverna objeto;

	public void assumir(ObjetosCaverna objeto, int valLinha, int valColuna) {
		linha = valLinha;
		coluna = valColuna;
		this.objeto = objeto;
	}

	public int informarLinha() {
		return linha;
	}

	public int informarColuna() {
		return coluna;
	}

	public ObjetosCaverna informarObjeto() {
		return this.objeto;
	}
}