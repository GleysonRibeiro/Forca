package br.edu.iff.jogoforca.texto;
import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.texto.LetraTextoFactory;
import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.texto.BonecoTextoFactory;

public class ElementoGraficoTextoFactory implements ElementoGraficoFactory  {
    
    private static ElementoGraficoTextoFactory soleInstance = null;

    private ElementoGraficoTextoFactory(){}

    public static ElementoGraficoTextoFactory getSoleInstance(){
        if (soleInstance == null){
            soleInstance = new ElementoGraficoTextoFactory();
        }
        return soleInstance;
    }

    public Boneco getBoneco(){
        return BonecoTextoFactory.getSoleInstance();
    }

    @Override
    public Letra getLetra(char codigo) {
        return LetraTextoFactory.getLetra(codigo);
    }

    @Override
    public Letra getLetraEncoberta() {
        return LetraTextoFactory.getLetraEncoberta();
    }

    @Override
    public long getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

}
