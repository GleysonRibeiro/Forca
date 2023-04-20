package br.edu.iff.jogoforca.dominio.boneco.imagem;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;


public class BonecoImagem implements Boneco{
private BonecoImagem soleInstance;
	
	public BonecoImagem getSoleInstance() {
		return this.soleInstance;
	}
	
	private BonecoImagem() {
	}
	
	@Override
	public void exibir(Object contexto, int partes) {
		
	}

}
