package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Cliente;

public class ClienteDAO {

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

    public List<Cliente> buscarPorNome(String filtro) {
        String termo = filtro == null ? "" : filtro.trim().toLowerCase();
        if (termo.isEmpty()) {
            return listar();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "from Cliente c where lower(c.nome) like :nome order by c.nome",
                    Cliente.class)
                    .setParameter("nome", "%" + termo + "%")
                    .list();
        }
    }
}
