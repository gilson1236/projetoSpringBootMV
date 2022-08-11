package br.com.projeto.springboot.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.projeto.springboot.model.Apostador;
import br.com.projeto.springboot.model.Telefone;
import br.com.projeto.springboot.service.ApostaService;

public class ApostadorForm {

    @NotEmpty @NotNull @Length(min = 2)
    private String nome;

    @NotEmpty @NotNull @Length(min = 2, max = 140)
    private String endereco;

    @NotEmpty @NotNull @Length(min = 2, max = 140)
    private String telefone;

    public Apostador converter(ApostaService apostadorService){

        Telefone telefone = apostadorService.buscarPorNome(nome).get().getTelefone();

        return new Apostador(nome, endereco, telefone);
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
