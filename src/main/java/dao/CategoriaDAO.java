package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Categoria;

public class CategoriaDAO {

    public Categoria salvar(Categoria categoria) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(categoria);
            transaction.commit();
            return categoria;
        } catch (RuntimeException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        }
    }

    public List<Categoria> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Categoria c order by c.nomeCategoria", Categoria.class).list();
        }
    }

    public Categoria buscarPorNomeExato(String nome) {
        String termo = nome == null ? "" : nome.trim().toLowerCase();
        if (termo.isEmpty()) {
            return null;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "from Categoria c where lower(c.nomeCategoria) = :nome",
                    Categoria.class)
                    .setParameter("nome", termo)
                    .uniqueResult();
        }
    }
}
