/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.utils;

import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author Bruno Henrique
 */
public class Validação {
    
    public static boolean verificaCampos(List<JTextField> campos) {
        for(JTextField campo:campos) {
            if(campo.getText().equals("")){
                return false;
            }
        }
        return true;
    }
    
    public static boolean validarCpf(String cpf) {
        if (cpf == null) {
            return false;
        } else {
            String cpfGerado = "";
            cpf = cpf.replaceAll("[.|-]", "");
            if (verificarSeTamanhoInvalido(cpf)) {
                return false;
            }
            cpfGerado = cpf.substring(0, 9);
            cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));
            cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));

            if (!cpfGerado.equals(cpf)) {
                return false;
            }
        }
        return true;
    }
   
    
    private static boolean verificarSeTamanhoInvalido(String cpf) {
        if (cpf.length() != 11) {
            return true;
        }
        return false;
    }
    
    private static String calculoComCpf(String cpf) {
        int digGerado = 0;
        int mult = cpf.length() + 1;
        char[] charCpf = cpf.toCharArray();
        for (int i = 0; i < cpf.length(); i++) {
            digGerado += (charCpf[i] - 48) * mult--;
        }
        if (digGerado % 11 < 2) {
            digGerado = 0;
        } else {
            digGerado = 11 - digGerado % 11;
        }
        return String.valueOf(digGerado);
    }
    
}
