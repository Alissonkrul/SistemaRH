/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemarh.entidades.Cargo;
import sistemarh.entidades.Departamento;
import sistemarh.entidades.Funcionario;
import sistemarh.entidades.Salario;
import sistemarh.utils.ConnectionFactory;

/**
 *
 * @author Bruno Henrique
 */
public class FuncionarioDAO {    

    public void add(Funcionario funcionario) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO funcionario (idFuncionario, telefone, cpf, nome, rg, senha, sobrenome, idcargo, nivel, iddepartamento) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, funcionario.getId());
            ps.setString(2, funcionario.getTelefone());
            ps.setString(3, funcionario.getCpf());
            ps.setString(4, funcionario.getNome());
            ps.setString(5, funcionario.getRg());
            ps.setString(6, funcionario.getSenha());
            ps.setString(7, funcionario.getSobrenome());
            ps.setInt(8, funcionario.getSalario().getCargo().getId());
            ps.setInt(9, funcionario.getSalario().getNivel());
            Departamento dep = funcionario.getDepartamento();
            ps.setInt(10, dep.getId());
            
            ps.executeUpdate();
            funcionario.setId(getID(ps));
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static int getID(PreparedStatement stm) throws SQLException {
        ResultSet resultado = stm.getGeneratedKeys();
        resultado.next();
        return resultado.getInt(1);
    }

}
