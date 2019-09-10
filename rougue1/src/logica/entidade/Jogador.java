package logica.entidade;

import logica.itens.Item;

public class Jogador {

	public static final int TAMANHO_INVENTORIO = 3;

	private Item[] iventario;

	private boolean inventarioAberto;

	public Jogador(String nome, int posX, int posY) {
		super();
		this.iventario = new Item[TAMANHO_INVENTORIO];
		this.inventarioAberto = false;
	}

	/**
	 * 
	 * @param item - Item a adicionar no inventario
	 * @return Retorna Verdadeiro se o item for adicionado, falso se nao tiver.
	 */
	public boolean pegarItem(Item item) {
		for (int i = 0; i < TAMANHO_INVENTORIO; i++) {
			if (this.iventario[i] == null) {
				this.iventario[i] = item;
				return true;
			}
		}
		return false;
	}

	public Item pegarItemIventorio(int i) {
		return iventario[i];
	}

	public void deixarIventarioAberto(boolean inventarioAberto) {
		this.inventarioAberto = inventarioAberto;
	}

	public boolean verificarIventario() {
		return inventarioAberto;
	}
}
