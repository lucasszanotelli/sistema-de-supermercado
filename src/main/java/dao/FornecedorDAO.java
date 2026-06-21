package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Fornecedor;

public class FornecedorDAO extends GenericDAO {

    public Fornecedor salvar(Fornecedor fornecedor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (fornecedor.getEndereco() != null) {
                session.persist(fornecedor.getEndereco());
            }
            session.persist(fornecedor);
            transaction.commit();
            return fornecedor;
        } catch (RuntimeException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        }
    }

    public List<Fornecedor> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Fornecedor f order by f.nome", Fornecedor.class).list();
        }
    }

}
