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
public class Diretor extends Funcionario {

    private List<Departamento> departamentosDirigidos;

    public Diretor(String cpf, String senha, String nome, String sobrenome, String rg, String telefone, Cargo cargo, Departamento departamento, int id, List<Sistema> sitemas,int nivel) {
        super(cpf, senha, nome, sobrenome, rg, telefone, cargo, departamento, id, sitemas);
        this.setCargo(new Cargo(1, nivel));
        this.getCargo().carregar();
    }

    public Diretor() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Departamento> getDepartamentosDirigidos() {
        return departamentosDirigidos;
    }

    public void setDepartamentosDirigidos(List<Departamento> departamentosDirigidos) {
        this.departamentosDirigidos = departamentosDirigidos;
    }

    @Override
    public boolean autentica(String nomeSistema, String usuario, String senha) {
         if (usuario.equals(this.getCpf()) && senha.equals(getSenha())) {
             return true;
         }     
     return false;
    }
}
