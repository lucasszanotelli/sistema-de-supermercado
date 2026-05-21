/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Cidade;
import org.hibernate.Session;

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
     */
    public List<Cidade> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Cidade", Cidade.class).list();
        }
    }

}
