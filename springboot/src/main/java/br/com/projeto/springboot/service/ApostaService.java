package br.com.projeto.springboot.service;

import br.com.projeto.springboot.model.Apostador;
import br.com.projeto.springboot.repository.ApostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApostaService {

    @Autowired
    ApostaRepository apostaRepository;

    public Optional<Apostador> getApostador(String nomeApostador){
        return this.apostaRepository.findByNome(nomeApostador);
    }

    public Apostador salvarApostador(Apostador apostador) {

        return apostaRepository.save(apostador);
    }

    public List<Apostador> listarApostadores() {
        return this.apostaRepository.findAll();
    }

    public Page<Apostador> listarApostadores(Pageable paginacao){
        return this.apostaRepository.findAll(paginacao);
    }

    public Optional<Apostador> buscarPorId(Long id) {
        return this.apostaRepository.findById(id);
    }

    public void delete(Long id) {
        this.apostaRepository.deleteById(id);
    }

    public Optional<Apostador> buscarPorNome(String nome) {
        return this.apostaRepository.findByNome(nome);
    }
}
