package br.edu.iff.bancodepalavras.dominio.tema.embdr;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

public class BDRTemaRepository implements TemaRepository {

	private BDRTemaRepository soleInstance = null;
	
	public BDRTemaRepository getSoleInstance() {
		return this.soleInstance;
	}
	
	private BDRTemaRepository() {
		
	}
	
	@Override
	public long getProximoId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tema getPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tema[] getPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tema[] getTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserir(Tema tema) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Tema tema) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(Tema tema) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

}
