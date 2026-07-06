/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import dao.ClienteDAO;
import dao.CidadeDAO;
import dao.FornecedorDAO;
import dao.FuncionarioDAO;
import dao.ProdutoDAO;
import java.sql.SQLException;
import java.util.List;
import model.Cliente;
import model.Cidade;
import model.Fornecedor;
import model.Funcionario;
import model.Produto;

/**
 *
 * @author 2024122760121
 */
public class GerenciadorDominio {
    private CidadeDAO cidDAO;
    private ClienteDAO clienteDAO;
    private FornecedorDAO fornecedorDAO;
    private FuncionarioDAO funcionarioDAO;
    private ProdutoDAO produtoDAO;

    public GerenciadorDominio() throws ClassNotFoundException, SQLException {
        cidDAO = new CidadeDAO();
        clienteDAO = new ClienteDAO();
        fornecedorDAO = new FornecedorDAO();
        funcionarioDAO = new FuncionarioDAO();
        produtoDAO = new ProdutoDAO();
    }

    public List<Cidade> listarCidade() throws ClassNotFoundException, SQLException {
        return cidDAO.listar();
    }

    public List<Cliente> pesquisarClientes(String filtro) {
        return clienteDAO.buscarPorCampo(Cliente.class, "nome", filtro);
    }

    public List<Fornecedor> pesquisarFornecedores(String filtro) {
        return fornecedorDAO.buscarPorCampo(Fornecedor.class, "nome", filtro);
    }

    public List<Funcionario> pesquisarFuncionarios(String filtro) {
        return funcionarioDAO.buscarPorCampo(Funcionario.class, "nome", filtro);
    }

    public List<Produto> pesquisarProdutos(String filtro) {
        return produtoDAO.pesquisarPorNome(filtro);
    }

    public Cliente buscarCliente(int id) {
        return (Cliente) clienteDAO.get(Cliente.class, id);
    }

    public Fornecedor buscarFornecedor(int id) {
        return (Fornecedor) fornecedorDAO.get(Fornecedor.class, id);
    }

    public Funcionario buscarFuncionario(int id) {
        return (Funcionario) funcionarioDAO.get(Funcionario.class, id);
    }

    public Produto buscarProduto(int id) {
        return (Produto) produtoDAO.get(Produto.class, id);
    }

    public void excluirCliente(int id) {
        Cliente cliente = buscarCliente(id);
        if (cliente != null) {
            clienteDAO.excluir(cliente);
        }
    }

    public void excluirFornecedor(int id) {
        Fornecedor fornecedor = buscarFornecedor(id);
        if (fornecedor != null) {
            fornecedorDAO.excluir(fornecedor);
        }
    }

    public void excluirFuncionario(int id) {
        Funcionario funcionario = buscarFuncionario(id);
        if (funcionario != null) {
            funcionarioDAO.excluir(funcionario);
        }
    }

    public void excluirProduto(int id) {
        Produto produto = buscarProduto(id);
        if (produto != null) {
            produtoDAO.excluir(produto);
        }
    }
}
