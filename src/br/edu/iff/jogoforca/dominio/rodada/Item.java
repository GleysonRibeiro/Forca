package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.dominio.ObjetoDominioImpl;

public class Item extends ObjetoDominioImpl{

    private boolean posicoesDescobertas[];
    private String palavraArriscada = null;
    private Palavra palavra;
    

    private Item (int id, Palavra palavra){ 
        this.palavra = palavra;
    }

    private Item (int id, Palavra palavra, int[] posicoesDescobertas, String palavraArriscada){
    	this.palavra = palavra;
    	for(int i = 0; i < posicoesDescobertas.length;i++) {
    		if(posicoesDescobertas[i]==1) { //verificar se o vetor vem com 0 ou 1
    			this.posicoesDescobertas[i]=true;    			
    		}
    		else {
    			this.posicoesDescobertas[i]=false;
    		}
    		
    	}
    	
    	this.palavraArriscada=palavraArriscada;
    		
    }
    
    Item criar(int id, Palavra palavra) {
    	Item item = new Item(id, palavra);
    	return item;
    }
    
    public Item reconstituir(int id, Palavra palavra, int posicoesDescoberta[], String palavraArriscada) {
    	//Verificar como recuperar sem repositorio
    }   
    

    public Palavra getPalavra (){
        return this.palavra;
    }
    
    public Letra[] getLetrasDescobertas() {
    	Letra[] letrasDescobertas = null;
    	
    	for(int i = 0;i<this.palavra.getTamanho();i++) {
    		if(this.posicoesDescobertas[i]==true) {
    			letrasDescobertas[i]=this.palavra.getLetra(i);
    		}
    	}
    	return letrasDescobertas;
    }
    
    public Letra[] getLetrasEncobertas() {
    	Letra [] letrasEncobertas = null;
    	for(int i = 0;i<this.palavra.getTamanho();i++) {
    		if(this.posicoesDescobertas[i]==false) {
    			letrasEncobertas[i]=this.palavra.getLetra(i);
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
    	
    	int[] posicoes;
    	int cont = 0;
    	posicoes = this.palavra.tentar(codigo);
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
    	return this.palavra.comparar(palavraArriscada);
    }
    
    
}
    
    
    