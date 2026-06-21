package dao;

import model.ItemVenda;
import model.Produto;
import model.Venda;
import model.Cliente;
import model.Funcionario;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VendaDAO extends GenericDAO {

    public Venda salvar(Venda venda) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            if (venda.getCliente() != null) {
                Cliente cliente = session.get(Cliente.class, venda.getCliente().getIdPessoa());
                if (cliente == null) {
                    throw new IllegalArgumentException("Cliente nao encontrado.");
                }
                venda.setCliente(cliente);
            }
            if (venda.getFuncionario() != null) {
                Funcionario funcionario = session.get(Funcionario.class, venda.getFuncionario().getIdPessoa());
                if (funcionario == null) {
                    throw new IllegalArgumentException("Funcionario nao encontrado.");
                }
                venda.setFuncionario(funcionario);
            }
            if (venda.getItens() == null || venda.getItens().isEmpty()) {
                throw new IllegalArgumentException("Informe ao menos um produto para a venda.");
            }

            for (ItemVenda item : venda.getItens()) {
                if (item.getProduto() == null) {
                    throw new IllegalArgumentException("Produto invalido na venda.");
                }
                Produto produto = session.get(Produto.class, item.getProduto().getIdProduto());
                if (produto == null) {
                    throw new IllegalArgumentException("Produto nao encontrado.");
                }
                if (produto.getQtdEstoque() < item.getQuantidade()) {
                    throw new IllegalArgumentException("Estoque insuficiente para " + produto.getNomeProduto() + ".");
                }
                item.setProduto(produto);
                item.setVenda(venda);
                item.setValorUnitario(produto.getPreco());
                item.setValorTotal(produto.getPreco() * item.getQuantidade());
                produto.setQtdEstoque(produto.getQtdEstoque() - item.getQuantidade());
            }

            session.persist(venda);
            transaction.commit();
            return venda;
        } catch (RuntimeException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        }
    }
}
