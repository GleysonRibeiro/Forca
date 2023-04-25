package br.edu.iff.jogoforca.dominio.rodada;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;

public class Rodada extends ObjetoDominioImpl {
	
	private static int maxPalavras = 3;
	private static int maxErros = 10;
	private static int pontosQuandoDescobreTodasAsPalavras = 100;
	private static int pontosPorLetraEncoberta = 15;
	private static BonecoFactory bonecoFactory;
	private Item[] itens = new Item[maxPalavras];
	private Jogador jogador;
	private List<Letra> letrasErradas = new ArrayList<>();
	private Boneco boneco;
	
	
	public static int getMaxPalavras() {
		return maxPalavras;
	}
	
	public static void setMaxPalavras(int max) {
		maxPalavras = max;
	}
	
	public static int getMaxErros () {
		return maxErros;
	}
	
	public static void setMaxErros (int max) {
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
	
	public static void setBonecoFactory (BonecoFactory factory) {
		bonecoFactory = factory;
	}
	
	public BonecoFactory getBonecoFactory() {
		return this.bonecoFactory;
	}
	
	public static Rodada criar(long id, Palavra[] palavras, Jogador jogador) {
		Rodada novaRodada = new Rodada(id, palavras, jogador);
		return novaRodada;
	}
	
	public Rodada reconstituir(long id, Item[] itens, Letra[] erradas, Jogador jogador) {
		Rodada reconstituida = new Rodada(id, itens, erradas, jogador);
		return reconstituida;
	}
	
	private Rodada(long id, Palavra[] palavras, Jogador jogador) {
		if(bonecoFactory==null) {
			throw new RuntimeException("bonecoFactory não pode estar vazio");
		}
		ObjetoDominio(id);
		for(int i = 0; i < palavras.length;i++) {
			this.itens[i] = Item.criar(i, palavras[i]);
		}
		this.boneco = bonecoFactory.getBoneco();		
		this.jogador = jogador;
		
	}
	
	private Rodada(long id, Item[] itens, Letra[] erradas, Jogador jogador) {
		if(bonecoFactory==null) {
			throw new RuntimeException("bonecoFactory não pode estar vazio");
		}
		ObjetoDominio(id);
		this.itens = itens;
		this.letrasErradas = new ArrayList<>(Arrays.asList(erradas));
		this.jogador = jogador;
		this.boneco = bonecoFactory.getBoneco();
		
	}
	
	public Jogador getJogador() {
		return this.jogador;
	}
	
	public Tema getTema() {
		return itens[0].getPalavra().getTema();
	}
	
	public Palavra[] getPalavras() {
		Palavra[] palavras = new Palavra[this.getNumPalavras()];
		for(int i = 0; i<this.getNumPalavras();i++) {
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
		
		if(this.encerrou()==true) {
			throw new RuntimeException("Rodada já encerrou");
		}
		
		for(int i = 0; i<this.getNumPalavras();i++) {
			if(this.itens[i].tentar(codigo)==false) {
				itens[i].getPalavra();
				if(!letrasErradas.contains(Palavra.getLetraFactory().getLetra(codigo))) {
					letrasErradas.add(Palavra.getLetraFactory().getLetra(codigo));
				}
								
			}
		}
		if(this.encerrou()==true) {
			this.jogador.setPontuacao(this.calcularPontos());
		}
		
	}
	
	public void arriscar(String[] palavras) {
		if(this.arriscou()==true) {
			throw new RuntimeException("Jogador já arriscou");
		}
		if(this.encerrou()==true) {
			throw new RuntimeException("Rodada já encerrou");
		}
		for(int i = 0; i<palavras.length;i++) {
			itens[i].arriscar(palavras[i]);
		}
		if(this.encerrou()==true) {
			this.jogador.setPontuacao(this.calcularPontos());
		}
	}
	
	public void exibirItens(Object contexto) {
		for(int i = 0; i < this.getNumPalavras();i++) {
			itens[i].exibir(contexto);
		}
	}
	
	public void exibirBoneco(Object contexto) {
		this.boneco.exibir(contexto, this.getQtdeErros());
		
	}
	
	public void exibirPalavras(Object contexto) {
		for(int i = 0; i < this.getNumPalavras(); i++) {
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
		
		System.arraycopy(this.getCertas(), 0, tentativas, 0 , this.getQtdeAcertos());
		System.arraycopy(letrasErradas.toArray(), 0, tentativas, this.getQtdeAcertos(), this.getQtdeErros());
		
		return tentativas;
		
	}
	
	public Letra[] getCertas() {
		int tamanhoTotal = this.getQtdeAcertos();
		Letra[] certas = new Letra[tamanhoTotal];
		int i;
		int proxPosicao = 0;

		for(i = 0; i < getMaxPalavras(); i++) {
			if(i==0) {
				proxPosicao = 0;
			}
			System.arraycopy(itens[i].getLetrasDescobertas(), 0, certas, proxPosicao , itens[i].getLetrasDescobertas().length);
			proxPosicao += itens[i].getLetrasDescobertas().length;
		}
		
		return certas; 
	}
	public Letra[] getErradas() {
		Letra[] erradas = new Letra[letrasErradas.size()];
		for(int i = 0; i<letrasErradas.size();i++) {
			erradas[i] = letrasErradas.get(i);			
		}
		
		return erradas;
	}
	
	public int calcularPontos() {
		 int pontos = 0;
		 
		 if(this.descobriu()==true) {
			 pontos = getPontosQuandoDescobreTodasAsPalavras();
		 
		 
			 for(int i = 0; i < getMaxPalavras(); i++) {
				 pontos += this.itens[i].calcularPontosLetrasEncobertas(getPontosPorLetraEncoberta());
			 }		 
		 }
		 
		return pontos;
	}
	
	public boolean encerrou() {
		if(this.arriscou()==true||this.descobriu()==true||this.atingiuMaxErros()==true) {
			return true;
		}
		return false;
	}
	
	public boolean descobriu() {
		
		for(int i = 0; i < this.getNumPalavras(); i++) {
			if(this.itens[i].descobriu()==false) {
				return false;
			}
		}	
		
		return true;
	}
	
	public boolean arriscou() {
			
		for(int i = 0; i < this.getNumPalavras(); i++) {
			if(this.itens[i].arriscou()==true) {
				return true;
			}
		}	
		
		return false;
	}
	
	public int getQtdeTentativasRestantes() {
		return getMaxErros() - this.getQtdeErros(); 
	}
	
	public int getQtdeErros() {
		return this.getErradas().length;
	}
	
	public int getQtdeAcertos() {
		/*int cont=0;
		for(Item item:itens) {
			cont+=item.getLetrasDescobertas().length;
		}
		return cont;*/
		return this.getCertas().length;
	}
	
	public int getQtdeTentativas () {
		return this.getQtdeAcertos()+this.getQtdeErros();
	}
	
	public boolean atingiuMaxErros() {
		if(this.getQtdeTentativasRestantes()==0) {
			return true;
		}
		return false;
	}
	
	
	
	
}
