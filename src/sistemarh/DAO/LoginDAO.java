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
import sistemarh.entidades.Funcionario;
import sistemarh.entidades.Sistema;
import sistemarh.utils.ConnectionFactory;

/**
 *
 * @author Bruno Henrique
 */
public class LoginDAO {
    
    private static String getSistema = "select idsistema from temacesso where idfuncionario = ?";
    
    public static List<Sistema> sistemaPorFuncionario(Funcionario funcionario){
        List<Sistema> list = new ArrayList();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(getSistema);
            
            ps.setInt(1, funcionario.getId());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Sistema sistema = new Sistema();
                sistema.setId(rs.getInt("idsistema"));
                List<Sistema> sistemas = SistemaDAO.select(sistema);
                list.add(sistemas.get(0));
            }
            
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}
