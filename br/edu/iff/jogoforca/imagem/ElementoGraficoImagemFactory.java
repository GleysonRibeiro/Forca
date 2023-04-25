package br.edu.iff.jogoforca.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;


public class ElementoGraficoImagemFactory implements ElementoGraficoFactory {

    private static ElementoGraficoImagemFactory soleInstance = null;

    private ElementoGraficoImagemFactory(){}

    public static ElementoGraficoImagemFactory getSoleInstance(){
        if (soleInstance == null){
            soleInstance = new ElementoGraficoImagemFactory();
        }
        return soleInstance;
    }

    public Boneco getBoneco(){
        return null;
    }

    @Override
    public Letra getLetra(char codigo) {
        return null;
    }

    @Override
    public Letra getLetraEncoberta() {
        return null;
    }

    @Override
    public long getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}
