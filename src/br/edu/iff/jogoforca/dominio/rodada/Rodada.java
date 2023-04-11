package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.dominio.ObjetoDominioImpl;

public class Rodada extends ObjetoDominioImpl {
	
	private static final int maxPalavras = 3;
	private static final int maxErros = 0;
	private static final int pontosQuandoDescobreTodasAsPalavras = 100;
	private static final int pontosPorLetraEncoberta = 15;
	private BonecoFactory bonecoFactory;
	private Item[] itens;
	private Jogador jogador;
	
	
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
		//Verificar como recuperar sem repositorio
	}
	
	private Rodada(long id, Palavra[] palavras, Jogador jogador) {
		super(id);
		for(int i = 0; i < palavras.length;i++) {
			this.itens[i].criar(i, palavras[i]);
		}
		this.jogador = jogador;
	}
	
	//continuar implementação a partir daqui, tratar anotações do diagrama
}
