/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cidade;

/**
 *
 * @author 2024122760121
 */
public class CidadeDAO {

    public CidadeDAO() {
    }
    
    /**
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Cidade> listar() throws ClassNotFoundException, SQLException{
        Statement stmt = ConexaoMySQL.obterConexao().createStatement();
        List<Cidade> lista = new ArrayList();
        
        
        String sql = "SELECT * FROM Cidade";
        ResultSet rs = stmt.executeQuery(sql);
        
        while(rs.next()){
            int idCidade = rs.getInt("idCidade"); // entre aspas vai o nome do campo de dados
            String nomeCidade = rs.getString("nomeCidade");
            Cidade cid = new Cidade(idCidade, nomeCidade);
            
            lista.add(cid);
            
        }
        return lista;
        
        
    }
    
}
