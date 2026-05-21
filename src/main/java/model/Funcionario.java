/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Funcionario")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class Funcionario extends Pessoa {

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "salario")
    private BigDecimal salario;

    public Funcionario() {
    }

    public Funcionario(int idPessoa, String nome, String telefone, String cpf, LocalDate dtNasc, Endereco endereco, String cargo, BigDecimal salario) {
        super(idPessoa, nome, telefone, cpf, dtNasc, endereco);
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}
