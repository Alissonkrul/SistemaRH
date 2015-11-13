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
public abstract class Funcionario extends Pessoa implements Autenticavel {

    private String telefone;
    private String senha;
    private Cargo cargo;
    private Departamento departamento;

    private int id;
    private List<Sistema> sitemas[];

    public Funcionario(String cpf, String senha, String nome, String sobrenome, String rg, String telefone, Cargo cargo, Departamento departamento, int id, List<Sistema>[] sitemas) {
        super(nome, sobrenome, rg, cpf);
        this.senha = senha;
        this.cargo = cargo;
        this.telefone = telefone;
        this.departamento = departamento;
        this.id = id;
        this.sitemas = sitemas;
    }
    
    public Funcionario() {
        
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    } 

    public List<Sistema>[] getSitemas() {
        return sitemas;
    }

    public void setSitemas(List<Sistema>[] sitemas) {
        this.sitemas = sitemas;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


}
