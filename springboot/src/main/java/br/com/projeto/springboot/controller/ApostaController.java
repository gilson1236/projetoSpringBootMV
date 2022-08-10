package br.com.projeto.springboot.controller;

import br.com.projeto.springboot.model.Apostador;
import br.com.projeto.springboot.service.ApostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aposta")
public class ApostaController {

    @Autowired
    ApostaService apostaService = new ApostaService();

    @PostMapping()
    @Transactional
    public ResponseEntity<Object> salvarApostador(@RequestBody Apostador apostador){
        Optional<Apostador> optionalApostador = apostaService.getApostador(apostador.getNome());

        if(!optionalApostador.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(apostaService.salvarApostador(apostador));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível salvar um novo apostador!");

    }

    @GetMapping
    public List<Apostador> listarApostadores(){
        return this.apostaService.listarApostadores();
    }

    @GetMapping("/{id}")
    public Optional<Apostador> buscarPorId(@PathVariable Long id){
        return apostaService.buscarPorId(id);
    }

    @GetMapping("/nome")
    public Optional<Apostador> buscarPorNome(String nome){
        return apostaService.buscarPorNome(nome);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarApostador(@PathVariable Long id, @RequestBody Apostador apostador){

        Optional<Apostador> optionalApostador = apostaService.buscarPorId(id);
        if(!optionalApostador.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Apostador não encontrado!!!");
        }

        apostador.setId(id);
        apostador.getTelefone().setId(optionalApostador.get().getTelefone().getId());

        return ResponseEntity.status(HttpStatus.OK).body(apostaService.salvarApostador(apostador));
    }

    @DeleteMapping("{id}")
    public void deleteApostador(@PathVariable Long id){
        this.apostaService.delete(id);
    }
}
