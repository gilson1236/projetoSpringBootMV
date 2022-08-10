package br.com.projeto.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.springboot.model.Apostador;

import java.util.Optional;

public interface ApostaRepository extends JpaRepository<Apostador, Long> {
    Optional<Apostador> findByNome(String nomeApostador);
}
