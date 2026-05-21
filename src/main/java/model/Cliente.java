/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Cliente")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class Cliente extends Pessoa {

    public Cliente() {
    }

    public Cliente(int idPessoa, String nome, String telefone, String cpf, LocalDate dtNasc, Endereco endereco) {
        super(idPessoa, nome, telefone, cpf, dtNasc, endereco);
    }
}