package trie;
import contenedores.*;

/**
 * .
 * Lista ordenada de Letras Clave (LetraKey)
 * permite la inserción de cada una
 * de las Letras Claves en orden alfabético
 *  
 */
public class ListaDeLetras extends Lista2DLinkedL {

	@Override
	public boolean iguales(Object elemento1, Object elemento2) {
		if (((LetraKey) elemento1).getLetra() == ((LetraKey) elemento2)
				.getLetra()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean esMenor(Object elemento1, Object elemento2) {
		if (((LetraKey) elemento1).getLetra() < ((LetraKey) elemento2)
				.getLetra()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean esMayor(Object elemento1, Object elemento2) {
		if (((LetraKey) elemento1).getLetra() > ((LetraKey) elemento2)
				.getLetra()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * método para mostrar todas las LetrasKey de la lista
	 */
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
