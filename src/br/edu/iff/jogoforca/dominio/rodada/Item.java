package br.edu.iff.jogoforca.dominio.rodada;

import java.util.Arrays;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.dominio.ObjetoDominioImpl;

public class Item extends ObjetoDominioImpl{

    private boolean posicoesDescobertas[];
    private String palavraArriscada = null;
    private Palavra palavra;
    

    private Item (int id, Palavra palavra){
    	ObjetoDominio(id);
    	boolean novoVetor[] = new boolean[palavra.getTamanho()];
    	Arrays.fill(novoVetor, false);
    	this.posicoesDescobertas = novoVetor;
        this.palavra = palavra;
    }

    private Item (int id, Palavra palavra, int[] posicoesDescobertas, String palavraArriscada){
    	ObjetoDominio(id);
    	this.palavra = palavra;
    	boolean novoVetor[] = new boolean[palavra.getTamanho()];
    	for(int i = 0; i < novoVetor.length;i++) {
    		if(posicoesDescobertas[i]==1) { 
    			novoVetor[i]=true;    			
    		}
    		else {
    			novoVetor[i]=false;
    		}
    		
    	}
    	this.posicoesDescobertas=novoVetor;
    	this.palavraArriscada=palavraArriscada;
    		
    }
    
    static Item criar(int id, Palavra palavra) {
    	Item item = new Item(id, palavra);
    	return item;
    }
    
    public static Item reconstituir(int id, Palavra palavra, int posicoesDescobertas[], String palavraArriscada) {
    	Item item = new Item(id, palavra, posicoesDescobertas, palavraArriscada);
    	
    	return item;
    }   
    

    public Palavra getPalavra (){
        return this.palavra;
    }
    
    public Letra[] getLetrasDescobertas() {
    	int tamanho = 0;
    	for(int i = 0;i<this.palavra.getTamanho();i++) {
    		if(this.posicoesDescobertas[i]==true) {
    			tamanho++;
    		}
    	}
    	Letra[] letrasDescobertas = new Letra[tamanho];
    	int j = 0;
    	for(int i = 0;i<this.palavra.getTamanho();i++) {
    		if(this.posicoesDescobertas[i]==true) {
    			letrasDescobertas[j]=this.palavra.getLetra(i);
    			j++;
    		}
    	}
    	return letrasDescobertas;
    }
    
    public Letra[] getLetrasEncobertas() {
    	Letra [] letrasEncobertas = new Letra[this.qtdeLetrasEncobertas()];
    	int j = 0;
    	for(int i = 0;i<this.palavra.getTamanho();i++) {
    		if(this.posicoesDescobertas[i]==false) {
    			letrasEncobertas[j]=this.palavra.getLetra(i);
    			j++;
    		}
    	}
    	
    	return letrasEncobertas;    	
    }
    
    public int qtdeLetrasEncobertas() {    	
    	int cont = 0;
    	for(int i = 0;i<this.palavra.getTamanho();i++) {
    		if(this.posicoesDescobertas[i]==false) {
    			cont++;
    		}
    	}
    	
    	return cont;    	
    }
    
    public int calcularPontosLetrasEncobertas(int valorPorLetraEncoberta) {
        int pontos = 0;
        pontos = valorPorLetraEncoberta * this.qtdeLetrasEncobertas();
        return pontos;
    }
    
    public boolean descobriu() {
    	if(this.acertou()==true||this.qtdeLetrasEncobertas()==0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public void exibir(Object contexto) {
    	this.palavra.exibir(contexto);
    }
    
    boolean tentar(char codigo) {
    	
    	int[] posicoes = this.palavra.tentar(codigo);
    	int cont = 0;
    	for(int i = 0; i < this.palavra.getTamanho();i++) {
    		if(posicoes[i]==1) {
    			this.posicoesDescobertas[i]=true;
    			
    		}
    		else {
    			this.posicoesDescobertas[i]=false;
    			cont++;
    		}
    	}
    	if(cont==this.palavra.getTamanho()) {
    		return false;
    	}
    	else {
    		return true;
    	}
    	
    }
    
    void arriscar(String palavra) {
    	this.palavraArriscada = palavra;
    }
    
    public String getPalavraArriscada() {
    	return this.palavraArriscada;
    }
    
    public boolean arriscou() {
    	if(this.getPalavraArriscada()==null) {
    		return false;
    	}
    	return true;
    }
    
    public boolean acertou() {
    	if(this.getPalavraArriscada()==null) {
    		return false;
    	}
    	return this.palavra.comparar(palavraArriscada);
    }
    
    
}
    
    
    