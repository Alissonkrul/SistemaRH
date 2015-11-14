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
public class Funcionario extends Pessoa implements Autenticavel {

    private String telefone;
    private String senha;
    private Cargo cargo;
    private Departamento departamento;

    private int id;
    private List<Sistema> sistemas;

    public Funcionario(String cpf, String senha, String nome, String sobrenome, String rg, String telefone, Cargo cargo, Departamento departamento, int id, List<Sistema> sistemas) {
        super(nome, sobrenome, rg, cpf);
        this.senha = senha;
        this.cargo = cargo;
        this.telefone = telefone;
        this.departamento = departamento;
        this.id = id;
        this.sistemas = sistemas;
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

    public List<Sistema> getSitemas() {
        return sistemas;
    }

    private boolean procuraSistema(String nomeSistema) {
        for (Sistema sistema : sistemas) {
            if(sistema.getNome().equalsIgnoreCase(nomeSistema))
                return true;
        }
        return false;
    }

    public void setSitemas(List<Sistema> sistemas) {
        this.sistemas = sistemas;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean autentica(String nomeSistema, String usuario, String senha) {
        if (usuario.equals(this.getCpf()) && senha.equals(this.senha)) {
            if(procuraSistema(nomeSistema))
                return true;
        }
        return false;
    }
}
