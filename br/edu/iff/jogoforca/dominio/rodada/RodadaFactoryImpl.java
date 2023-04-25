package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.factory.EntityFactory;

public abstract class RodadaFactoryImpl extends EntityFactory implements RodadaFactory {

    private TemaRepository temaRepository;

    private PalavraRepository palavraRepository;


    protected RodadaFactoryImpl(RodadaRepository repository, TemaRepository temaRepository, PalavraRepository palavraRepository){
        super(repository); // Chama a super pois ela herda a relação de Repository para Entity factory, só que com override de relacionamento, herdando o RodadaRepository
        this.temaRepository = temaRepository; // Chama o tema para criar o tema.
        this.palavraRepository = palavraRepository; // Chama a palavra pra criar a palavra. OBS: O tema nao conhece as suas palavras, entao ele pega tema, e pega as palavras do tema.
    }

    protected RodadaRepository getRodadaRepository(){
        return (RodadaRepository) getRepository();
    } //Como ele ta herdando de EntityFactory, e entityFactory contem repository dentro dele, voce deve passar o tipo de repositório que quer acessar.

    protected TemaRepository getTemaRepository(){
        return this.temaRepository;
    }

    protected PalavraRepository getPalavraRepository(){
        return this.palavraRepository;
    }
    
}
