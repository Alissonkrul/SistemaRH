/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.entidades;

import java.util.List;
import sistemarh.DAO.DepartamentoDAO;

/**
 *
 * @author Alisson
 */
public class Gerente extends Funcionario{
    private Departamento departamentoGerenciado;

    public Gerente(String cpf, String senha, String nome, String sobrenome, String rg, String telefone, Cargo cargo, Departamento departamento, int id, List<Sistema> sitemas, int nivel) {
        super(cpf, senha, nome, sobrenome, rg, telefone, cargo, departamento, id, sitemas);
        this.setCargo(new Cargo(2,nivel));
        this.getCargo().carregar();
    }

    public Gerente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Departamento getDepartamentoGerenciado() {
        return departamentoGerenciado;
    }
    
    public void carregarDepartamentos(){
        List<Departamento> list = DepartamentoDAO.carregarDepartamento(this);
        if(!list.isEmpty())
        departamentoGerenciado = list.get(0);
    }
    
    public void setDepartamentoGerenciado(Departamento departamentoGerenciado) {
        this.departamentoGerenciado = departamentoGerenciado;
    }
}
