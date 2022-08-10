package br.com.projeto.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "APOSTADOR_TABLE")
public class Apostador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 70, nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Telefone telefone;

    public Apostador(){}

    public Apostador(String nome, String endereco, Telefone telefone){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
}
