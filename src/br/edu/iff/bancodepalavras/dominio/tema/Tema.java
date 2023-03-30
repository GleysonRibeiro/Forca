package br.edu.iff.bancodepalavras.dominio.tema;

import br.edu.iff.dominio.ObjetoDominioImpl;

public class Tema extends ObjetoDominioImpl {
	
	private String nome;
	
	private Tema(long id, String nome) {		
		super(id);
		this.nome = nome;		
	}
	
	public static Tema criar(long id, String nome) {
		Tema novoTema = new Tema(id, nome);
		return novoTema;
	}
	
	public static Tema reconstituir(long id, String nome) {
		
		Tema temaRecuperado = TemaRepository.getPorId(id);
		
		return temaRecuperado;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
