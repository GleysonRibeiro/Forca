package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.dominio.ObjetoDominioImpl;

public class Palavra extends ObjetoDominioImpl {
	
	private static letraFactory;
	
	public static void setLetraFactory(LetraFactory factory) {
		this.letraFactory = factory;		
	}
	

}
