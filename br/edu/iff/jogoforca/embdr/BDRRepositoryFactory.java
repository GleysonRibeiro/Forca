package br.edu.iff.jogoforca.embdr;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.palavra.embdr.BDRPalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.bancodepalavras.dominio.tema.embdr.BDRTemaRepository;
import br.edu.iff.jogoforca.RepositoryFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.jogoforca.dominio.jogador.embdr.BDRJogadorRepository;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.jogoforca.dominio.rodada.embdr.BDRRodadaRepository;

public class BDRRepositoryFactory implements RepositoryFactory{
	
private static BDRRepositoryFactory soleInstance = null;
	
	public static BDRRepositoryFactory getSoleInstance() {
		return soleInstance;
	}
	
	private BDRRepositoryFactory() {
		soleInstance = null;
	}

	@Override
	public PalavraRepository getPalavraRepository() {
		BDRPalavraRepository palavra = BDRPalavraRepository.getSoleInstance();
		return palavra;
	}

	@Override
	public TemaRepository getTemaRepository() {
		BDRTemaRepository tema = BDRTemaRepository.getSoleInstance();
		return tema;
	}

	@Override
	public RodadaRepository getRodadaRepository() {
		BDRRodadaRepository rodada = BDRRodadaRepository.getSoleInstance();
		return rodada;
	}

	@Override
	public JogadorRepository getJogadorRepository() {
		BDRJogadorRepository jogador = BDRJogadorRepository.getSoleInstance();
		return jogador;
	}

}
