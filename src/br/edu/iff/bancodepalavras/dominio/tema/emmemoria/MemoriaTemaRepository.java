package br.edu.iff.bancodepalavras.dominio.tema.emmemoria;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

public class MemoriaTemaRepository implements TemaRepository{
	
	private MemoriaTemaRepository soleInstance = null;
	private List<Tema> pool;
	
	public MemoriaTemaRepository getSoleInstance() {
		return this.soleInstance; 
	}
	
	private MemoriaTemaRepository() {
		this.soleInstance =  null;
		this.pool = new ArrayList<Tema>();		
	}

	@Override
	public long getProximoId() {
		return pool.size()+1;
	}

	@Override
	public Tema getPorId(long id) {
		for(Tema tema:pool) {
			if(tema.getId()==id) {
				return tema;
			}
		}
		
		throw new RuntimeException("O tema não foi localizado");
	}

	@Override
	public Tema[] getPorNome(String nome) {
		
		int tamanho = 0;		
		for(Tema tema:pool) {
			if(tema.getNome().equals(nome)==true) {
				tamanho++;
			}
		}
		if(tamanho == 0) {
			throw new RuntimeException("Não foram encontrados temas");
		}
		Tema[] temas = new Tema[tamanho];
		int posicaoAtual = 0;
		for(int i = 0;i<pool.size();i++) {
			if(pool.get(i).getNome().equals(nome)==true) {
				temas[posicaoAtual] = pool.get(i);
				posicaoAtual++;
			}
		}
		return temas;			
		
	}

	@Override
	public Tema[] getTodos() {
		return this.pool.toArray(new Tema[this.pool.size()]);
	}

	@Override
	public void inserir(Tema tema) throws RepositoryException {
		this.pool.add(tema);
		
	}

	@Override
	public void atualizar(Tema tema) throws RepositoryException {
		for(Tema temaAtual:pool) {
			if(temaAtual.getId() == tema.getId()) {
				this.remover(temaAtual);
				this.inserir(tema);
			}
		}
		
	}

	@Override
	public void remover(Tema tema) throws RepositoryException {
		this.pool.remove(tema);
		
	}

}
