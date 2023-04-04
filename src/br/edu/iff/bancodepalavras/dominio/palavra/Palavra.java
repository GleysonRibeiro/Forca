package br.edu.iff.bancodepalavras.dominio.palavra;

import java.util.List;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;

public class Palavra extends ObjetoDominioImpl {
	
	private static LetraFactory letraFactory;
	private List<Letra> letras;
	private Tema tema;
	
	public static void setLetraFactory(LetraFactory factory) {
		letraFactory = factory;		
	}
	
	public static LetraFactory getLetraFactory() {
		return letraFactory;
	}
	
	private Palavra(long id, String palavra, Tema tema) {
		if(letraFactory == null) {
			throw new RuntimeException("letraFacotry não pode ser null");
		}//Tirar dúvida com o professor de como será setado letraFactory = será setado na classe roxa 
		
		this.ObjetoDominio(id);
		
		for(int i = 0; i<palavra.length();i++) {
			char letra = palavra.charAt(i);			
			letras.add(letraFactory.getLetra(letra));			
		}
		
		this.tema = tema;		
	}
	
	public static Palavra criar(long id, String palavra, Tema tema) {
		Palavra novaPalavra = new Palavra(id, palavra, tema);
		
		return novaPalavra;
	}
	
	public static Palavra reconstituir(long id, String palavra, Tema tema) {
		Palavra palavraReconstituida = palavraRepository.getPorId(id);
		return palavraReconstituida;
	}
	
	public Letra[] getLetras() {
		return (Letra[]) letras.toArray();
	}
	
	public Letra getLetra(int posicao) {
		return letras.get(posicao);
	}
	
	//O que é o objeto Object em + exibir(contexto : Object, posicoes : boolean[]) : void = imprimir todas as letras de palavra
	
	public int[] tentar(char codigo) {
		
		int[] posicoes = new int[this.getTamanho()];
		Letra letra = letraFactory.getLetra(codigo);
		
		for(int i=0; i<this.getTamanho(); i++) {
			if(letra == this.getLetra(i)) {
				posicoes[i] = 1;
			}
			else {
				posicoes[i] = 0;
			}
		}
		return posicoes;
		
	}
	
	public Tema getTema() {
		return this.tema;
	}
	
	public boolean comparar(String palavra) {
		for(int i=0; i<this.getTamanho();i++) {
			if(this.getLetra(i)!=letraFactory.getLetra(palavra.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public int getTamanho() {
		return letras.size();
	}
	
	public String toString() {
		
		String palavra = null;
		
		for(int i=0;i<this.getTamanho();i++) {
			palavra+=this.getLetra(i);
		}
		
		return palavra;
	}
	

}
