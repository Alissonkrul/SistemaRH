/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemarh.entidades.Funcionario;
import sistemarh.entidades.Sistema;
import sistemarh.utils.ConnectionFactory;

/**
 *
 * @author Bruno Henrique
 */
public class PermissaoDAO {
    private static String givePermission = "INSERT INTO temacesso (idFuncionario, idSistema) VALUES (?,?)";
    private static final String delete = "DELETE FROM temacesso WHERE idfuncionario = ? and idsistema = ?";
    
    public static void delete(Sistema sistema, Funcionario funcionario) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(delete, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setInt(1, funcionario.getId());
            statment.setInt(2, sistema.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao excluir um departamento no banco de dados. =" + ex.getMessage()
            );

        } finally {

            try {
                statment.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };

            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }
    
    public static boolean darPermissao(Funcionario f, Sistema s) {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(givePermission);
            ps.setInt(1, f.getId());
            ps.setInt(2, s.getId());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {

            try {
                ps.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };

            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
    }
}
