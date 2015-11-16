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
public class AuxLimpeza extends Funcionario {

    public AuxLimpeza(String cpf, String senha, String nome, String sobrenome, String rg, String telefone, Cargo cargo, Departamento departamento, int id, List<Sistema> sitemas, int nivel) {
        super(cpf, senha, nome, sobrenome, rg, telefone, cargo, departamento, id, sitemas);
        this.setCargo(new Cargo(5, nivel));
        this.getCargo().carregar();
    }

    public AuxLimpeza() {
    }

    @Override
    public boolean autentica(String nomeSistema, String usuario, String senha) {
        return false;
    }

}
