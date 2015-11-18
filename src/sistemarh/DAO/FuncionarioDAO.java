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
import sistemarh.entidades.Sistema;
import sistemarh.utils.ConnectionFactory;

/**
 *
 * @author Bruno Henrique
 */
public class FuncionarioDAO {

    private static final String selectAll = "SELECT * FROM FUNCIONARIO";
    private static final String selectFuncionariosDep = "SELECT * FROM FUNCIONARIO where iddepartamento = ?";
    private static final String select = "SELECT * FROM FUNCIONARIO WHERE idfuncionario = ?";
    private static final String add = "INSERT INTO funcionario ( telefone, cpf, nome, rg, senha, sobrenome, idcargo, nivel, iddepartamento) VALUES (?,?,?,?,?,?,?,?,?) ";
    private static final String addGerecia = "INSERT INTO gerencia ( idFuncionario, iddepartamento) VALUES (?,?) ";
    private static final String addDirige = "INSERT INTO dirige ( idFuncionario, iddepartamento) VALUES (?,?) ";
    private static final String update = "update funcionario SET Telefone = ? ,CPF = ?,Nome = ?,RG = ?,Senha = ?,Sobrenome = ?,idCargo = ?,Nivel = ?,idDepartamento = ? where idfuncionario = ?";
    private static final String procurarLogin = "SELECT * FROM funcionario WHERE cpf = ? and senha = ?";
    private static final String selectByCpf = "SELECT * FROM funcionario WHERE cpf LIKE ?";
    private static final String addTemAcesso = "INSERT temacesso(idfuncionario,idsistema) values(?,?) ";
    private static final String deleteFuncionarioDeSis = "DELETE FROM temAcesso WHERE idFuncionario = ?";
    private static final String deleteFuncionarioDeGer = "DELETE FROM gerencia WHERE idFuncionario = ?";
    private static final String deleteFuncionarioDeDir = "DELETE FROM dirige WHERE idFuncionario = ?";
    private static final String deleteSisDeFuncionario = "DELETE FROM temAcesso WHERE idFuncionario = ?";
    private static final String selectByRg = "SELECT * FROM funcionario WHERE rg LIKE ?";
    private static final String selectByName = "SELECT * FROM funcionario WHERE nome LIKE ?";
    private static final String selectByLastName = "SELECT * FROM funcionario WHERE sobrenome LIKE ?";
    private static final String selectByCargo = "SELECT * FROM funcionario WHERE idcargo = ?";
    private static final String delete = "DELETE FROM funcionario WHERE idFuncionario = ?";
    
    
    public static void delete(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement statment = null;
        deleteSisDeFuncionario(funcionario);
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(delete);
            statment.setInt(1, funcionario.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao excluir um funcionario no banco de dados. =" + ex.getMessage()
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
    public static void deleteSisDeFuncionario(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(deleteSisDeFuncionario);
            statment.setInt(1, funcionario.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao excluir uma associação de Sistema com funcionario no banco de dados. =" + ex.getMessage()
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
    
     public static void deleteDir(Diretor diretor) {
        deleteDirigeFuncionario((Funcionario)diretor);
        delete((Funcionario)diretor);
     }     
     public static void deleteGer(Gerente gerente) {
        deleteGerenciaFuncionario((Funcionario)gerente);
        delete((Funcionario)gerente);
     }  
    
    
    public static List<Funcionario> carregaPorCargo(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(selectByCargo);
            
            String selectCargo = "SELECT idcargo FROM cargo WHERE nome LIKE ?";
            Cargo cargo = funcionario.getCargo();
            cargo.setNome(cargo.getNome() + "%");
            
            PreparedStatement ps1 = con.prepareStatement(selectCargo);
            ps1.setString(1, cargo.getNome());
            ResultSet rs1 = ps1.executeQuery();
            
            while(rs1.next()){
                cargo.setId(rs1.getInt("idCargo"));
            }
            
            ps.setInt(1, cargo.getId());

            rs = ps.executeQuery();

            List<Funcionario> list = new ArrayList();
            while (rs.next()) {
                Funcionario funcionario2;
                funcionario2 = especializaFuncionario(rs.getInt("idCargo"));
                funcionario2.setId(rs.getInt("idFuncionario"));
                funcionario2.setTelefone(rs.getString("telefone"));
                funcionario2.setCpf(rs.getString("cpf"));
                funcionario2.setNome(rs.getString("nome"));
                funcionario2.setRg(rs.getString("RG"));
                funcionario2.setSenha(rs.getString("senha"));
                funcionario2.setSobrenome(rs.getString("sobrenome"));
                funcionario2.getCargo().setId(rs.getInt("idcargo"));
                funcionario2.getCargo().setNivel(rs.getInt("nivel"));
                funcionario2.getDepartamento().setId(rs.getInt("idDepartamento"));

                funcionario2.getDepartamento().carregar();
                funcionario2.getCargo().carregar();
                funcionario2.carregarSistemas();
                funcionario2.carregarDepartamentos();

                list.add(funcionario2);
            }
            return list;
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
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
        return null;
    }
    
    public static List<Funcionario> carregaPorSobrenome(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(selectByLastName);
            funcionario.setSobrenome(funcionario.getSobrenome() + "%");
            ps.setString(1, funcionario.getSobrenome());

            rs = ps.executeQuery();

            List<Funcionario> list = new ArrayList();
            while (rs.next()) {
                Funcionario funcionario2;
                funcionario2 = especializaFuncionario(rs.getInt("idCargo"));
                funcionario2.setId(rs.getInt("idFuncionario"));
                funcionario2.setTelefone(rs.getString("telefone"));
                funcionario2.setCpf(rs.getString("cpf"));
                funcionario2.setNome(rs.getString("nome"));
                funcionario2.setRg(rs.getString("RG"));
                funcionario2.setSenha(rs.getString("senha"));
                funcionario2.setSobrenome(rs.getString("sobrenome"));
                funcionario2.getCargo().setId(rs.getInt("idcargo"));
                funcionario2.getCargo().setNivel(rs.getInt("nivel"));
                funcionario2.getDepartamento().setId(rs.getInt("idDepartamento"));

                funcionario2.getDepartamento().carregar();
                funcionario2.getCargo().carregar();
                funcionario2.carregarSistemas();
                funcionario2.carregarDepartamentos();

                list.add(funcionario2);
            }
            return list;
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
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
        return null;
    }
    
    public static List<Funcionario> carregaPorNome(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(selectByName);
            funcionario.setNome(funcionario.getNome() + "%");
            ps.setString(1, funcionario.getNome());

            rs = ps.executeQuery();

            List<Funcionario> list = new ArrayList();
            while (rs.next()) {
                Funcionario funcionario2;
                funcionario2 = especializaFuncionario(rs.getInt("idCargo"));
                funcionario2.setId(rs.getInt("idFuncionario"));
                funcionario2.setTelefone(rs.getString("telefone"));
                funcionario2.setCpf(rs.getString("cpf"));
                funcionario2.setNome(rs.getString("nome"));
                funcionario2.setRg(rs.getString("RG"));
                funcionario2.setSenha(rs.getString("senha"));
                funcionario2.setSobrenome(rs.getString("sobrenome"));
                funcionario2.getCargo().setId(rs.getInt("idcargo"));
                funcionario2.getCargo().setNivel(rs.getInt("nivel"));
                funcionario2.getDepartamento().setId(rs.getInt("idDepartamento"));

                funcionario2.getDepartamento().carregar();
                funcionario2.getCargo().carregar();
                funcionario2.carregarSistemas();
                funcionario2.carregarDepartamentos();

                list.add(funcionario2);
            }
            return list;
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
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
        return null;
    }

    public static List<Funcionario> carregaPorCpf(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(selectByCpf);
            funcionario.setCpf(funcionario.getCpf() + "%");
            ps.setString(1, funcionario.getCpf());

            rs = ps.executeQuery();

            List<Funcionario> list = new ArrayList();
            while (rs.next()) {
                Funcionario funcionario2;
                funcionario2 = especializaFuncionario(rs.getInt("idCargo"));
                funcionario2.setId(rs.getInt("idFuncionario"));
                funcionario2.setTelefone(rs.getString("telefone"));
                funcionario2.setCpf(rs.getString("cpf"));
                funcionario2.setNome(rs.getString("nome"));
                funcionario2.setRg(rs.getString("RG"));
                funcionario2.setSenha(rs.getString("senha"));
                funcionario2.setSobrenome(rs.getString("sobrenome"));
                funcionario2.getCargo().setId(rs.getInt("idcargo"));
                funcionario2.getCargo().setNivel(rs.getInt("nivel"));
                funcionario2.getDepartamento().setId(rs.getInt("idDepartamento"));

                funcionario2.getDepartamento().carregar();
                funcionario2.getCargo().carregar();
                funcionario2.carregarSistemas();
                funcionario2.carregarDepartamentos();

                list.add(funcionario2);
            }
            return list;
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
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
        return null;
    }
    
    public static List<Funcionario> carregaPorRg(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(selectByRg);
            funcionario.setRg(funcionario.getRg() + "%");
            ps.setString(1, funcionario.getRg());

            rs = ps.executeQuery();

            List<Funcionario> list = new ArrayList();
            while (rs.next()) {
                Funcionario funcionario2;
                funcionario2 = especializaFuncionario(rs.getInt("idCargo"));
                funcionario2.setId(rs.getInt("idFuncionario"));
                funcionario2.setTelefone(rs.getString("telefone"));
                funcionario2.setCpf(rs.getString("cpf"));
                funcionario2.setNome(rs.getString("nome"));
                funcionario2.setRg(rs.getString("RG"));
                funcionario2.setSenha(rs.getString("senha"));
                funcionario2.setSobrenome(rs.getString("sobrenome"));
                funcionario2.getCargo().setId(rs.getInt("idcargo"));
                funcionario2.getCargo().setNivel(rs.getInt("nivel"));
                funcionario2.getDepartamento().setId(rs.getInt("idDepartamento"));

                funcionario2.getDepartamento().carregar();
                funcionario2.getCargo().carregar();
                funcionario2.carregarSistemas();
                funcionario2.carregarDepartamentos();

                list.add(funcionario2);
            }
            return list;
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
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
        return null;
    }

    public static Funcionario procurarLogin(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(procurarLogin);

            ps.setString(1, funcionario.getCpf());
            ps.setString(2, funcionario.getSenha());
            rs = ps.executeQuery();

            if (rs.next()) {
                Funcionario funcionario2;
                funcionario2 = especializaFuncionario(rs.getInt("idCargo"));
                funcionario2.setId(rs.getInt("idFuncionario"));
                funcionario2.setTelefone(rs.getString("telefone"));
                funcionario2.setCpf(rs.getString("cpf"));
                funcionario2.setNome(rs.getString("nome"));
                funcionario2.setRg(rs.getString("RG"));
                funcionario2.setSenha(rs.getString("senha"));
                funcionario2.setSobrenome(rs.getString("sobrenome"));
                funcionario2.getCargo().setId(rs.getInt("idcargo"));
                funcionario2.getCargo().setNivel(rs.getInt("nivel"));
                funcionario2.getDepartamento().setId(rs.getInt("idDepartamento"));

                funcionario2.getDepartamento().carregar();
                funcionario2.getCargo().carregar();
                funcionario2.carregarSistemas();
                funcionario2.carregarDepartamentos();

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

    //TODO FIXME
    public static void add(Funcionario funcionario) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(add, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, funcionario.getTelefone());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getNome());
            ps.setString(4, funcionario.getRg());
            ps.setString(5, funcionario.getSenha());
            ps.setString(6, funcionario.getSobrenome());
            ps.setInt(7, funcionario.getCargo().getId());
            ps.setInt(8, funcionario.getCargo().getNivel());
            ps.setInt(9, funcionario.getDepartamento().getId());

            ps.executeUpdate();
            funcionario.setId(getID(ps));
        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao inserir um Funcinario no banco de dados. =" + ex.getMessage()
            );

        } finally {

            try {
                ps.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };

            try {
                connection.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }

    public static void update(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(update, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setInt(10, funcionario.getId());
            statment.setString(1, funcionario.getTelefone());
            statment.setString(2, funcionario.getCpf());
            statment.setString(3, funcionario.getNome());
            statment.setString(4, funcionario.getRg());
            statment.setString(5, funcionario.getSenha());
            statment.setString(6, funcionario.getSobrenome());
            statment.setInt(7, funcionario.getCargo().getId());
            statment.setInt(8, funcionario.getCargo().getNivel());
            statment.setInt(9, funcionario.getDepartamento().getId());
            statment.executeUpdate();

            uptateTemAcesso(funcionario);

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
            throw new RuntimeException("Erro ao consultar um Funcionarios. Origem=" + ex.getMessage());
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
                funcionario.carregarDepartamentos();
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
    }

    ;

    private static int getID(PreparedStatement stm) throws SQLException {
        ResultSet resultado = stm.getGeneratedKeys();
        resultado.next();
        return resultado.getInt(1);
    }

    public static void addGer(Gerente gerente) {
        FuncionarioDAO.add((Funcionario) gerente);
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(addGerecia);
            statment.setInt(1, gerente.getId());
            statment.setInt(2, gerente.getDepartamentoGerenciado().getId());
            statment.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao inserir uma Associação de Gerente no banco de dados. =" + ex.getMessage()
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

    public static void addDir(Diretor diretor) {
        FuncionarioDAO.add((Funcionario) diretor);
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            for (Departamento dep : diretor.getDepartamentosDirigidos()) {
                statment = con.prepareStatement(addDirige);
                statment.setInt(1, diretor.getId());
                statment.setInt(2, dep.getId());
                statment.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao inserir uma Associação de Gerente no banco de dados. =" + ex.getMessage()
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
    public static void updateGer(Gerente gerente) {
        FuncionarioDAO.update((Funcionario) gerente);
        Connection con = null;
        PreparedStatement statment = null;
        try {
            deleteGerenciaFuncionario((Funcionario)gerente);
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(addGerecia);
            statment.setInt(1, gerente.getId());
            statment.setInt(2, gerente.getDepartamentoGerenciado().getId());
            statment.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao inserir uma Associação de Gerente no banco de dados. =" + ex.getMessage()
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

    public static void updateDir(Diretor diretor) {
        FuncionarioDAO.update((Funcionario) diretor);
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            deleteDirigeFuncionario((Funcionario)diretor);
            for (Departamento dep : diretor.getDepartamentosDirigidos()) {
                statment = con.prepareStatement(addDirige);
                statment.setInt(1, diretor.getId());
                statment.setInt(2, dep.getId());
                statment.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao inserir uma Associação de Gerente no banco de dados. =" + ex.getMessage()
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

    public static int getTamanhoDepartamento(Gerente gerente) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        int cont = 0;
        List<Funcionario> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(selectFuncionariosDep);
            // Dados da tabela de funcionarios
            statment.setInt(1, gerente.getDepartamentoGerenciado().getId());
            resultSet = statment.executeQuery();
            while (resultSet.next()) {
                cont++;
            }
            return cont;
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

    private static void uptateTemAcesso(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            deleteSistemasFuncionario(funcionario);
            for (Sistema sistema : funcionario.getSitemas()) {
                statment = con.prepareStatement(addTemAcesso);
                statment.setInt(1, funcionario.getId());
                statment.setInt(2, sistema.getId());
                statment.executeUpdate();
                
            }

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao atualizar uma associação de tem sistema no banco de dados. =" + ex.getMessage()
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
    
    public static void deleteSistemasFuncionario(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(deleteFuncionarioDeSis);
            statment.setInt(1, funcionario.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao excluir uma associação temAcesso no banco de dados. =" + ex.getMessage()
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
     public static void deleteDirigeFuncionario(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(deleteFuncionarioDeDir);
            statment.setInt(1, funcionario.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao excluir uma associação temAcesso no banco de dados. =" + ex.getMessage()
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
      public static void deleteGerenciaFuncionario(Funcionario funcionario) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(deleteFuncionarioDeGer);
            statment.setInt(1, funcionario.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao excluir uma associação temAcesso no banco de dados. =" + ex.getMessage()
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
}
