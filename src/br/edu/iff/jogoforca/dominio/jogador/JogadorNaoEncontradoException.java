package br.edu.iff.jogoforca.dominio.jogador;

public class JogadorNaoEncontradoException extends Exception {

	private String jogador;
	public JogadorNaoEncontradoException (String jogador) {
		super("Jogador" + jogador + "não encontrado");
		this.jogador = jogador;
	}
	public String getJogador() {
		return this.jogador;
	}

}
