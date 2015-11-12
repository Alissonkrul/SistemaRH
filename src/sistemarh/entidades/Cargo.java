/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.entidades;

/**
 *
 * @author Alisson
 */
public class Cargo {
    private int id;
    private String nome;

    public Cargo(int idCargo, String nomeCargo) {
        this.id = idCargo;
        this.nome = nomeCargo;
    }

    public Cargo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int idCargo) {
        this.id = idCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomeCargo) {
        this.nome = nomeCargo;
    }
    
}
