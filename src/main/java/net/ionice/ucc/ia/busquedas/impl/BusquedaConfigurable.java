package net.ionice.ucc.ia.busquedas.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import net.ionice.ucc.ia.busquedas.IBusqueda;

public class BusquedaConfigurable<T> implements IBusqueda<T> {

	boolean profundidad = false;

	List<T> nodosGenerados;
	List<T> nodosCerrados;
	List<T> nodosSucesores;
	LinkedList<T> nodosAbiertos;
	Set<T> hashGenerados;

	Function<T, Boolean> funcSolucion;

	Function<T, List<T>> funcObtenerHijos;

	T nodoInicial;
	T nodoObjetivo;
	T nodoSolucion;

	public BusquedaConfigurable() {

		nodosAbiertos = new LinkedList<>();
		nodosCerrados = new ArrayList<>();
		nodosGenerados = new ArrayList<>();
		nodosSucesores = new ArrayList<>();
		hashGenerados = new HashSet<>();

	}

	private T buscarSolucion() {

		if (funcSolucion.apply(nodoInicial)) {
			return nodoInicial;
		}

		nodosAbiertos.add(nodoInicial);

		while (!nodosAbiertos.isEmpty()) {

			T tNodo = nodosAbiertos.getFirst();
			nodosAbiertos.removeFirst();

			nodosCerrados.add(tNodo);

			nodosSucesores.addAll(funcObtenerHijos.apply(tNodo));

			for (T t : nodosSucesores) {
				if (funcSolucion.apply(t)) {
					return t;
				}
			}

			if (profundidad) {
				insertarAdelante(nodosAbiertos, nodosSucesores);
			} else {
				nodosAbiertos.addAll(nodosSucesores);
			}

			for (T t : nodosSucesores) {
				if (this.hashGenerados.add(t)) {
					nodosGenerados.add(t);
				}
			}

			nodosSucesores.clear();
		}

		return null;
	}

	public void insertarAdelante(LinkedList<T> objetivo, List<T> elementos) {

		for (int i = elementos.size() - 1; i >= 0; i--) {
			objetivo.addFirst(elementos.get(i));
		}
	}

	@Override
	public void setNodoInicial(T nodoInicial) {
		this.nodoInicial = nodoInicial;
	}

	@Override
	public void setNodoObjetivo(T nodoObjetivo) {
		this.nodoObjetivo = nodoObjetivo;

	}

	@Override
	public T getNodoSolucion() {
		return nodoSolucion;
	}

	@Override
	public List<T> getNodosGenerados() {
		return nodosGenerados;
	}

	@Override
	public boolean iniciarBusqueda() {

		this.nodosAbiertos.clear();
		this.nodosCerrados.clear();
		this.nodosSucesores.clear();
		nodosGenerados.clear();

		this.nodoSolucion = buscarSolucion();

		return this.nodoSolucion != null;
	}

	@Override
	public void setProfundidad(boolean bProfundidad) {
		this.profundidad = bProfundidad;

	}

	@Override
	public void setFuncObtenerHijos(Function<T, List<T>> funcObtenerHijos) {
		this.funcObtenerHijos = funcObtenerHijos;
	}

	@Override
	public List<T> getNodosCerrados() {
		return this.nodosCerrados;
	}

	@Override
	public List<T> getNodosAbiertos() {
		return this.nodosAbiertos;
	}

	@Override
	public void setSolucionFunc(Function<T, Boolean> funcSolucion) {
		this.funcSolucion = funcSolucion;
	}

}
