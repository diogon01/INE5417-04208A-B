package logica.entidade;

public class EntidadeBase {

	private String nome;

	private int posX;
	private int posY;

	/**
	 * Criando as entidades do jogo
	 * 
	 * @param nome - O nome da Entidade registrada
	 * @param posX - X cordenada do topo esquerdo da tela
	 * @param posY - Y cordenada do topo esquerdo da tela
	 */

	public EntidadeBase(String nome, int posX, int posY) {

		this.nome = nome;
		this.posX = posX;
		this.posY = posY;

	}

	public String getNome() {
		return nome;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosition(int dirX, int dirY) {
		// TODO Auto-generated method stub
		this.posX = dirX;
		this.posY = dirY;
		
	}

}
