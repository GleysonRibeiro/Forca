package br.edu.iff.bancodepalavras.dominio.letra.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactoryImpl;
import br.edu.iff.jogoforca.dominio.boneco.texto.BonecoTextoFactory;

public class LetraTextoFactory extends LetraFactoryImpl  {
    
    private static LetraTextoFactory soleInstance = null;
    
    public static LetraTextoFactory getSoleInstance() {
        if (soleInstance == null){ // Se ainda estiver nula, cria uma nova soleInstance, senão retorna a que já existe.
            soleInstance = new LetraTextoFactory();
        }
        return soleInstance;
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
