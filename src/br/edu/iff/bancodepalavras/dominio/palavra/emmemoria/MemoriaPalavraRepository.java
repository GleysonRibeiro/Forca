package br.edu.iff.bancodepalavras.dominio.palavra.emmemoria;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.repository.RepositoryException;

public class MemoriaPalavraRepository implements PalavraRepository{
	
	private MemoriaPalavraRepository soleInstance = null;
	private List<Palavra> pool;
	
	private MemoriaPalavraRepository() {
		this.soleInstance = null;
		this.pool = new ArrayList<Palavra>();
		
	}
	
	public MemoriaPalavraRepository getSoleInstance() {
		return this.soleInstance;
	}

	@Override
	public long getProximoId() {	
			
		return pool.size()+1;
	}

	@Override
	public Palavra getPorId(long id) {
		
		for(Palavra palavra:pool) {
			if(palavra.getId()==id) {
				return palavra;
			}
		}
		
		throw new RuntimeException("A palavra não foi localizada");
	}

	@Override
	public Palavra[] getPorTema(Tema tema) {
		int tamanho = 0;
		for(Palavra palavra:pool) {
			if(palavra.getTema()==tema) {
				tamanho++;
			}
		}
		if(tamanho==0) {
			throw new RuntimeException("Não há palavras com o tema.");
		}
		Palavra[] palavras = new Palavra[tamanho];
		int i = 0;
		for(Palavra palavra:pool) {
			if(palavra.getTema()==tema) {
				palavras[i] = palavra;
				i++;
			}
		}
		return palavras;
	}

	
	@Override
	public Palavra[] getTodas() {
		
		return this.pool.toArray(new Palavra[this.pool.size()]);
	}

	@Override
	public Palavra getPalavra(String palavra) {
		
		for(Palavra palavraAtual:pool) {
			if(palavraAtual.comparar(palavra)==true) {
				return palavraAtual;
			}
		}
		
		throw new RuntimeException("A palavra não foi localizada");
	}

	@Override
	public void inserir(Palavra palavra) throws RepositoryException {
		this.pool.add(palavra);
		
	}

	@Override
	public void atualizar(Palavra palavra) throws RepositoryException {
		for(Palavra palavraAtual:pool) {
			if(palavraAtual.getId() == palavra.getId()) {
				this.remover(palavraAtual);
				this.inserir(palavra);
			}
		}
		
	}

	@Override
	public void remover(Palavra palavra) throws RepositoryException {
		this.pool.remove(palavra);
		
	}

}
