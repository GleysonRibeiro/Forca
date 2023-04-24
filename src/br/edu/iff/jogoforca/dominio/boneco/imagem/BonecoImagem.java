package br.edu.iff.jogoforca.dominio.boneco.imagem;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;


public class BonecoImagem implements Boneco{
private static BonecoImagem soleInstance;
	
	public BonecoImagem getSoleInstance() {
		return soleInstance;
	}
	
	private BonecoImagem() {
	}
	
	@Override
	public void exibir(Object contexto, int partes) {
		
	}

	public static Boneco getBoneco() {
		// TODO Auto-generated method stub
		return null;
	}

}
