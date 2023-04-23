package br.edu.iff.bancodepalavras.dominio.letra;

public abstract class LetraFactoryImpl implements LetraFactory  {

    private Letra[] pool; //Um vetor pois existe uma quantidade exata que pode ser armazenada da variavel, sendo as 26 letras do alfabeto.

	private Letra encoberta;

    protected LetraFactoryImpl(){
        this.pool = new Letra[26];
		this.encoberta = null;
    }

    @Override
    public final Letra getLetra(char codigo){ //Template metodo, ele faz partes aqui nesta classe, mas outras partes do metodo sao realizadas nas classes abstratas abaixo.
        if(codigo < 'a' || codigo > 'z'){
			throw new IllegalArgumentException("Caracter inválido");
		}
        int posicao = codigo - 'a';
        Letra letra = this.pool[posicao];
        if (letra == null){ //Verificando se o codigo recebido ja existe na pull, se não utiliza o metodo criarLetra.
            letra = this.criarLetra(codigo);
            this.pool[posicao] = letra;
        }
        return letra;
    }

    protected abstract Letra criarLetra(char codigo);

    public final Letra getLetraEncoberta(){
        if(encoberta == null){
            char codigo = '*';
            this.encoberta = criarLetra(codigo);
        }
        return this.encoberta;
    }
    
}
