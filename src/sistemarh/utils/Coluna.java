/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.utils;

/**
 *
 * @author Alisson
 */
class Coluna {
    private String nome;
    private Class classe;

    public Coluna(String nome, Class classe) {
        this.nome = nome;
        this.classe = classe;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Object getObjeto() {
        return classe;
    }

    public void setObjeto(Class classe) {
        this.classe = classe;
    }
    
    
}
