package net.ionice.ucc.ia.busquedas.impl;

import java.util.ArrayList;
import java.util.List;

import net.ionice.ucc.ia.busquedas.impl.BusquedaAbstracta;
import net.ionice.ucc.ia.model.Estado;
import net.ionice.ucc.ia.model.Nodo;

public class BusquedaAmplitud extends BusquedaAbstracta<Nodo> {

	int nivelMaximo;

	public BusquedaAmplitud(int nivelMaximo) {
		this.nivelMaximo = nivelMaximo;

	}

	/**
	 * Se encarga de girar el estado actual (arreglo) desde el indice indicado,
	 * ejemplo: {1,2,5,4,3} girando desde la posicion 2, el resultado seria el
	 * siguiente: {1,2,3,4,5}
	 * 
	 * @param nodo
	 *            nodo que contiene el estado a girar
	 * @param indice
	 *            posicion desde la cual se va a realizar el giro
	 * @return
	 */
	public Nodo flipEstado(Nodo nodo, int indice) {

		if (indice >= nodo.getEstado().getNumeros().length) {
			throw new IllegalStateException(
					"el indice no puede ser mayor que el numero total de elementos en el estado");
		}

		int[] numeros = nodo.getEstado().getNumeros().clone();
		int[] numerosTemporal = new int[numeros.length - indice];

		for (int i = indice; i < numeros.length; i++) {
			numerosTemporal[i - indice] = numeros[(numeros.length - 1) - (i - indice)];
		}

		for (int i = indice; i < numeros.length; i++) {
			numeros[i] = numerosTemporal[i - indice];
		}

		Nodo newNodo = new Nodo();

		newNodo.setPadre(nodo);
		newNodo.setNivel(nodo.getNivel() + 1);
		newNodo.setEstado(new Estado(numeros));

		return newNodo;
	}

	@Override
	public List<Nodo> obtenerNodosHijo(Nodo tNodo) {
		List<Nodo> nodosHijo = new ArrayList<>();

		if (tNodo.getNivel() + 1 >= nivelMaximo) {
			return nodosHijo;
		}

		for (int i = 0; i < tNodo.getEstado().getNumeros().length; i++) {
			nodosHijo.add(flipEstado(tNodo, i));
		}

		return nodosHijo;
	}

	/**
	 * Inicializa la busqueda con el nodo inicial definido y el nodo objetivo
	 * 
	 * @param inicial
	 * @param objetivo
	 * @return el nodo solucion del problema, o null en caso de que se recorra el
	 *         arbol y no se encuentre la solucion
	 */
	public Nodo realizarBusqueda(Nodo inicial, Nodo objetivo) {

		this.busquedaConfigurable.setNodoInicial(inicial);
		this.busquedaConfigurable.setNodoObjetivo(objetivo);

		if (this.busquedaConfigurable.iniciarBusqueda()) {
			System.out.println("generados amplitud " + this.busquedaConfigurable.getNodosGenerados().size());
			return this.busquedaConfigurable.getNodoSolucion();
		}

		return null;
	}

}
