package br.edu.iff.jogoforca;

import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.letra.texto.LetraTextoFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactoryImpl;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactoryImpl;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactoryImpl;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactory;
import br.edu.iff.jogoforca.dominio.rodada.sorteio.RodadaSorteioFactory;
import br.edu.iff.jogoforca.embdr.BDRRepositoryFactory;
import br.edu.iff.jogoforca.emmemoria.MemoriaRepositoryFactory;
import br.edu.iff.jogoforca.imagem.ElementoGraficoImagemFactory;
import br.edu.iff.jogoforca.texto.ElementoGraficoTextoFactory;

public class Aplicacao {
	
	private final static String[] TIPOS_REPOSITORY_FACTORY = {"memoria", "relacional"};
	private final static String[] TIPOS_ELEMENTO_GRAFICO_FACTORY = {"texto", "imagem"};
	private final static String[] TIPOS_RODADA_FACTORY = {"sorteio"};
	private static Aplicacao soleInstance = null;
	private String tipoRepositoryFactory = TIPOS_REPOSITORY_FACTORY[0];
	private String tipoElementoGraficoFactory = TIPOS_ELEMENTO_GRAFICO_FACTORY[0];
	private String tipoRodadaFactory = TIPOS_RODADA_FACTORY[0];
	
	public static Aplicacao getSoleInstance() {
		if(soleInstance!=null) {
			return soleInstance;
		}
		soleInstance = new Aplicacao();
		
		return soleInstance;
	}
		
	private Aplicacao() {};
	
	public void configurar() {
		
		RodadaSorteioFactory.createSoleInstance(this.getRepositoryFactory().getRodadaRepository(), this.getRepositoryFactory().getTemaRepository(), this.getRepositoryFactory().getPalavraRepository());
		PalavraAppService.createSoleInstance(this.getRepositoryFactory().getTemaRepository(), this.getRepositoryFactory().getPalavraRepository(), getPalavraFactory());
		RodadaAppService.createSoleInstance(this.getRodadaFactory(), this.getRepositoryFactory().getRodadaRepository(), this.getRepositoryFactory().getJogadorRepository());
		Palavra.setLetraFactory(this.getLetraFactory());
		Rodada.setBonecoFactory(this.getBonecoFactory());
		
		this.getRepositoryFactory();
		this.getElementoGraficoFactory();
		this.getBonecoFactory();
		
		this.getRodadaFactory();
		
		this.getTemaFactory();
		this.getPalavraFactory();
		this.getJogadorFactory();	
		
	}
	
	public String[] getTiposRepositoryFactory() {
		return TIPOS_REPOSITORY_FACTORY;
	}
	
	public void setTipoRepositoryFactory(String tipo) {
		this.tipoRepositoryFactory = tipo;
		this.configurar();
	}
	
	public RepositoryFactory getRepositoryFactory() {
		if(this.tipoRepositoryFactory=="memoria") {
			MemoriaRepositoryFactory repositoryFactory = null;
			repositoryFactory = MemoriaRepositoryFactory.getSoleInstance();
			return repositoryFactory;
		}else if(this.tipoRepositoryFactory=="relacional") {
			BDRRepositoryFactory repositoryFactory = null;
			repositoryFactory = BDRRepositoryFactory.getSoleInstance();
			return repositoryFactory;
		}
		throw new RuntimeException("Tipo de repositório não previsto");
	}
	
	public String[] getTiposElementoGraficoFactory() {
		return TIPOS_ELEMENTO_GRAFICO_FACTORY;
	}
	
	public void setTipoElementoGraficoFactory(String tipo) {
		this.tipoElementoGraficoFactory = tipo;
		this.configurar();		
	}
	
	private ElementoGraficoFactory getElementoGraficoFactory() {
		if(this.tipoElementoGraficoFactory=="texto") {
			return ElementoGraficoTextoFactory.getSoleInstance();
		}else if(this.tipoRepositoryFactory=="imagem") {
			
			return ElementoGraficoImagemFactory.getSoleInstance();
		}
		throw new RuntimeException("Tipo de elemento gráfico não previsto");
	}
	
	public BonecoFactory getBonecoFactory() {
		return this.getElementoGraficoFactory();
	}
	
	public LetraFactory getLetraFactory() {
		return this.getElementoGraficoFactory();
	}
	
	public String[] getTiposRodadaFactory() {
		return TIPOS_RODADA_FACTORY;
	}
	
	public void setTipoRodadaFactory(String tipo) {
		this.tipoRodadaFactory = tipo;
		this.configurar();		
	}
	
	public RodadaFactory getRodadaFactory() {
		if(this.tipoRodadaFactory=="sorteio") {
			RodadaSorteioFactory rodadaFactory = null;
			rodadaFactory = RodadaSorteioFactory.getSoleInstance();
			return rodadaFactory;
		}
		throw new RuntimeException("Tipo de rodada não prevista");
	}
	
	public TemaFactory getTemaFactory() {
		TemaRepository temaRepository = this.getRepositoryFactory().getTemaRepository();
		TemaFactoryImpl.createSoleInstance(temaRepository);
		TemaFactoryImpl temaFactory = TemaFactoryImpl.getSoleInstance();	
		
		return temaFactory;
	}
	
	public PalavraFactory getPalavraFactory() {
		PalavraRepository palavraRepository = this.getRepositoryFactory().getPalavraRepository();
		PalavraFactoryImpl.createSoleInstance(palavraRepository);
		PalavraFactoryImpl palavraFactory = PalavraFactoryImpl.getSoleInstance();	
		
		return palavraFactory;
	}
	
	public JogadorFactory getJogadorFactory() {
		JogadorRepository jogadorRepository = this.getRepositoryFactory().getJogadorRepository();
		JogadorFactoryImpl.createSoleInstance(jogadorRepository);
		JogadorFactoryImpl jogadorFactory = JogadorFactoryImpl.getSoleInstance();	
		
		return jogadorFactory;
	}
	
	
	
	
	
	
	
	
	
	
	

}
