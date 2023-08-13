package testing;
import trie.*;
public class TestingCosas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		 * Testeando ListaDeLetras (de LetraKeys)
		 */
		/*ListaDeLetras lista=new ListaDeLetras();
		lista.insertar(new LetraKey('T'));
		lista.insertar(new LetraKey('H'));
		lista.insertar(new LetraKey('R'));
		lista.insertar(new LetraKey('O'));
		lista.insertar(new LetraKey('N'));
		lista.insertar(new LetraKey('E'));
		lista.mostrar();
		System.out.println(lista.buscar(new LetraKey('S')));
		System.out.println(lista.tamanio());*/
		
		
		
		/**
		 * Testeando cambio de referencia a una LetraKey
		 */
		/*TrieNode c=new TrieNode();
		Object o=lista.devolver(5);
		((LetraKey)o).setPtro(c);
		lista.mostrar();*/
		
		ListaDeStrings test = new ListaDeStrings();
		test.insertar("Bebe");
		test.insertar("Avion");
		test.insertar("elefante");
		test.insertar("dinosaurio");
		test.insertar("casa");
		test.mostrar();
		System.out.println(test.tamanio());

		

	}

}
