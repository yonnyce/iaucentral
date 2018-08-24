package net.ionice.ucc.ia.busquedas;

import java.util.List;
import java.util.function.Function;

/**
 * Interfaz que define los metodos para realizar la busqueda
 * 
 * @author yonnyce
 *
 */
public interface IBusqueda<T> {

	/**
	 * Especifica el estado inicial de la busqueda
	 * 
	 * @param nodoInicial
	 *            elemento que representa el estado inicial
	 */
	void setNodoInicial(T nodoInicial);

	/**
	 * Especifica el estado objetivo de la busqueda
	 * 
	 * @param nodoObjetivo
	 *            elemento que representa el estado objetivo
	 */
	void setNodoObjetivo(T nodoObjetivo);

	/**
	 * Funcion que se encarga de aplicar las reglas y retornar los hijos,el
	 * resultado List<T> puede estar vacio pero no nulo
	 * 
	 * @param funcObtenerHijos
	 *            funcion que se encarga de generar los nodos hijos y retornarlos en
	 *            un List<T>
	 */
	void setFuncObtenerHijos(Function<T, List<T>> funcObtenerHijos);

	/**
	 * Luego de realizar la busqueda, en caso de que se halla encontrado la
	 * solucion, este elemento contendra el elemento T solucion
	 * 
	 * @return nodo que contiene el estado solucion
	 */
	T getNodoSolucion();

	/**
	 * Contendra los diferentes nodos generados en la busqueda
	 * 
	 * @return listado con los estados generados
	 */
	List<T> getNodosGenerados();

	/**
	 * Inicia la busqueda del estado objetivo
	 * 
	 * @return true si se encuentra el estado objetivo, false en caso contrario
	 */
	boolean iniciarBusqueda();

	/**
	 * Especifica si los nodos generados se insertaran al frente o atras de la lista
	 * de nevos nodos a ser evaluados
	 * 
	 * @param bProfundidad
	 *            true si se quiere realizar busqueda de profundidad, false si se
	 *            desea hacer busqueda en amplitud, por defecto esta en false
	 */
	void setProfundidad(boolean bProfundidad);

	/**
	 * Retorna todos los nodos que se procesaron en la busqueda
	 * 
	 * @return listado con todos los (diferentes) que se generaron en la busqueda
	 */
	List<T> getNodosCerrados();

	/**
	 * Retorna todos los nodos que estaba en cola, los cuales aun no habian sido
	 * procesados
	 * 
	 * @return listado con todos los nodos en cola a la espera de ser procesados
	 */
	List<T> getNodosAbiertos();

}
