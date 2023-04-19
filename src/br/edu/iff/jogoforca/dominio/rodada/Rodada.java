package br.edu.iff.jogoforca.dominio.rodada;

import java.util.List;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;

public class Rodada extends ObjetoDominioImpl {
	
	private static final int maxPalavras = 3;
	private static final int maxErros = 10;
	private static final int pontosQuandoDescobreTodasAsPalavras = 100;
	private static final int pontosPorLetraEncoberta = 15;
	private BonecoFactory bonecoFactory;
	private Item[] itens;
	private Jogador jogador;
	private List<Letra> letrasErradas = null;
	
	RodadaRepository repo;
	
	
	public static int getMaxPalavras() {
		return maxPalavras;
	}
	
	public static void setMaxPalavras(int max) {
		maxPalavras = max;
	}
	
	public static int getMaxErros () {
		return maxErros;
	}
	
	public static void getMaxErros (int max) {
		maxErros = max;
	}
	
	public static int getPontosQuandoDescobreTodasAsPalavras() {
		return pontosQuandoDescobreTodasAsPalavras;
	}
	
	public static void setPontosQuandoDescobreTodasAsPalavras(int pontos) {
		pontosQuandoDescobreTodasAsPalavras = pontos;
	}
	
	public static int getPontosPorLetraEncoberta() {
		return pontosPorLetraEncoberta;
	}
	
	public static void setPontosPorLetraEncoberta(int pontos) {
		pontosPorLetraEncoberta = pontos;
	}
	
	public void setBonecoFactory (BonecoFactory bonecoFactory) {
		this.bonecoFactory = bonecoFactory;
	}
	
	public BonecoFactory getBonecoFactory() {
		return this.bonecoFactory;
	}
	
	public Rodada criar(long id, Palavra[] palavras, Jogador jogador) {
		Rodada novaRodada = new Rodada(id, palavras, jogador);
		return novaRodada;
	}
	
	public Rodada reconstituir(long id, Item[] itens, Letra[] erradas, Jogador jogador) {
		Rodada rodadaRecuperada = repo.getPorId(id);
	}
	
	private Rodada(long id, Palavra[] palavras, Jogador jogador) {
		super(id);
		for(int i = 0; i < palavras.length;i++) {
			this.itens[i].criar(i, palavras[i]);
		}
		this.jogador = jogador;
	}
	
	public Jogador getJogador() {
		return this.jogador;
	}
	
	public Tema getTema() {
		return itens[0].getPalavra().getTema();
	}
	
	public Palavra[] getPalavras() {
		Palavra[] palavras = null;
		for(int i = 0; i<itens.length;i++) {
			palavras[i] = itens[i].getPalavra();			
		}
		
		return palavras;
	}
	
	public int getNumPalavras() {
		int cont = 0;
		for(int i = 0;i<itens.length;i++) {
			if(itens[i]!=null) {
				cont++;
			}
		}
		
		return cont;
	}
	
	public void tentar(char codigo) {
		
		for(int i = 0; i<this.getNumPalavras();i++) {
			if(this.itens[i].tentar(codigo)==false) {
				letrasErradas.add(itens[i].getPalavra().getLetraFactory().getLetra(codigo));				
			}
		}
		
	}
	
	public void arriscar(String[] palavras) {
		for(int i = 0; i<palavras.length;i++) {
			itens[i].arriscar(palavras[i]);
		}
	}
	
	public void exibirItens(Object contexto) {
		for(int i = 0; i < getMaxPalavras();i++) {
			itens[i].exibir(contexto);
		}
	}
	
	public void exibirBoneco(Object contexto) {
		this.bonecoFactory.getBoneco().exibir(contexto, this.getQtdeErros());
		
	}
	
	public void exibirPalavras(Object contexto) {
		for(int i = 0; i < getMaxPalavras(); i++) {
			itens[i].getPalavra().exibir(contexto);
		}
	}
	
	public void exibirLetrasErradas(Object contexto) {
		for(int i = 0; i < this.letrasErradas.size(); i++) {
			this.letrasErradas.get(i).exibir(contexto);
		}
	}
	
	public Letra[] getTentativas() {

		int tamanhoTotal = this.getQtdeTentativas();
		
		Letra[] tentativas = new Letra[tamanhoTotal];
		int i;
		int proxPosicao;

		for(i = 0; i < getMaxPalavras(); i++) {
			if(i==0) {
				proxPosicao = 0;
			}
			System.arraycopy(itens[i].getLetrasDescobertas(), 0, tentativas, proxPosicao , itens[i].getLetrasDescobertas().length);
			proxPosicao += itens[i].getLetrasDescobertas().length+1;
		}
		
		System.arraycopy(letrasErradas.toArray(), 0, tentativas, proxPosicao, letrasErradas.toArray().length);
		return tentativas;
		
	}
}
