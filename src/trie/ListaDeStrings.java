package trie;

import contenedores.Lista2DLinkedL;
import contenedores.NodoDoble;

public class ListaDeStrings extends Lista2DLinkedL{ //contenedor lineal para guardar las palabras que estan mal

	@Override
	public boolean iguales(Object elemento1, Object elemento2) {
		String a = (String)elemento1; String b = (String)elemento2;
		if(a.compareTo(b) == 0) {
			return true;
		}else
			return false;
	}
	@Override
	public boolean esMenor(Object elemento1, Object elemento2) {
		String a = (String)elemento1; String b = (String)elemento2;
		if(a.compareTo(b) < 0) {
			return true;
		}else
			return false;
	}
	@Override
	public boolean esMayor(Object elemento1, Object elemento2) {
		String a = (String)elemento1; String b = (String)elemento2;
		if(a.compareTo(b) > 0) {
			return true;
		}else
			return false;
	}
	public void mostrar() {
		NodoDoble temp;

		if (!estaVacia()) {
			temp = this.frenteL; // el primero
			while (temp != null) {

				System.out.println(temp.getNodoInfo().toString());
				temp = temp.getNextNodo();
			}
		} else {
			System.out.println("Error muestra. Lista vacia");
		}

	}
}
