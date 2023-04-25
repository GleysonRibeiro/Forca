package br.edu.iff.bancodepalavras.dominio.letra;

import br.edu.iff.dominio.ObjetoDominio;

public interface LetraFactory extends ObjetoDominio{
	public Letra getLetra(char codigo);
	
	public Letra getLetraEncoberta();
}
