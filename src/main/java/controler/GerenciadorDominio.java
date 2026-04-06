/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import dao.CidadeDAO;
import dao.ConexaoMySQL;
import java.sql.SQLException;
import java.util.List;
import model.Cidade;

/**
 *
 * @author 2024122760121
 */
public class GerenciadorDominio {
    private CidadeDAO cidDAO;
    
    
    public GerenciadorDominio() throws ClassNotFoundException, SQLException{
        ConexaoMySQL.obterConexao();
        
        cidDAO = new CidadeDAO();        
    }
    
    public List<Cidade> listarCidade() throws ClassNotFoundException, SQLException{
        return cidDAO.listar();
    }
    
}
