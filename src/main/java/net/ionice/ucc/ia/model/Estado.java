package net.ionice.ucc.ia.model;

import java.util.Arrays;

public class Estado {

	int[] numeros;

	public Estado(int[] numeros) {
		super();
		this.numeros = numeros;
	}

	public int[] getNumeros() {
		return numeros;
	}

	public void setNumeros(int[] numeros) {
		this.numeros = numeros;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(numeros);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		if (!Arrays.equals(numeros, other.numeros))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estado [numeros=" + Arrays.toString(numeros) + "]";
	}

}
