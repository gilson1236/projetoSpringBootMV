package br.com.projeto.springboot.controller;

import br.com.projeto.springboot.dto.PageApostadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Optional;

import br.com.projeto.springboot.dto.ApostadorDTO;
import br.com.projeto.springboot.model.Apostador;
import br.com.projeto.springboot.service.ApostaService;

@RestController
@RequestMapping("/api/v1/aposta")
public class ApostaController {

    @Autowired
    ApostaService apostaService = new ApostaService();

    @PostMapping()
    @Transactional
    @CacheEvict(value = "listaApostadores", allEntries = true)
    public ResponseEntity<Object> salvarApostador(@RequestBody Apostador apostador){
        Optional<Apostador> optionalApostador = apostaService.getApostador(apostador.getNome());

        if(!optionalApostador.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(apostaService.salvarApostador(apostador));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("O apostador já está cadastrado!");

    }

    @GetMapping
    @Cacheable(value = "listaApostadores")
    public PageApostadorDTO getApostadores(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                           @RequestParam(defaultValue = "10") @Positive @Max(100) int size){
        Pageable pageable = PageRequest.of(page, size);

        Page<ApostadorDTO> pageDTO = ApostadorDTO.converter(apostaService.listarApostadores(pageable));
        List<ApostadorDTO> listDTO = pageDTO.getContent();

        return new PageApostadorDTO(listDTO, pageDTO.getTotalElements(), pageDTO.getTotalPages());
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
    @CacheEvict(value = "listaApostadores", allEntries = true)
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
    @CacheEvict(value = "listaApostadores", allEntries = true)
    public void deleteApostador(@PathVariable Long id){
        this.apostaService.delete(id);
    }

    @GetMapping(value= "/sortear", produces = {"application/json"})
    public Optional<Apostador> sortear(){
        Apostador vencedor = null;
        List<Apostador> apostadores = apostaService.listarApostadores();
        int tamanho = apostadores.size();
        int numSorteado = (int) (1 + (Math.random() * tamanho));

        for(Apostador encontrar: apostadores){
            if(numSorteado == encontrar.getId()){
                vencedor = encontrar;
                break;
            }
        }

        return Optional.ofNullable(vencedor);
    }
}
