package br.edu.iff.bancodepalavras.dominio.letra.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactoryImpl;

public class LetraTextoFactory extends LetraFactoryImpl  {
    
    private static LetraTextoFactory soleInstance = null;
    
    public static LetraTextoFactory getSoleInstance() {
    	if(soleInstance!=null) {
    		return soleInstance;
    	}
    	LetraTextoFactory factory = new LetraTextoFactory();
    	return factory;
    }

    private LetraTextoFactory(){}

    @Override
    protected Letra criarLetra(char codigo) {
        
        return new LetraTexto(codigo);
    }

    @Override
    public long getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    
    
}
