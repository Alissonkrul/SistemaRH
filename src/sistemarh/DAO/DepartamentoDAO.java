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
import javax.swing.JOptionPane;
import sistemarh.entidades.Departamento;
import sistemarh.entidades.Funcionario;
import sistemarh.utils.ConnectionFactory;

/**
 *
 * @author Alisson
 */
public class DepartamentoDAO {

    private static final String insert = "INSERT INTO departamento(nome) VALUES(?)";
    private static final String selectAll = "SELECT * FROM departamento";
    private static final String select = "SELECT * FROM departamento where idDepartamento = ?";
    private static final String delete = "DELETE FROM departamento WHERE idDepartamento = ?";
    private static final String deleteDirDeFuncionario = "DELETE FROM dirige WHERE idDepartamento = ?";
    private static final String deleteGerDeFuncionario = "DELETE FROM gerencia WHERE idDepartamento = ?";
    private static final String selectGerDeFuncionario = "SELECT d.idDepartamento, d.nome FROM gerencia tb, departamento d WHERE tb.idDepartamento = d.idDepartamento and tb.idfuncionario = ?";
    private static final String selectDirDeFuncionario = "SELECT d.idDepartamento, d.nome FROM dirige tb, departamento d WHERE tb.idDepartamento = d.idDepartamento and tb.idfuncionario = ?";
    private static final String update = "UPDATE departamento SET nome=? WHERE idDepartamento = ?";
    private static final String funcionario = "SELECT * FROM funcionario WHERE idDepartamento = ?";
    
    public static boolean funcionario(Departamento departamento) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(funcionario);
            
            ps.setInt(1, departamento.getId());
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
                if(rs.getString("Nome").equalsIgnoreCase("")) {
                    return false;
                }
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return false;
    }

    public static void add(Departamento departamento) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setString(1, departamento.getNome());
            statment.executeUpdate();
            departamento.setId(getID(statment));

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao inserir um departamento no banco de dados. =" + ex.getMessage()
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

    public static void update(Departamento departamento) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(update, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setInt(2, departamento.getId());
            statment.setString(1, departamento.getNome());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao atualizar um departamento no banco de dados. =" + ex.getMessage()
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

    public static void delete(Departamento departamento) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(deleteDirDeFuncionario, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setInt(1, departamento.getId());
            statment.executeUpdate();

            statment = con.prepareStatement(deleteGerDeFuncionario, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setInt(1, departamento.getId());
            statment.executeUpdate();

            statment = con.prepareStatement(delete, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setInt(1, departamento.getId());
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

    private static int getID(PreparedStatement stm) throws SQLException {
        ResultSet resultado = stm.getGeneratedKeys();
        resultado.next();
        return resultado.getInt(1);
    }

    public static List<Departamento> carregarDepartamentos() {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<Departamento> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(selectAll);
            resultSet = statment.executeQuery();
            while (resultSet.next()) {
                Departamento departamento = new Departamento();
                departamento.setNome(resultSet.getString("nome"));
                departamento.setId(resultSet.getInt("idDepartamento"));
                list.add(departamento);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de departamentos. Origem=" + ex.getMessage());
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

    public static void carregarDepartamento(Departamento departamento) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<Departamento> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(select);
            statment.setInt(1, departamento.getId());
            resultSet = statment.executeQuery();
            resultSet.next();
            departamento.setNome(resultSet.getString("nome"));
            departamento.setId(resultSet.getInt("idDepartamento"));

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de departamentos. Origem=" + ex.getMessage());
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

    public static List<Departamento> carregarDepartamento(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<Departamento> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            if (funcionario.getCargo().getId() == 1) {
                statment = con.prepareStatement(selectDirDeFuncionario);
            } else {
                statment = con.prepareStatement(selectGerDeFuncionario);
            }
            statment.setInt(1, funcionario.getId());
            resultSet = statment.executeQuery();

            while (resultSet.next()) {
                Departamento departamento = new Departamento();
                departamento.setNome(resultSet.getString("nome"));
                departamento.setId(resultSet.getInt("idDepartamento"));
                list.add(departamento);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de departamentos. Origem=" + ex.getMessage());
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
