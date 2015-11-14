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

    private static final String selectAll = "select cargo.idcargo,cargo.nome,salario.nivel,salario.valor from cargo,salario where cargo.idcargo = salario.idcargo";
    private static final String select = "select cargo.idcargo,cargo.nome,salario.nivel,salario.valor from cargo c,salario s where c.idcargo = s.idcargo and c.idcargo = ? s.nivel = ?";
    private static final String update = "UPDATE salario SET valor = ? WHERE idCargo = ? and nivel = ?";

    private static int getID(PreparedStatement stm) throws SQLException {
        ResultSet resultado = stm.getGeneratedKeys();
        resultado.next();
        return resultado.getInt(1);
    }

    public static void update(Cargo cargo) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(update, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setDouble(1, cargo.getSalario());
            statment.setInt(2, cargo.getId());
            statment.setInt(3, cargo.getNivel());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao atualizar um cargo no banco de dados. =" + ex.getMessage()
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

    public static List<Cargo> carregarCargos() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cargo> list = new ArrayList();
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setNome(rs.getString("nome"));
                cargo.setId(rs.getInt("idCargo"));
                cargo.setNivel(rs.getInt("nivel"));
                cargo.setSalario(rs.getDouble("valor"));
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

    public static void carregarCargo(Cargo cargo) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(select);
            ps.setInt(1, cargo.getId());
            ps.setInt(2, cargo.getNivel());
            rs = ps.executeQuery();
            cargo.setNome(rs.getString("nome"));
            cargo.setId(rs.getInt("idCargo"));
            cargo.setNivel(rs.getInt("nivel"));
            cargo.setSalario(rs.getDouble("valor"));
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
