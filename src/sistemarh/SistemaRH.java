/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh;

import sistemarh.entidades.Funcionario;
import sistemarh.intefaces.MenuPrincipal;

/**
 *
 * @author Alisson
 */
public class SistemaRH {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
        Funcionario f = new Funcionario();
        f.setId(2);
        f.carregar();
        String toString = f.toString();
    }
    
    
}
