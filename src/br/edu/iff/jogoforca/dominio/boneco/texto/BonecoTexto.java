package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;

public class BonecoTexto implements Boneco {
	
	private BonecoTexto soleInstance;
	
	public BonecoTexto getSoleInstance() {
		return this.soleInstance;
	}
	
	private BonecoTexto() {
	}
	
	@Override
	public void exibir(Object contexto, int partes) {
		if (partes == 1) {
		    System.out.println("cabeça");
		} else if (partes == 2) {
		    System.out.println("cabeça, olho esquerdo");
		} else if (partes == 3) {
		    System.out.println("cabeça, olho esquerdo, olho direito");
		} else if (partes == 4) {
		    System.out.println("cabeça, olho esquerdo, olho direito, nariz");
		} else if (partes == 5) {
		    System.out.println("cabeça, olho esquerdo, olho direito, nariz, boca");
		} else if (partes == 6) {
		    System.out.println("cabeça, olho esquerdo, olho direito, nariz, boca, tronco");
		} else if (partes == 7) {
		    System.out.println("cabeça, olho esquerdo, olho direito, nariz, boca, tronco, braço esquerdo");
		} else if (partes == 8) {
		    System.out.println("cabeça, olho esquerdo, olho direito, nariz, boca, tronco, braço esquerdo, braço direito");
		} else if (partes == 9) {
		    System.out.println("cabeça, olho esquerdo, olho direito, nariz, boca, tronco, braço esquerdo, braço direito, perna esquerda");
		} else if (partes == 10) {
		    System.out.println("cabeça, olho esquerdo, olho direito, nariz, boca, tronco, braço esquerdo, braço direito, perna esquerda, perna direita");
		} else {
			throw new RuntimeException("Quantidade de partes inválida");
		}
	}
	

}
