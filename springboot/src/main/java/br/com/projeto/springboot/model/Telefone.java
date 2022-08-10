package br.com.projeto.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "TELEFONE_TABLE")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String numero;

    public Telefone(){

    }

    public Telefone(String numero){
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
