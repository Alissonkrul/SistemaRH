/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.entidades;

import java.util.List;

/**
 *
 * @author Alisson
 */
public class Programador extends Funcionario{
    
    public Programador(String cpf, String senha, String nome, String sobrenome, String rg, String telefone, Cargo cargo, Departamento departamento, int id, List<Sistema> sistemas, int nivel) {
        super(cpf, senha, nome, sobrenome, rg, telefone, cargo, departamento, id, sistemas);
        this.setCargo(new Cargo(4,nivel));
        this.getCargo().carregar();
    }

    public Programador() {
    }

    
    
}
