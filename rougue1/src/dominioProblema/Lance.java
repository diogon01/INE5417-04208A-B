package dominioProblema;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Lance implements Jogada {


	private static final long serialVersionUID = 1L;
	protected int linha;
	protected int coluna;

	public void assumir(int valLinha, int valColuna) {
		linha = valLinha;
		coluna = valColuna;
	}

	public int informarLinha() {
		return linha;
	}

	public int informarColuna() {
		return coluna;
	}

}