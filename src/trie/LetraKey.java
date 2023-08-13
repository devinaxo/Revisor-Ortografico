package trie;

/**
 * Clase que guarda la info
 * de una letra y
 * la referencia al siguiente TrieNode
 *
 */
public class LetraKey {
	private char letra;
	private TrieNode ptro;

	public LetraKey(char letra) {
		this.letra = letra;
		this.ptro = null;
	}

	public LetraKey(char letra, TrieNode ptro) {
		this.letra = letra;
		this.ptro = ptro;
	}

	public char getLetra() {
		return this.letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}

	public TrieNode getPtro() {
		return this.ptro;
	}

	public void setPtro(TrieNode ptro) {
		this.ptro = ptro;
	}

	/**
	 * para mostrar la info de una LetraKey, y la referencia del puntero
	 */
	public String toString() {
		String cadena = "";
		cadena += getLetra() + "," + getPtro();
		return cadena;
	}
}
