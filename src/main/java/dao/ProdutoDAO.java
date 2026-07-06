package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Categoria;
import model.Fornecedor;
import model.Produto;

public class ProdutoDAO extends GenericDAO {

    public Produto salvar(Produto produto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Categoria categoria = produto.getCat();
            if (categoria != null) {
                if (categoria.getIdCategoria() > 0) {
                    Categoria managed = session.get(Categoria.class, categoria.getIdCategoria());
                    if (managed != null) {
                        produto.setCat(managed);
                    } else {
                        session.persist(categoria);
                    }
                } else {
                    session.persist(categoria);
                }
            }

            Fornecedor fornecedor = produto.getFornecedor();
            if (fornecedor != null) {
                if (fornecedor.getIdPessoa() > 0) {
                    Fornecedor managed = session.get(Fornecedor.class, fornecedor.getIdPessoa());
                    if (managed != null) {
                        produto.setFornecedor(managed);
                    } else {
                        session.persist(fornecedor);
                    }
                } else {
                    session.persist(fornecedor);
                }
            }
            session.persist(produto);
            transaction.commit();
            return produto;
        } catch (RuntimeException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        }
    }

    public List<Produto> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Produto p order by p.nomeProduto", Produto.class).list();
        }
    }

    public List<Produto> pesquisarPorNome(String filtro) {
        String termo = filtro == null ? "" : filtro.trim().toLowerCase();
        if (termo.isEmpty()) {
            return listar();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "from Produto p where lower(p.nomeProduto) like :termo order by p.nomeProduto",
                    Produto.class)
                    .setParameter("termo", "%" + termo + "%")
                    .list();
        }
    }
}
