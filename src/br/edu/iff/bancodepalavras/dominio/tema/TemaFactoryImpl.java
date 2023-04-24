package br.edu.iff.bancodepalavras.dominio.tema;

import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.RepositoryException;

public class TemaFactoryImpl extends EntityFactory implements TemaFactory {

    private static TemaFactoryImpl soleInstance = null;

    // Singleton Parametrizado, alguem vai criar a instancia, e outro alguem vai
    // usar ela. Ele tem duas operaçoes, uma para criar e outra pra retornar.
    public static void createSoleInstance(TemaRepository repository) {
        soleInstance = new TemaFactoryImpl(repository);
    }

    public static TemaFactoryImpl getSoleInstance() {
        if (soleInstance == null) {
            throw new RuntimeException("O objeto ainda não foi criado.");
        }
        return soleInstance;
    }

    private TemaFactoryImpl(TemaRepository repository) {
        super(repository);
    }

    private TemaRepository getTemaRepository() {
        return (TemaRepository) getRepository();
    }

    @Override
    public Tema getTema(String nome) {
        Tema temporario = Tema.criar(getProximoId(), nome);

        if (getTemaRepository() == null) { // Primeiro verificar se o repositório de Temas é nulo, se sim já retorna uma excessão, já que não vai dar pra salvar o tema.
            throw new RuntimeException("Não foi possível acessar o repositório de temas.");
        }

        try {
            getTemaRepository().inserir(temporario); //Se não for nulo, tenta inserir tema no Repositório
        } catch (RepositoryException e) {
            throw new RuntimeException("Não foi possível salvar o tema.");
        }

        return temporario;
    }

}
