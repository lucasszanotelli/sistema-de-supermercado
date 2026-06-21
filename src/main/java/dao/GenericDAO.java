package dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GenericDAO {

    public void inserir(Object obj) throws HibernateException {
        Transaction transacao = null;

        try (Session sessao = ConexaoHibernate.getSessionFactory().openSession()) {
            transacao = sessao.beginTransaction();
            sessao.persist(obj);
            transacao.commit();
        } catch (HibernateException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw new HibernateException(ex);
        }
    }

    public void alterar(Object obj) throws HibernateException {
        Transaction transacao = null;

        try (Session sessao = ConexaoHibernate.getSessionFactory().openSession()) {
            transacao = sessao.beginTransaction();
            sessao.merge(obj);
            transacao.commit();
        } catch (HibernateException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw new HibernateException(ex);
        }
    }

    public void excluir(Object obj) throws HibernateException {
        Transaction transacao = null;

        try (Session sessao = ConexaoHibernate.getSessionFactory().openSession()) {
            transacao = sessao.beginTransaction();
            sessao.remove(sessao.contains(obj) ? obj : sessao.merge(obj));
            transacao.commit();
        } catch (HibernateException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw new HibernateException(ex);
        }
    }

    public List listar(Class classe) throws HibernateException {
        Transaction transacao = null;

        try (Session sessao = ConexaoHibernate.getSessionFactory().openSession()) {
            transacao = sessao.beginTransaction();

            CriteriaQuery consulta = sessao.getCriteriaBuilder().createQuery(classe);
            consulta.from(classe);
            List lista = sessao.createQuery(consulta).getResultList();

            transacao.commit();
            return lista;
        } catch (HibernateException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw new HibernateException(ex);
        }
    }

    public Object get(Class classe, int id) throws HibernateException {
        Transaction transacao = null;

        try (Session sessao = ConexaoHibernate.getSessionFactory().openSession()) {
            transacao = sessao.beginTransaction();
            Object objReturn = sessao.get(classe, id);
            transacao.commit();
            return objReturn;
        } catch (HibernateException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw new HibernateException(ex);
        }
    }

    public List buscarPorCampo(Class classe, String campo, String filtro) throws HibernateException {
        String termo = filtro == null ? "" : filtro.trim().toLowerCase();
        if (termo.isEmpty()) {
            return listar(classe);
        }

        Transaction transacao = null;

        try (Session sessao = ConexaoHibernate.getSessionFactory().openSession()) {
            transacao = sessao.beginTransaction();

            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery consulta = builder.createQuery(classe);
            Root root = consulta.from(classe);
            consulta.select(root);
            consulta.where(builder.like(builder.lower(root.get(campo).as(String.class)), "%" + termo + "%"));
            consulta.orderBy(builder.asc(root.get(campo)));

            List lista = sessao.createQuery(consulta).getResultList();

            transacao.commit();
            return lista;
        } catch (HibernateException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw new HibernateException(ex);
        }
    }

    public Object buscarPorCampoExato(Class classe, String campo, String valor) throws HibernateException {
        String termo = valor == null ? "" : valor.trim().toLowerCase();
        if (termo.isEmpty()) {
            return null;
        }

        Transaction transacao = null;

        try (Session sessao = ConexaoHibernate.getSessionFactory().openSession()) {
            transacao = sessao.beginTransaction();

            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery consulta = builder.createQuery(classe);
            Root root = consulta.from(classe);
            consulta.select(root);
            consulta.where(builder.equal(builder.lower(root.get(campo).as(String.class)), termo));

            Object objReturn = sessao.createQuery(consulta).uniqueResult();

            transacao.commit();
            return objReturn;
        } catch (HibernateException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw new HibernateException(ex);
        }
    }
}
