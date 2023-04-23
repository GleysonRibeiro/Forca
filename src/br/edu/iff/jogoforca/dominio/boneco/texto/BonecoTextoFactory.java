package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;

public class BonecoTextoFactory implements BonecoFactory{

    private static BonecoTextoFactory soleInstance = null; // Como deve haver apenas uma instancia singleton, a variavel deve ser inicializada como nula.

    private BonecoTextoFactory() {}

    public static BonecoTextoFactory getSoleInstance(){ 
        if (soleInstance == null){ // Se ainda estiver nula, cria uma nova soleInstance, senão retorna a que já existe.
            soleInstance = new BonecoTextoFactory();
        }
        return soleInstance;
    }

    @Override
    public Boneco getBoneco(){
        return BonecoTexto.getSoleInstance();
    }

}
