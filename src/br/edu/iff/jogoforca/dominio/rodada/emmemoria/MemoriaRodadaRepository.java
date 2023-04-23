package br.edu.iff.jogoforca.dominio.rodada.emmemoria;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.bancodepalavras.dominio.palavra.emmemoria.MemoriaPalavraRepository;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;

public class MemoriaRodadaRepository implements RodadaRepository {

	private static MemoriaRodadaRepository soleInstance = null;
	private List<Rodada> pool;
	public static MemoriaRodadaRepository getSoleInstance() {
		if (soleInstance == null) {
	        soleInstance = new MemoriaRodadaRepository();
	    }
		return soleInstance;
	}
	
	private MemoriaRodadaRepository() {
		this.pool = new ArrayList<>();		
	}
	
	@Override
	public long getProximoId() {
		return pool.size()+1;
	}

	@Override
	public Rodada getPorId(long id) {
		if (pool.isEmpty()) {
	        throw new RuntimeException("O pool está vazio");
	    }
		for(Rodada rodada:pool) {
			if(rodada.getId()==id) {
				return rodada;
			}
		}
		
		throw new RuntimeException("O tema não foi localizado");
	}

	@Override
	public Rodada[] getPorJogador(Jogador jogador) {
		if (pool.isEmpty()) {
	        throw new RuntimeException("O pool está vazio");
	    }
		int tamanho = 0;
		for(Rodada rodada:pool) {
			if(rodada.getJogador().getId()==jogador.getId()) {
				tamanho++;				
			}
		}
		if(tamanho == 0) {
			throw new RuntimeException("Não foram encontradas rodadas para o jogador");
		}
		Rodada[] rodadas= new Rodada[tamanho];
		int posicaoAtual = 0;
		for(int i = 0; i<pool.size(); i++) {
			if(pool.get(i).getJogador().getId()==jogador.getId()) {
				rodadas[posicaoAtual] = pool.get(i);
				posicaoAtual++;
			}
		}
		return rodadas;
	}

	@Override
	public void inserir(Rodada rodada) throws RepositoryException {
		if(rodada==null) {
			throw new RuntimeException("A rodada não pode estar vazia");
		}		
		if(pool.contains(rodada)==true) {
			throw new RepositoryException();
		}
		pool.add(rodada);
		
	}

	@Override
	public void atualizar(Rodada rodada) throws RepositoryException {
		if(rodada==null) {
			throw new RuntimeException("A rodada não pode estar vazia");
		}
		if(pool.contains(rodada)==false) {
			throw new RepositoryException();
		}
		for(Rodada rodadaAtual:pool) {
			if(rodadaAtual.getId() == rodada.getId()) {
				this.remover(rodadaAtual);
				this.inserir(rodada);
			}
		}
		
	}

	@Override
	public void remover(Rodada rodada) throws RepositoryException {
		if(rodada==null) {
			throw new RuntimeException("A rodada não pode estar vazia");
		}		
		if(pool.contains(rodada)==false) {
			throw new RepositoryException();
		}
		pool.remove(rodada);
		
	}
	

}
