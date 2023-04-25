package br.edu.iff.jogoforca.dominio.rodada.sorteio;

import java.util.Random;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
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
        Tema[] temas = this.getTemaRepository().getTodos();
        int qtdeTemas = temas.length;
        Random random = new Random();
        int i = random.nextInt(qtdeTemas-1);
        Palavra[] palavrasDoTema = this.getPalavraRepository().getPorTema(temas[i]); 
        int qtdePalavras = palavrasDoTema.length;
        Palavra[] palavrasSorteadas = new Palavra[Rodada.getMaxPalavras()];
        for(int j = 0; j < Rodada.getMaxPalavras(); j++) {
        	palavrasSorteadas[j] = palavrasDoTema[random.nextInt(qtdePalavras-1)];
        }              
        
        return Rodada.criar(this.getRodadaRepository().getProximoId() , palavrasSorteadas, jogador);
    }

}
