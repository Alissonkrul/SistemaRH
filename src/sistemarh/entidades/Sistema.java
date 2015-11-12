/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.entidades;

import sistemarh.DAO.SistemaDAO;

/**
 *
 * @author Alisson
 */
public class Sistema {

    private String nome;
    private int id;

    public Sistema(String nome) {
        this.nome = nome;
    }

    public Sistema() {
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
         SistemaDAO.update(this);
    }

    public void add() {
        SistemaDAO.add(this);
    }

    public void delete() {
        SistemaDAO.delete(this);
    }

}
