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
    private double salario;
    private int nivel;

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Cargo(int idCargo, String nomeCargo, String salario) {
        this.id = idCargo;
        this.nome = nomeCargo;
        this.nome = salario;
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
