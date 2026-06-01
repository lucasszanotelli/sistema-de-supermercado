/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 2024122760121
 */
public class Produto {
    private int codProduto;
    private String nomeProduto;
    private double preco;
    private int qtdEstoque;
    private Categoria cat;

    public Produto(int codProduto, String nomeProduto, double preco, int qtdEstoque, Categoria cat) {
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.cat = cat;
    }

    public int getIdProduto() {
        return codProduto;
    }

    public void setIdProduto(int codProduto ) {
        this.codProduto = codProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Categoria getCat() {
        return cat;
    }

    public void setCat(Categoria cat) {
        this.cat = cat;
    }
    
    
    
}
