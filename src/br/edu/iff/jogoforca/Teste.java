package br.edu.iff.jogoforca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactory;
//import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactoryImpl;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.jogoforca.Aplicacao;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactory;
import br.edu.iff.repository.RepositoryException;

public class Teste {

	public static void main(String[] args) throws RepositoryException {
		
		Scanner input = new Scanner(System.in); 
		Aplicacao aplicacao = Aplicacao.getSoleInstance();
		RodadaAppService rodadaAppService = RodadaAppService.getSoleInstance();
		int opcao;
		
		JogadorFactory jogadorFactory = aplicacao.getJogadorFactory();
		TemaFactory temaFactory = aplicacao.getTemaFactory();
		RodadaFactory rodadaFactory = aplicacao.getRodadaFactory();
		
		//Inicio - cadastro de temas 
		//Durante o cadastro não é permitido:
		//Espaço, Caracteres especiais, Maiusculas
		
		Tema temaNomes = temaFactory.getTema("Nome de Pessoas");
		PalavraFactory palavraNomesFactory = aplicacao.getPalavraFactory();
		
		palavraNomesFactory.getPalavra("amarildo", temaNomes);
		palavraNomesFactory.getPalavra("mateus", temaNomes);
		palavraNomesFactory.getPalavra("lucas", temaNomes);
		
		Tema temaCarros = temaFactory.getTema("Marcas de Carro");
		PalavraFactory palavraCarrosFactory = aplicacao.getPalavraFactory();
		
		palavraCarrosFactory.getPalavra("wolksvagen", temaCarros);
		palavraCarrosFactory.getPalavra("chevrolet", temaCarros);
		palavraCarrosFactory.getPalavra("hyundai", temaCarros);
		//Fim Cadastro --

		do {
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println("*-*JOGO DA FORCA v1.0*-*");
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-\n");
			System.out.println("1 - Jogar");
			System.out.println("0 - Sair");
			System.out.println("Digite uma opcao: ");
			opcao = input.nextInt();
			switch (opcao) {
			case 1:
				System.out.println("Entre com o nome do jogador atual: ");
				String nomeJogador = input.next();
				
				Jogador jogador = jogadorFactory.getJogador(nomeJogador);
				
				//Define o máximo de palavras que pode ser apresentado em uma rodada
				Rodada.setMaxPalavras(3);
				
				//Definição do máximo de erros possíveis em uma rodada
				//Pode ser alterado, foi iniciado a fins de teste, porém permanece 10 pois o boneco deve ter 10 partes apenas.
				//Rodada.setMaxErros(10);
				
				//Quando a rodada é instanciada ela prepara o jogo, quantas palavras serão exibidas, tema, etc
				Rodada rodada = rodadaFactory.getRodada(jogador);
				
				//Início do jogo
				jogar(rodada, jogador);
				//Salvamento de rodada, não implementado
				//rodadaAppService.salvarRodada(rodada);
				
				break;
			default:
				System.out.println("Ogrigado por jogar! Jogo Finalizado!");
				System.out.println("Jogo criado por:");
				System.out.println("Amarildo Júnior");
				System.out.println("Lucas de Figueiredo");
				System.out.println("Mateus Freitas");
				System.out.println("Para a Disciplina Laboratório Orientado a Objetos Ministrada por Mark Douglas");
				break;
			}
			
		}while(opcao!= 0);
		
		input.close();

	}
	private static void jogar(Rodada rodada, Jogador jogador) {
		
		Scanner input = new Scanner(System.in);
		
		String temaAtual = rodada.getTema().getNome();
		String jogadorAtual = jogador.getNome();

		while(rodada.encerrou() == false) {
			System.out.println("Tema: " + temaAtual + " || Jogador: " + jogadorAtual);

			System.out.println("Letras arriscadas: ");
			for(Letra letraTentativa: rodada.getTentativas()) {
				letraTentativa.exibir(null);
				System.out.print(" ");
			}
			System.out.println();

			System.out.println("Palavras:");
			rodada.exibirItens(null);
			System.out.println();
			System.out.println("Erros: " + rodada.getQtdeErros() + "/" + Rodada.getMaxErros());
			System.out.println("Corpo: ");
			rodada.exibirBoneco(null);
			System.out.println();

			System.out.println("Selecione uma opcao: ");
			System.out.println("1 - Digitar uma letra");
			System.out.println("2 - Arriscar palavra");

            String escolha = input.next();
            
            if (escolha.equalsIgnoreCase("1")) {
            	System.out.print("Digite uma letra: ");
            	char codigo = input.next().charAt(0);

            	if (codigo >= 'A' && codigo <= 'Z') {
            		codigo = (char)(codigo+32);
            	}

            	rodada.tentar(codigo); 

            	System.out.println("");
            } else if (escolha.equalsIgnoreCase("2")) {
            	List<String> palavras = new ArrayList<>();

            	for (int i = 1; i <= rodada.getNumPalavras(); i++) {
            		System.out.println("Qual a palavra "+i+"? ");
            		String palavra = input.next();
            		palavras.add(palavra);
            	}
            	
            	String[] arrayPalavras = palavras.toArray(new String[palavras.size()]);
            	rodada.arriscar(arrayPalavras);
            	System.out.println("");
            }

		}

		if (rodada.descobriu() == true) {
			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println("-*-*-*-*-PARABENS VOCE CONSEGUIU-*-*-*-*-");
			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			rodada.exibirPalavras(null);

		} else {
			System.out.println("Resposta(s) incorreta(s), a(s) palavra(s) era(m): ");
			rodada.exibirPalavras(null);
			rodada.exibirBoneco(null);
			System.out.println("");
			System.out.println("Enforcado, tente novamente!");
		}

		System.out.println("Sua pontuação foi: " + rodada.calcularPontos() + " pontos!");


	}		

}