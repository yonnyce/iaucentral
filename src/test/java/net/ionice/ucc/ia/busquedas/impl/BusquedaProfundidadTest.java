package net.ionice.ucc.ia.busquedas.impl;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import net.ionice.ucc.ia.model.Estado;
import net.ionice.ucc.ia.model.Nodo;

public class BusquedaProfundidadTest {
	private BusquedaProfundidad busquedaProfundidad;

	@Before
	public void initTest() {
		busquedaProfundidad = new BusquedaProfundidad(8);
	}

	@Test
	public void testRealizarBusqueda() throws Exception {
		int[] numeros = { 2, 1, 5, 4, 3, 6, 7, 9, 8 };

		Nodo nodoInicial = new Nodo();

		nodoInicial.setEstado(new Estado(numeros));
		nodoInicial.setNivel(0);
		nodoInicial.setPadre(null);
		int[] resultadoObjetivo = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		Nodo objetivo = new Nodo();
		objetivo.setEstado(new Estado(resultadoObjetivo));

		Nodo nodoResultadoAmplitud = this.busquedaProfundidad.realizarBusqueda(nodoInicial, objetivo);

		System.out.println("solucion profundidad");
		while (nodoResultadoAmplitud != null) {
			System.out.println(nodoResultadoAmplitud.toString());
			nodoResultadoAmplitud = nodoResultadoAmplitud.getPadre();
		}
	}

	@Test
	public void testFlipEstado() throws Exception {
		int[] numeros = new int[10];

		for (int i = 0; i < numeros.length; i++) {
			numeros[i] = i + 1;
		}

		Nodo nodo = new Nodo();

		nodo.setEstado(new Estado(numeros));
		nodo.setNivel(0);
		nodo.setPadre(null);

		Nodo nodoResultado = busquedaProfundidad.flipEstado(nodo, 5);

		int[] resultadoEsperado = { 1, 2, 3, 4, 5, 10, 9, 8, 7, 6 };

		assertTrue(Arrays.equals(nodoResultado.getEstado().getNumeros(), resultadoEsperado));

	}

}
