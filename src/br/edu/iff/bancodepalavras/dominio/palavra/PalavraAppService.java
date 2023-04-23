package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

public class PalavraAppService {
	
	private static PalavraAppService soleInstance = null;
	
	public PalavraRepository palavraRepository = null;
	public TemaRepository temaRepository = null;
	public PalavraFactory palavraFactory = null;
	
	public static void createSoleInstance(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory palavraFactory) {
		if(soleInstance!=null) {
			throw new RuntimeException("soleInstance já foi criada");
		}
		soleInstance = new PalavraAppService(temaRepository, palavraRepository, palavraFactory);
	}
	
	public static PalavraAppService getSoleInstance() {
		return soleInstance;
	}
	
	private PalavraAppService(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory factory) {
		this.palavraRepository = palavraRepository;
		this.temaRepository = temaRepository;
		this.palavraFactory = factory;
	}
	
	public boolean novaPalavra (String palavra, long idTema) {
		Tema tema;
		Palavra novaPalavra;
		
		try {
			this.temaRepository.getPorId(idTema);	
			
		} catch (RuntimeException e) {
			throw new RuntimeException("Erro ao obter tema: " + e.getMessage());
			
		}
		
		try {
			this.palavraRepository.getPalavra(palavra);
			return true;
		} catch (RuntimeException e) {
			tema = this.temaRepository.getPorId(idTema);
			novaPalavra = this.factory.getPalavra(palavra, tema);
		}
		try {
			this.palavraRepository.inserir(novaPalavra);
			return true;
		} catch (RepositoryException e) {
			return false;
		}
	}
	
	

}
