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
import sistemarh.entidades.Analista;
import sistemarh.entidades.AuxLimpeza;
import sistemarh.entidades.Cargo;
import sistemarh.entidades.Departamento;
import sistemarh.entidades.Diretor;
import sistemarh.entidades.Funcionario;
import sistemarh.entidades.Gerente;
import sistemarh.entidades.Programador;
import sistemarh.utils.ConnectionFactory;

/**
 *
 * @author Bruno Henrique
 */
public class FuncionarioDAO {

    private static final String selectAll = "SELECT * FROM FUNCIONARIO";
    private static final String select = "SELECT * FROM FUNCIONARIO WHERE idfuncionario = ?";
    private static final String procurarLogin = "SELECT * FROM funcionario WHERE nome = ? and senha = ?";

    public static Funcionario procurarLogin(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(procurarLogin);

            ps.setString(1, funcionario.getNome());
            ps.setString(1, funcionario.getSenha());
            rs = ps.executeQuery();

            String nome = funcionario.getCargo().getNome();
            Funcionario funcionario2 = null;

            switch (nome) {
                case "Diretor":
                    funcionario2 = new Diretor();
                    funcionario2.setId(rs.getInt("idfuncionario"));
                    funcionario2.setNome(rs.getString("nome"));
                    return funcionario2;

                case "Analista":
                    funcionario2 = new Analista();
                    funcionario2.setId(rs.getInt("idfuncionario"));
                    funcionario2.setNome(rs.getString("nome"));
                    return funcionario2;

                case "Gerente":
                    funcionario2 = new Gerente();
                    funcionario2.setId(rs.getInt("idfuncionario"));
                    funcionario2.setNome(rs.getString("nome"));
                    return funcionario2;

                case "Programador":
                    funcionario2 = new Programador();
                    funcionario2.setId(rs.getInt("idfuncionario"));
                    funcionario2.setNome(rs.getString("nome"));

                case "Auxiliar de Limpeza":
                    funcionario2 = new AuxLimpeza();
                    funcionario2.setId(rs.getInt("idfuncionario"));
                    funcionario2.setNome(rs.getString("nome"));
                    return funcionario2;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                con.close();;
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
        return null;
    }

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
            ps.setInt(8, funcionario.getCargo().getId());
            ps.setInt(9, funcionario.getCargo().getNivel());
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

    public static void carregarFuncionario(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<Funcionario> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(select);
            statment.setInt(1, funcionario.getId());
            // Dados da tabela de funcionarios
            resultSet = statment.executeQuery();
            resultSet.next();
            funcionario.setId(resultSet.getInt("idFuncionario"));
            funcionario.setTelefone(resultSet.getString("telefone"));
            funcionario.setCpf(resultSet.getString("cpf"));
            funcionario.setNome(resultSet.getString("nome"));
            funcionario.setRg(resultSet.getString("RG"));
            funcionario.setSenha(resultSet.getString("senha"));
            funcionario.setSobrenome(resultSet.getString("sobrenome"));
            funcionario.getCargo().setId(resultSet.getInt("idcargo"));
            funcionario.getCargo().setNivel(resultSet.getInt("nivel"));
            funcionario.getDepartamento().setId(resultSet.getInt("idDepartamento"));

            funcionario.getDepartamento().carregar();

            funcionario.getCargo().carregar();

            funcionario.carregarSistemas();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de Funcionarios. Origem=" + ex.getMessage());
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

    public static List<Funcionario> carregarFuncionarios() {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<Funcionario> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(selectAll);
            // Dados da tabela de funcionarios
            resultSet = statment.executeQuery();
            while (resultSet.next()) {
                Funcionario funcionario;
                funcionario = especializaFuncionario(resultSet.getInt("idCargo"));
                funcionario.setId(resultSet.getInt("idFuncionario"));
                funcionario.setTelefone(resultSet.getString("telefone"));
                funcionario.setCpf(resultSet.getString("cpf"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setRg(resultSet.getString("RG"));
                funcionario.setSenha(resultSet.getString("senha"));
                funcionario.setSobrenome(resultSet.getString("sobrenome"));
                funcionario.getCargo().setId(resultSet.getInt("idcargo"));
                funcionario.getCargo().setNivel(resultSet.getInt("nivel"));
                funcionario.getDepartamento().setId(resultSet.getInt("idDepartamento"));
                
                funcionario.getDepartamento().carregar();
                funcionario.getCargo().carregar();
                funcionario.carregarSistemas();
                list.add(funcionario);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de Funcionarios. Origem=" + ex.getMessage());
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

    public static Funcionario especializaFuncionario(int cargo) {
        switch (cargo) {
            case 1:
                return new Diretor();
            case 2:
                return new Gerente();
            case 3:
                return new Analista();
            case 4:
                return new Programador();
            case 5:
                return new AuxLimpeza();

        }
        return new Funcionario();
    };

    private static int getID(PreparedStatement stm) throws SQLException {
        ResultSet resultado = stm.getGeneratedKeys();
        resultado.next();
        return resultado.getInt(1);
    }

}
