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
	private Letra encoberta;
	private static PalavraRepository repo;
	
	public static void setLetraFactory(LetraFactory factory) {
		letraFactory = factory;		
	}
	
	public static LetraFactory getLetraFactory() {
		return letraFactory;
	}
	
	private Palavra(long id, String palavra, Tema tema) {
		if(letraFactory == null) {
			throw new RuntimeException("letraFactory não pode ser null");
		} 
		
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
		Palavra palavraReconstituida = repo.getPorId(id);
		return palavraReconstituida;
	}
	
	public Letra[] getLetras() {
		return (Letra[]) letras.toArray();
	}
	
	public Letra getLetra(int posicao) {
		return letras.get(posicao);
	}
	
	
	public void exibir(Object contexto) {
		for(int i = 0; i < this.letras.size();i++) {
			letras.get(i).exibir(contexto);
		}
	}
	
	public void exibir(Object contexto, boolean posicoes[]) {
		for(int i = 0; i < this.letras.size(); i++) {
			if(posicoes[i]==true) {
				letras.get(i).exibir(contexto);
			}
		}
	}
	
	
	
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
		
		String palavra = "";
		
		for(int i=0;i<this.getTamanho();i++) {
			palavra+=this.getLetra(i);
		}
		
		return palavra;
	}
	

}
