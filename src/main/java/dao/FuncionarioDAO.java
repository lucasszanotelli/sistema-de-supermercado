package dao;

import java.util.List;
import model.Funcionario;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FuncionarioDAO extends GenericDAO {

    public Funcionario salvar(Funcionario funcionario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (funcionario.getEndereco() != null) {
                session.persist(funcionario.getEndereco());
            }
            session.persist(funcionario);
            transaction.commit();
            return funcionario;
        } catch (RuntimeException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        }
    }

    public List<Funcionario> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Funcionario f order by f.nome", Funcionario.class).list();
        }
    }
}
