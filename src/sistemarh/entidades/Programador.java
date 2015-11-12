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
public class Programador extends Funcionario{

    public Programador(String cpf, String senha, String nome, String sobrenome, String rg, String telefone, Salario salario, Departamento departamento, int id, List<Sistema>[] sitemas) {
        super(cpf, senha, nome, sobrenome, rg, telefone, salario, departamento, id, sitemas);
    }

    @Override
    public boolean autentica(String nomeSistema, String usuario, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
