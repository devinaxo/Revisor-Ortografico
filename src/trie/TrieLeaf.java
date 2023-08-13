package trie;

/**
 * 
 * Una Hoja del Trie, almacena la información del sufijo
 * Note que el sufijo es un String, ya que no es
 * necesario que esté en orden
 *
 */
public class TrieLeaf extends TrieNode {
	private String sufijo;
	
	public TrieLeaf(){
		this.hoja = true;
	}
	public TrieLeaf(String sufijo){
		this.sufijo = new String(sufijo);
		this.hoja = true;
	}
	public String getSufijo() {
		return sufijo;
	}
	public void setSufijo(String sufijo) {
		this.sufijo = sufijo;
	}
	

}
