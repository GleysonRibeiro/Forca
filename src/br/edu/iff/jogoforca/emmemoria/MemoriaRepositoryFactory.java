package br.edu.iff.jogoforca.emmemoria;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.palavra.emmemoria.MemoriaPalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.bancodepalavras.dominio.tema.emmemoria.MemoriaTemaRepository;
import br.edu.iff.jogoforca.RepositoryFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.jogoforca.dominio.jogador.emmemoria.MemoriaJogadorRepository;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.jogoforca.dominio.rodada.emmemoria.MemoriaRodadaRepository;

public class MemoriaRepositoryFactory implements RepositoryFactory  {
	
	private static MemoriaRepositoryFactory soleInstance = null;
	
	public static MemoriaRepositoryFactory getSoleInstance() {
		return soleInstance;
	}
	
	private MemoriaRepositoryFactory() {
		soleInstance = null;
	}

	@Override
	public PalavraRepository getPalavraRepository() {
		MemoriaPalavraRepository palavra = MemoriaPalavraRepository.getSoleInstance();
		return palavra;
	}

	@Override
	public TemaRepository getTemaRepository() {
		MemoriaTemaRepository tema = MemoriaTemaRepository.getSoleInstance();
		return tema;
	}

	@Override
	public RodadaRepository getRodadaRepository() {
		MemoriaRodadaRepository rodada = MemoriaRodadaRepository.getSoleInstance();
		return rodada;
	}

	@Override
	public JogadorRepository getJogadorRepository() {
		MemoriaJogadorRepository jogador = MemoriaJogadorRepository.getSoleInstance();
		return jogador;
	}

}
