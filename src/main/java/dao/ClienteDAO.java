package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Cliente;

public class ClienteDAO extends GenericDAO {

    public Cliente salvar(Cliente cliente) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (cliente.getEndereco() != null) {
                session.persist(cliente.getEndereco());
            }
            session.persist(cliente);
            transaction.commit();
            return cliente;
        } catch (RuntimeException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        }
    }

    public List<Cliente> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Cliente c order by c.nome", Cliente.class).list();
        }
    }

}
