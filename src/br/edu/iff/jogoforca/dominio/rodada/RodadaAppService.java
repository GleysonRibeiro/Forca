package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorNaoEncontradoException;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;

public class RodadaAppService {
	private static RodadaAppService soleInstance;
	private RodadaRepository rodadaRepository;
	private JogadorRepository jogadorRepository;
	private RodadaFactory factory;
	
	public static void createSoleInstance(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository, JogadorRepository jogadorRepository) {
		soleInstance = new RodadaAppService(rodadaFactory, rodadaRepository, jogadorRepository);		
		
	}
	
	public RodadaAppService getSoleInstance() {
		return soleInstance;
	}
	
	private RodadaAppService(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository, JogadorRepository jogadorRepository) {
		this.rodadaRepository = rodadaRepository;
		this.jogadorRepository = jogadorRepository;
		this.factory = rodadaFactory;
	}
	
	public Rodada novaRodada(long idJogador) {
		Jogador jogador;
		try {
			jogador = this.jogadorRepository.getPorId(idJogador);
		} catch(RuntimeException e) {
			throw new RuntimeException("Jogador não encontrado");
		}
		Rodada novaRodada = this.factory.getRodada(jogador);
		this.salvarRodada(novaRodada);
	}
	
	public Rodada novaRodada(String nomeJogador) {
		Jogador jogador;
		try {
			jogador = this.jogadorRepository.getPorNome(nomeJogador);
		} catch(RuntimeException e) {
			throw new JogadorNaoEncontradoException(nomeJogador);
		}
		Rodada novaRodada = this.factory.getRodada(jogador);
		this.salvarRodada(novaRodada);
	}
	
	public Rodada novaRodada(Jogador jogador) {
		if(jogador==null) {
			throw new RuntimeException("Jogador não pode estar vazio");
		}
		Rodada novaRodada = this.factory.getRodada(jogador);
		this.salvarRodada(novaRodada);
	}
	
	public boolean salvarRodada(Rodada rodada) {
		try {
			this.rodadaRepository.inserir(rodada);
			return true;
		} catch (RepositoryException e) {
			return false;
		}
	}
	
	

}
