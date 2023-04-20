package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.dominio.ObjetoDominioImpl;

public class Jogador extends ObjetoDominioImpl{
	private String nome;
	private int pontuacao = 0;
	
	public Jogador criar(long id, String nome) {
		Jogador novoJogador = new Jogador(id, nome);
		return novoJogador;
	}
	
	public Jogador reconstituir(long id, String nome, int pontuacao) {
		Jogador jogadorReconstituido = new Jogador (id, nome, pontuacao);
		return jogadorReconstituido;
	}
	
	private Jogador(long id, String nome) {
		ObjetoDominio(id);
		this.nome = nome;
	}
	
	private Jogador(long id, String nome, int pontuacao) {
		ObjetoDominio(id);
		this.nome = nome;
		this.pontuacao = pontuacao;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getPontuacao() {
		return this.pontuacao;
	}
	
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	
}
