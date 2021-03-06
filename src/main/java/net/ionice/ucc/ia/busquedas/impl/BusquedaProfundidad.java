package net.ionice.ucc.ia.busquedas.impl;

import java.util.ArrayList;
import java.util.List;

import net.ionice.ucc.ia.busquedas.impl.BusquedaAbstracta;
import net.ionice.ucc.ia.model.Estado;
import net.ionice.ucc.ia.model.Nodo;

public class BusquedaProfundidad extends BusquedaAbstracta<Nodo> {

	int nivelMaximo;

	public BusquedaProfundidad(int nivelMaximo) {
		this.nivelMaximo = nivelMaximo;

	}

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

	@Override
	public boolean esSolucion(Nodo tNodo) {

		if (tNodo.getEstado().getNumeros().length <= 1) {
			return true;
		}

		for (int i = 1; i < tNodo.getEstado().getNumeros().length; i++) {
			if (tNodo.getEstado().getNumeros()[i] < tNodo.getEstado().getNumeros()[i - 1]) {
				return false;
			}
		}

		return true;
	}

	public Nodo realizarBusqueda(Nodo inicial) {

		this.busquedaConfigurable.setNodoInicial(inicial);
		this.busquedaConfigurable.setProfundidad(true);

		if (this.busquedaConfigurable.iniciarBusqueda()) {
			System.out.println("generados amplitud " + this.busquedaConfigurable.getNodosGenerados().size());
			return this.busquedaConfigurable.getNodoSolucion();
		}

		return null;
	}

}
