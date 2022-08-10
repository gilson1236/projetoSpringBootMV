package br.com.projeto.springboot.dto;

import br.com.projeto.springboot.model.Apostador;
import br.com.projeto.springboot.model.Telefone;

public class ApostadorDTO {

    private String nome;
    private String endereco;
    private Telefone telefone;

    private ApostadorDTO(Apostador apostador){
        this.nome = apostador.getNome();
        this.endereco = apostador.getEndereco();
        this.telefone = apostador.getTelefone();
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }
}
