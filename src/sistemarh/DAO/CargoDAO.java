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
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemarh.entidades.Cargo;
import sistemarh.utils.ConnectionFactory;

/**
 *
 * @author Bruno Henrique
 */
public class CargoDAO {

    public static void add(Cargo cargo) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO cargo (idcargo, nome, salario, nivel) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, cargo.getId());
            ps.setString(2, cargo.getNome());
            ps.setDouble(3, cargo.getSalario());
            ps.setInt(4, cargo.getNivel());

            ps.executeUpdate();
            cargo.setId(getID(ps));
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static int getID(PreparedStatement stm) throws SQLException {
        ResultSet resultado = stm.getGeneratedKeys();
        resultado.next();
        return resultado.getInt(1);
    }

    public static List<Cargo> carregarCargos() {
        String sql = "SELECT * FROM cargo";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cargo> list = new ArrayList();
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setNome(rs.getString("nome"));
                cargo.setId(rs.getInt("idCargo"));
                list.add(cargo);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de cargo. Origem=" + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar result set. Ex=" + ex.getMessage());
            };
            try {
                ps.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                connection.close();;
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
    }
}
