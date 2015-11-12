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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemarh.entidades.Sistema;
import sistemarh.utils.ConnectionFactory;

/**
 *
 * @author Alisson
 */
public class SistemaDAO {

    private static final String insert = "INSERT INTO sistema(nome) VALUES(?)";
    private static final String selectAll = "SELECT * FROM sistema";
    private static final String deleteSisDeFuncionario = "DELETE FROM sistema WHERE idSistema = ?";
    private static final String delete = "DELETE FROM sistema WHERE idSistema = ?";
    private static final String update = "UPDATE sistema SET nome=? WHERE idSistema = ?";

    public static void add(Sistema sistema) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setString(1, sistema.getNome());
            statment.executeUpdate();
            sistema.setId(getID(statment));

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao inserir um sistema no banco de dados. =" + ex.getMessage()
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
   public static void update(Sistema sistema) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(update, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setInt(2, sistema.getId());
            statment.setString(1, sistema.getNome());
            statment.executeUpdate();
           
        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao atualizar um sistema no banco de dados. =" + ex.getMessage()
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

    public static void delete(Sistema sistema) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(deleteSisDeFuncionario, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setInt(1, sistema.getId());
            statment.executeUpdate();

            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(delete, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setInt(1, sistema.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao excluir um sistema no banco de dados. =" + ex.getMessage()
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

    private static int getID(PreparedStatement stm) throws SQLException {
        ResultSet resultado = stm.getGeneratedKeys();
        resultado.next();
        return resultado.getInt(1);
    }

    public static List<Sistema> carregarSistemas() {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<Sistema> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(selectAll);
            resultSet = statment.executeQuery();
            while (resultSet.next()) {
                Sistema sistema = new Sistema();
                sistema.setNome(resultSet.getString("nome"));
                sistema.setId(resultSet.getInt("idSistema"));
                list.add(sistema);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de sistemas. Origem=" + ex.getMessage());
        } finally {
            try {
                resultSet.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar result set. Ex=" + ex.getMessage());
            };
            try {
                statment.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();;
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }

}
