package br.edu.iff.jogoforca.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.imagem.LetraImagemFactory;
import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.imagem.BonecoImagemFactory;

public class ElementoGrafigoImagemFactory implements ElementoGraficoFactory {

    private static ElementoGrafigoImagemFactory soleInstance = null;

    private ElementoGrafigoImagemFactory(){}

    public static ElementoGrafigoImagemFactory getSoleInstance(){
        if (soleInstance == null){
            soleInstance = new ElementoGrafigoImagemFactory();
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
