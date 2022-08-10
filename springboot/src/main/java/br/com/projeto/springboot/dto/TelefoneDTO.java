package br.com.projeto.springboot.dto;

import br.com.projeto.springboot.model.Telefone;

public class TelefoneDTO {
    private String numero;

    public TelefoneDTO(Telefone telefone){
        this.numero = telefone.getNumero();
    }

    public String getNumero() {
        return numero;
    }
}
