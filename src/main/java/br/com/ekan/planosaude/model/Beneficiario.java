package br.com.ekan.planosaude.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="beneficiario")
public class Beneficiario implements Serializable {

    private static final long serialVersionUID = 3960436649365666213L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "data_nascimento", nullable = false)
    private String dataNascimento;

    @Column(name = "data_inclusao", nullable = false)
    private String dataInclusao;

    @Column(name = "data_atualizacao")
    private String dataAtualizacao;


}
