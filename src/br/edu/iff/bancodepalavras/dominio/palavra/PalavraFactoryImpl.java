package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.RepositoryException;

public class PalavraFactoryImpl extends EntityFactory implements PalavraFactory {

    private static PalavraFactoryImpl soleInstance = null;

    public static void createSoleInstance(PalavraRepository repository) {
        soleInstance = new PalavraFactoryImpl(repository);
    }

    public static PalavraFactoryImpl getSoleInstance() {
        if (soleInstance == null) {
            throw new RuntimeException("O objeto ainda não foi criado.");
        }
        return soleInstance;
    }

    private PalavraFactoryImpl(PalavraRepository repository) {
        super(repository);
    }

    private PalavraRepository getPalavraRepository() {
        return (PalavraRepository) getRepository();
    }


    @Override
    public Palavra getPalavra(String palavra, Tema tema) {
        Palavra temporaria = Palavra.criar(getProximoId(),palavra, tema);

        if (getPalavraRepository() == null) {
            throw new RuntimeException("Não foi possível acessar o repositório de palavras.");
        }

        try {
            getPalavraRepository().inserir(temporaria);
        } catch (RepositoryException e) {
            throw new RuntimeException("Não foi possível salvar a palavra.");
        }

        return temporaria;
    }
}
    

