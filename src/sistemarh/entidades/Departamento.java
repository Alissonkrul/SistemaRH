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
public class Departamento {

    private int id;
    private String nome;

    public Departamento(String nome) {
        this.nome = nome;
    }

    public Departamento() {
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void update() {
        DepartamentoDAO.update(this);
    }

    public void add() {
        DepartamentoDAO.add(this);
    }

    public void delete() {
        DepartamentoDAO.delete(this);
    }
}
