package br.edu.iff.jogoforca.dominio.jogador.emmemoria;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.bancodepalavras.dominio.palavra.emmemoria.MemoriaPalavraRepository;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;

public class MemoriaJogadorRepository implements JogadorRepository {
	
	public static MemoriaJogadorRepository soleInstance = null;
	private List<Jogador> pool;
	
	public static MemoriaJogadorRepository getSoleInstance() {
		if (soleInstance == null) {
	        soleInstance = new MemoriaJogadorRepository();
	    }
		return soleInstance;
	}
	
	private MemoriaJogadorRepository() {
		this.pool = new ArrayList<>();
	}

	@Override
	public long getProximoId() {
		return pool.size()+1;
	}

	@Override
	public Jogador getPorId(long id) {
		if (pool.isEmpty()) {
	        throw new RuntimeException("O pool está vazio");
	    }
		for(Jogador jogador:pool) {
			if(jogador.getId()==id) {
				return jogador;
			}
		}
		
		throw new RuntimeException("O jogador não foi localizado");
	}

	@Override
	public Jogador getPorNome(String nome) {
		if (pool.isEmpty()) {
	        throw new RuntimeException("O pool está vazio");
	    }
		for(Jogador jogadorAtual:pool) {
			if(jogadorAtual.getNome().equals(nome)==true) {
				return jogadorAtual;
			}
		}
		
		throw new RuntimeException("O jogador não foi localizado");
	}

	@Override
	public void inserir(Jogador jogador) throws RepositoryException {
		if(jogador==null) {
			throw new RuntimeException("O jogador não pode estar vazio");
		}		
		if(pool.contains(jogador)==true) {
			throw new RepositoryException();
		}
		pool.add(jogador);
		
	}

	@Override
	public void atualizar(Jogador jogador) throws RepositoryException {
		if(jogador==null) {
			throw new RuntimeException("O jogador não pode estar vazio");
		}
		if(pool.contains(jogador)==false) {
			throw new RepositoryException();
		}
		for(Jogador jogadorAtual:pool) {
			if(jogadorAtual.getId() == jogador.getId()) {
				this.remover(jogadorAtual);
				this.inserir(jogador);
			}
		}
		
	}

	@Override
	public void remover(Jogador jogador) throws RepositoryException {
		if(jogador==null) {
			throw new RuntimeException("O jogador não pode estar vazio");
		}		
		if(pool.contains(jogador)==false) {
			throw new RepositoryException();
		}
		pool.remove(jogador);
		
	}

}
