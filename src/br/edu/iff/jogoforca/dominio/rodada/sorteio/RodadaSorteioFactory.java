package br.edu.iff.jogoforca.dominio.rodada.sorteio;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;

public class RodadaSorteioFactory extends RodadaFactoryImpl {

    private static RodadaSorteioFactory soleInstance = null;

    private RodadaSorteioFactory(RodadaRepository repository, TemaRepository temaRepository,PalavraRepository palavraRepository) {
        super(repository, temaRepository, palavraRepository);
    
    }

    //Singleton Parametrizado, alguem vai criar a instancia, e outro alguem vai usar ela. Ele tem duas operaçoes, uma para criar e outra pra retornar.
    public static void createSoleInstance(RodadaRepository repository, TemaRepository temaRepository, PalavraRepository palavraRepository){
        soleInstance = new RodadaSorteioFactory(repository, temaRepository, palavraRepository);
    }
    
    public static RodadaSorteioFactory getSoleInstance(){
        if(soleInstance == null){
            throw new RuntimeException("O objeto ainda não foi criado.");
        }
        return soleInstance;
    }

    public Rodada getRodada(Jogador jogador){
        throw new UnsupportedOperationException("Unimplemented method 'getRodada'");
    }

}
