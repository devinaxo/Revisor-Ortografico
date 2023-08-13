package recursos;
import java.io.*;
import javax.swing.JOptionPane;

import trie.ListaDeStrings;
import trie.Trie;
public class Revisor {
	private String s;
	private int ch;
	private String direccionArchivo;
	private String direccionDiccionario;
	private ListaDeStrings palabrasMal; //cambiar a lista enlazada //done
	private Trie trie;
	
	public Revisor() {
		s = "";
		ch = ' ';
		direccionArchivo = "";
		direccionDiccionario = "";
		palabrasMal = new ListaDeStrings();
		trie = null;
	}
	public void iniciarDiccionario() {
		InputStream diccionario;
		FileChooser fc = new FileChooser();
		JOptionPane.showMessageDialog(null, "Ingrese el .txt del diccionario a usar.");
		direccionDiccionario = fc.getDireccion();
		
		try { //registro el diccionario
			diccionario = new FileInputStream(direccionDiccionario);
			readWord(diccionario);
			//System.out.println(s);
			trie = new Trie(s.toUpperCase()); // inicializa la root;
			while (ch > -1) {
				readWord(diccionario);
				if (ch == -1)
					break;
				//System.out.println(s);
				trie.insertar(s);
			}
			diccionario.close();
			} catch(IOException io) {
				JOptionPane.showMessageDialog(null,"No se pudo abrir el diccionario.","Error", JOptionPane.ERROR_MESSAGE);
				direccionDiccionario = "";
		}
		
		//System.out.println("\nTrie: ");
		//trie.mostrarTrie(); //debug only
	}
	public void iniciarTexto() {
		InputStream fIn;
		FileChooser fc = new FileChooser();
		
		try { //abro el archivo de texto
			JOptionPane.showMessageDialog(null, "Ingrese el .txt a revisar ortográficamente.");
			direccionArchivo = fc.getDireccion();
			fIn = new FileInputStream(direccionArchivo);
			
			//System.out.println("Palabras mal escritas:");
			//www(trie, (FileInputStream) fIn);
			
			fIn.close();
		} catch(IOException io) {
			JOptionPane.showMessageDialog(null,"No se pudo abrir el archivo.","Error", JOptionPane.ERROR_MESSAGE);
			direccionArchivo = "";
		}
	}
	public void readWord(InputStream fIn) {
		ch = ' ';
		try {
			while (true){
				if (ch > -1 && !Character.isLetter((char) ch)) { // salteamos
					ch = fIn.read(); // aquellos que no son letras;
				} else {
					break;
				}
			}
			if (ch == -1){
				return;
			}
			s = "";
			while (ch > -1 && Character.isLetter((char) ch)) {
				s += Character.toUpperCase((char) ch);
				ch = fIn.read();
			}					
		} catch (IOException io) {
			System.out.println("Hay un problema con la entrada.");
		}
	}
	public void www(Trie diccionario, FileInputStream fIn) { //wrongly written words, está piola el acrónimo eh?
		while (true) {
			readWord(fIn);
			if (ch == -1)
				break;
			if (!diccionario.buscar(s) ) { //agrego la palabra si es que no está en el diccionario o si es vocal/consonante
				palabrasMal.insertar(s);
				//System.out.println(s);
			}
		}
	}
	public ListaDeStrings getPalabrasMal() {
		return palabrasMal;
	}
	public Trie getTrie() {
		return trie;
	}
	public String getDireccionArchivo() {
		return direccionArchivo;
	}
	public String getDireccionDiccionario() {
		return direccionDiccionario;
	}
}
