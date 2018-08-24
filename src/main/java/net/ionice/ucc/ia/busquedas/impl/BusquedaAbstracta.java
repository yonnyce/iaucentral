package net.ionice.ucc.ia.busquedas.impl;

import java.util.List;
import java.util.function.Function;

public abstract class BusquedaAbstracta<T> {

	BusquedaConfigurable<T> busquedaConfigurable;
	int nivelMaximo;

	public BusquedaAbstracta() {
		this.busquedaConfigurable = new BusquedaConfigurable<>();
		this.nivelMaximo = 8;

		init();
	}

	public abstract List<T> obtenerNodosHijo(T tNodo);

	public void init() {

		Function<T, List<T>> funcObtenerHijos = nodo -> {
			return obtenerNodosHijo(nodo);
		};

		busquedaConfigurable.setFuncObtenerHijos(funcObtenerHijos);
	}

}
