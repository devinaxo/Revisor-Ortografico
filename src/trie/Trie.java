package trie;
/**
 * 
 * Un Trie es un árbol que utiliza
 * parte de su Clave para direccionar la búsqueda
 * 
 *
 */
public class Trie {
	public TrieNonLeaf root;
	public final int notFound = -1;
	
	public Trie() {
	}
	
	public Trie(String word) {
		root = new TrieNonLeaf(word.charAt(0)); // inicializa la raíz
		crearHoja(word.charAt(0),word.substring(1),root); // para evitar pruebas posteriores
	} 
	
	public void mostrarTrie() {
		mostrarTrie(0,root,new String()); // se supone que la raíz no es nula;
	}
	
	private void mostrarTrie(int depth, TrieNode p, String prefijo) {
		if (p.esHoja()) {
			for (int j = 1; j <= depth; j++)
				System.out.print(" ");
			System.out.println(" >" + prefijo + "|" + ((TrieLeaf)p).getSufijo());
		}
		else {
				for (int i = ((TrieNonLeaf)p).getLetras().tamanio()-1; i >= 0; i--) {
					if (((LetraKey)(((TrieNonLeaf)p).getLetras().devolver(i))).getPtro() != null) {
						// añade al prefijo la letra que corresponde a la posición i
						prefijo = prefijo.substring(0,depth) +
								((LetraKey)(((TrieNonLeaf)p).getLetras().devolver(i))).getLetra();
						mostrarTrie(depth+1,((LetraKey)(((TrieNonLeaf)p).getLetras().devolver(i))).getPtro(),prefijo);
					}
					else { // si la hoja está vacia.
						for (int j = 1; j <= depth+1; j++)
							System.out.print(" ");
						System.out.println(" >>" + prefijo.substring(0,depth) +
								((LetraKey)(((TrieNonLeaf)p).getLetras().devolver(i))).getLetra());
					}
				}
				if (((TrieNonLeaf)p).esEndOfWord()) {
					for (int j = 1; j <= depth+1; j++)
						System.out.print(" ");
					System.out.println(" >>>" + prefijo.substring(0,depth));
				}
		}
	}
	
	
	/*public boolean buscar(String word) {				M�TODO VIEJO DE BUSCAR, OBSOLETO
		TrieNode p = root;
		int pos, i = 0;
		while (true)
			if (p.esHoja()) { // el nodo p es una hoja
				TrieLeaf lf = (TrieLeaf) p; // donde debe encontrarse
				if (word.substring(i).equals(lf.getSufijo())) // el sufijo de la
					return true; // palabra que coincide
				else return false;
			}
			else if ((pos = ((TrieNonLeaf)p).getLetras().buscar(new LetraKey(word.charAt(i)))) != notFound
					&& i+1 == word.length()) // el final de la palabra debe
				if (((LetraKey)((TrieNonLeaf)p).getLetras().devolver(pos)).getPtro()==null) // corresponder a
					return true; // una hoja vacía.
				else if(!((LetraKey)((TrieNonLeaf)p).getLetras().devolver(pos)).getPtro().esHoja() &&
						((TrieNonLeaf)(((LetraKey)((TrieNonLeaf)p).getLetras().devolver(pos)).getPtro())).esHoja())
					return true; // o el marcador de fin de palabra es verdadero.
				else return false;
			else if (pos != notFound && ((LetraKey)((TrieNonLeaf)p).getLetras().devolver(pos)).getPtro() != null) {
				p=((LetraKey)((TrieNonLeaf)p).getLetras().devolver(pos)).getPtro();// continúa el camino,
				i++; // si es posible,
			}
			else return false; // en caso contrario fracasa. 
	}*/
	

	public boolean buscar(String word) {
		TrieNode p = root;
		int pos, i = 0;
		while (true)
			if (p.esHoja()) { // el nodo p es una hoja
				TrieLeaf lf = (TrieLeaf) p; // donde debe encontrarse
				if (word.substring(i).equals(lf.getSufijo())) // el sufijo de la
					return true; // palabra que coincide
				else return false;
			}
			else if ((pos = ((TrieNonLeaf)p).getLetras().buscar(new LetraKey(word.charAt(i)))) != notFound
					&& i+1 == word.length()) // el final de la palabra debe
				if (((LetraKey)((TrieNonLeaf)p).getLetras().devolver(pos)).getPtro()==null) // corresponder a
					return true; // una hoja vac�a.
				else if(!((LetraKey)((TrieNonLeaf)p).getLetras().devolver(pos)).getPtro().esHoja() &&
						((TrieNonLeaf)(((LetraKey)((TrieNonLeaf)p).getLetras().devolver(pos)).getPtro())).esEndOfWord()) ///<----Ac� se corrigi� para verificar si es Fin de Palabra
					return true; // o el marcador de fin de palabra es verdadero.
				else return false;
			else if (pos != notFound && ((LetraKey)((TrieNonLeaf)p).getLetras().devolver(pos)).getPtro() != null) {
				p=((LetraKey)((TrieNonLeaf)p).getLetras().devolver(pos)).getPtro();// contin�a el camino,
				i++; // si es posible,
			}
			else return false; // en caso contrario fracasa.
	}

	
	private void crearHoja(char ch, String sufijo, TrieNonLeaf p) {
		int pos = p.getLetras().buscar(new LetraKey(ch));
		TrieLeaf lf = null;
		if (sufijo != null && sufijo.length() > 0) // no crea ninguna hoja
			lf = new TrieLeaf(sufijo); // si no hay un sufijo.
		if (pos == notFound) {
			p.getLetras().insertar(new LetraKey(ch));
		}
		pos = p.getLetras().buscar(new LetraKey(ch));//busca nuevamente la posición para
		((LetraKey)(p.getLetras().devolver(pos))).setPtro(lf);//insertar adecuadamente la hoja
		
	}
	
	public void insertar(String word) {
		TrieNonLeaf p = root;
		TrieLeaf lf;
		int offset, pos, i = 0;
		while (true) {
			if (i == word.length()) { // si se llega al final de la palbra, entonces
				if (p.esEndOfWord()) // setear endOfWord como true;
					//System.out.println("entrada duplicada1: " + word);
				p.setEndOfWord(true);
				return;
			} // si se indicó la posición en p
			
			pos = p.getLetras().buscar(new LetraKey(word.charAt(i)));
			
			if (pos == notFound) { // por la primer letra de la palabra
				crearHoja(word.charAt(i),word.substring(i+1),p);//no existe, crea
				return; // una hoja y almacena en ella
			} // el sufijo sin procesar de la palabra.
			
			else if (pos != notFound && // hoja vacía en posición pos
					((LetraKey)(p.getLetras().devolver(pos))).getPtro()==null){
					if (i+1 == word.length()) {
						//System.out.println("entrada duplicada1: " + word);
						return;
					}
					LetraKey letra = (LetraKey)p.getLetras().devolver(pos);
					letra.setPtro(new TrieNonLeaf(word.charAt(i+1)));
					((TrieNonLeaf)letra.getPtro()).setEndOfWord(true);
					
					// revisa si queda algún sufijo:
					String s;
					if (word.length() > i+2) {
						s=word.substring(i+2);
					}else{
						s=null;
					}
										
					crearHoja(word.charAt(i+1),s,(TrieNonLeaf)letra.getPtro());
					return;
			}
			else if (pos != notFound && // si la posición pos está
					((LetraKey)p.getLetras().devolver(pos)).getPtro().esHoja()){// ocupada por una hoja,
					LetraKey letra = (LetraKey)p.getLetras().devolver(pos);
					lf = (TrieLeaf) letra.getPtro();// guarda esta hoja para no perderla;
					if (lf.getSufijo().equals(word.substring(i+1))) {
						//System.out.println("entrada duplicada2: " + word);
						return;
					}
					offset = 0;
					// se crean tantas hojas como el número de coincidencias
					// en letras que que haya entre el prefijo de la palabra y
					// el String de la hoja.
					/* Ejemplo: Antes de que "REP" se inserte en el trie de la Figura 4 de la diapo
					 * el sufijo "EAR" está almacenada en una hoja que corresponde a la Letra Key
					 * 'R' de la raíz del trie. Si "REP" se está insertando ahora, no es suficiente
					 * reemplazar esta hoja con una no hoja, porque las segundas letras de estas dos
					 * palabras son la misma letra, "E". Es decir para la Letra Key 'R', se crean dos
					 * nodos, la hoja "EAR" y la palabra "REP"
					 */
					do {
						pos = p.getLetras().buscar(new LetraKey(word.charAt(i+offset)));
						// word = "ABC", leaf = "ABCDEF" => leaf = "DEF";
						if (word.length() == i+offset+1) {
							
							LetraKey aux = (LetraKey)p.getLetras().devolver(pos);
							aux.setPtro(new TrieNonLeaf(lf.getSufijo().charAt(offset)));
							p = (TrieNonLeaf)aux.getPtro();
							
							p.setEndOfWord(true);
							crearHoja(lf.getSufijo().charAt(offset),
									lf.getSufijo().substring(offset+1),p);
							return;
						}
						// word = "ABCDEF", leaf = "ABC" => leaf = "DEF";
						else if (lf.getSufijo().length() == offset){
							((LetraKey)p.getLetras().devolver(pos)).setPtro(new TrieNonLeaf(word.charAt(i+offset+1)));
							p = (TrieNonLeaf)((LetraKey)p.getLetras().devolver(pos)).getPtro();
							p.setEndOfWord(true);
							crearHoja(word.charAt(i+offset+1),
								word.substring(i+offset+2),p);
							return;
						}
						((LetraKey)p.getLetras().devolver(pos)).setPtro(new TrieNonLeaf(word.charAt(i+offset+1)));
						p = (TrieNonLeaf)((LetraKey)p.getLetras().devolver(pos)).getPtro();
						offset++;

					} while (word.charAt(i+offset) == lf.getSufijo().charAt(offset-1));
					offset--;
					// word = "ABCDEF", leaf = "ABCPQR" =>
					// leaf('D') = "EF", leaf('P') = "QR";
					// revisa si queda algún sufijo:
					// word = "ABCD", leaf = "ABCPQR" =>
					// leaf('D') = null, leaf('P') = "QR";
					String s = null;
					if (word.length() > i+offset+2)
						s = word.substring(i+offset+2);
					crearHoja(word.charAt(i+offset+1),s,p);
					// revisa si queda algún sufijo:
					// word = "ABCDEF", leaf = "ABCP" =>
					// leaf('D') = "EF", leaf('P') = null;
					if (lf.getSufijo().length() > offset+1)
						s = lf.getSufijo().substring(offset+1);
					else s = null;
					crearHoja(lf.getSufijo().charAt(offset),s,p);
					return;
			}
			else {
				p = (TrieNonLeaf)((LetraKey)(p.getLetras().devolver(pos))).getPtro();
				i++;
			}
		}
	}
}
