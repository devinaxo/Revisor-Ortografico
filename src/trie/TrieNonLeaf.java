package trie;

/**
 * 
 * Nodo del Trie que no es es Hoja
 * tiene un boolean que determina si es fin de palabra
 * y la Lista de Letras Clave que están en ese nodo.
 *
 */
public class TrieNonLeaf extends TrieNode{
	private boolean endOfWord = false;
	private ListaDeLetras letras=new ListaDeLetras();
	
	public TrieNonLeaf(){
		this.hoja=false;
	}
	public TrieNonLeaf(char ch){
		this.letras.insertar(new LetraKey(ch));
		this.hoja=false;
	}
	public boolean esEndOfWord() {
		return endOfWord;
	}
	public ListaDeLetras getLetras() {
		return letras;
	}
	public void setEndOfWord(boolean endOfWord) {
		this.endOfWord = endOfWord;
	}
	
}
