package br.edu.iff.bancodepalavras.dominio.letra;

import br.edu.iff.dominio.ObjetoDominioImpl;

public abstract class Letra extends ObjetoDominioImpl{
	
	private char codigo;
	
	protected Letra(char codigo) {
		this.codigo = codigo;
	}
	
	public char getCodigo() {
		return this.codigo;
	}
	
	public void exibir(Object contexto) {
		
	}
	
	public boolean equals(Object o) {
		
		if(!(o instanceof Letra)) return false;
		Letra outra = (Letra) o;
		return this.codigo == outra.codigo && this.getClass().equals(outra.getClass());	
				
	}
	
	public int hashcode() {
		
		return this.codigo+this.getClass().hashCode();
	}
	
	public final String toString() {
		return "Letra [codigo="+this.codigo;
	}
	
	
	
	

}
