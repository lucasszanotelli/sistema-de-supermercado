/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Fornecedor")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class Fornecedor extends Pessoa {

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "email")
    private String email;

    public Fornecedor() {
    }

    public Fornecedor(int idPessoa, String nome, String telefone, String cnpj, String email, Endereco endereco) {
        super(idPessoa, nome, telefone, null, null, endereco);
        this.cnpj = cnpj;
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
