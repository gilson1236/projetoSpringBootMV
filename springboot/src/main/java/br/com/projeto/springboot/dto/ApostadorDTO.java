package br.com.projeto.springboot.dto;

import br.com.projeto.springboot.model.Apostador;
import br.com.projeto.springboot.model.Telefone;
import org.springframework.data.domain.Page;

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

    public static Page<ApostadorDTO> converter(Page<Apostador> asList){

        return asList.map(ApostadorDTO::new);
    }
}
