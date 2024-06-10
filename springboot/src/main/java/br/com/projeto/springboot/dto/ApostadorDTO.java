package br.com.projeto.springboot.dto;

import br.com.projeto.springboot.model.Apostador;
import br.com.projeto.springboot.model.Telefone;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

public class ApostadorDTO {

    @JsonProperty("_id")
    private Long id;

    private String nome;
    private String endereco;
    private Telefone telefone;

    private ApostadorDTO(Apostador apostador){
        this.id = apostador.getId();
        this.nome = apostador.getNome();
        this.endereco = apostador.getEndereco();
        this.telefone = apostador.getTelefone();
    }

    public Long getId() {
        return id;
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

    public static Page<ApostadorDTO> converter(Page<Apostador> asList){

        return asList.map(ApostadorDTO::new);
    }
}
