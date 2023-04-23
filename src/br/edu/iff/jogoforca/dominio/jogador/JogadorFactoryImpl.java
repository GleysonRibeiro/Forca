package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.RepositoryException;

public class JogadorFactoryImpl extends EntityFactory implements JogadorFactory {
    
    private static JogadorFactoryImpl soleInstance = null;

    public static void createSoleInstance(JogadorRepository repository) {
        soleInstance = new JogadorFactoryImpl(repository);
    }

    public static JogadorFactoryImpl getSoleInstance() {
        if (soleInstance == null) {
            throw new RuntimeException("O objeto ainda não foi criado.");
        }
        return soleInstance;
    }

    private JogadorFactoryImpl(JogadorRepository repository) {
        super(repository);
    }

    private JogadorRepository getPalavraRepository() {
        return (JogadorRepository) getRepository();
    }


    @Override
    public Jogador getJogador(String nome) {
        Jogador temporario = Jogador.criar(getProximoId(), nome);

        if (getPalavraRepository() == null) {
            throw new RuntimeException("Não foi possível acessar o repositório de palavras.");
        }

        try {
            getPalavraRepository().inserir(temporario);
        } catch (RepositoryException e) {
            throw new RuntimeException("Não foi possível salvar a palavra.");
        }

        return temporario;
    }
}
