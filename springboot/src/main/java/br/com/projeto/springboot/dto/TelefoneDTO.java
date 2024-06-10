package br.com.projeto.springboot.dto;

import br.com.projeto.springboot.model.Telefone;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TelefoneDTO {

    @JsonProperty("_id")
    private Long id;
    private String numero;

    public TelefoneDTO(Telefone telefone){
        this.id = telefone.getId();
        this.numero = telefone.getNumero();
    }

    public Long getId(){
        return id;
    }

    public String getNumero() {
        return numero;
    }
}
