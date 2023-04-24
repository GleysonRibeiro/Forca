package br.edu.iff.bancodepalavras.dominio.letra.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactoryImpl;

public class LetraImagemFactory extends LetraFactoryImpl {
    private static LetraImagemFactory soleInstance = null;
    
    public static LetraImagemFactory getSoleInstance() {
    	if(soleInstance!=null) {
    		return soleInstance;
    	}
    	LetraImagemFactory factory = new LetraImagemFactory();
    	return factory;
    }

    private LetraImagemFactory(){}

    @Override
    protected Letra criarLetra(char codigo) {
        
        return new LetraImagem(codigo);
    }

    @Override
    public long getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

}
