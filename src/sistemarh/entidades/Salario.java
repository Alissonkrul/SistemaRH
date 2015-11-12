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
public class Salario {
    private String id;
    private Cargo cargo;
    private int nivel;
    private double valor;

    public Salario(String id, Cargo cargo, int nivel, double valor) {
        this.id = id;
        this.cargo = cargo;
        this.nivel = nivel;
        this.valor = valor;
    }

    public Cargo getCargo() {
        return cargo;
    }
    
    public String getId() {
        return id;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
