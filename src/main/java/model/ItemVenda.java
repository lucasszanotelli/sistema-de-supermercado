package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ItemVenda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItemVenda")
    private int idItemVenda;

    @ManyToOne
    @JoinColumn(name = "idVenda")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "valorUnitario")
    private double valorUnitario;

    @Column(name = "valorTotal")
    private double valorTotal;

    public int getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(int idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
