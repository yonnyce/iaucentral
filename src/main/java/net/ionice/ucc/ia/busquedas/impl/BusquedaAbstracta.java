package net.ionice.ucc.ia.busquedas.impl;

import java.util.List;
import java.util.function.Function;

public abstract class BusquedaAbstracta<T> {

	BusquedaConfigurable<T> busquedaConfigurable;

	public BusquedaAbstracta() {
		this.busquedaConfigurable = new BusquedaConfigurable<>();

		init();
	}

	/**
	 * Se encarga de generar los nodos hijos apartir del nodo padre, en esta funcion
	 * se definen todas la reglas necesarias que seran aplicadas a cada nodo
	 * 
	 * @param tNodo
	 * @return listado con los nodos hijos
	 */
	public abstract List<T> obtenerNodosHijo(T tNodo);

	public void init() {

		Function<T, List<T>> funcObtenerHijos = nodo -> {
			return obtenerNodosHijo(nodo);
		};

		busquedaConfigurable.setFuncObtenerHijos(funcObtenerHijos);
	}

}
