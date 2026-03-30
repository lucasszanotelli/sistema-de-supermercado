/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 2024122760121
 */
public class ConexaoMySQL {
    static Connection conexao;
    
    public static Connection obterConexao() throws ClassNotFoundException, SQLException{
        //VARIÁVEIS DE AMBIENTE DO BANCO DE DADOS
        String login = "root";
        String senha = "";
        String ip = "127.0.0.1:3306";
        String nomeBD = "gerenciaSupermercado";
        String url = "jdbc:mysql://" + ip + "/" + nomeBD;
        
        Class.forName("com.mysql.jdbc.Driver");
        
        conexao = DriverManager.getConnection(url, login, senha);
        return conexao;        
    }
    
    
}
