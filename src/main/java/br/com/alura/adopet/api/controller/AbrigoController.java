package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.*;
import br.com.alura.adopet.api.dto.ListarECadastroPetDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;
    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<AbrigoDTO>> listar() {
        List<AbrigoDTO> dto = abrigoService.listarAbrigos();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastrarAbrigoDTO dto) {
        try {
            abrigoService.cadastrarAbrigo(dto);
            return ResponseEntity.ok("Abrigo cadastrado com sucesso");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<PetDTO>> listarPets(@PathVariable String idOuNome) {
        try {
            List<PetDTO> pets = abrigoService.listPets(idOuNome);
            return ResponseEntity.ok(pets);
        } catch (ValidationException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid CadastroPetDTO dto) {
        try {
            Abrigo abrigo = abrigoService.carregarAbrigo(idOuNome);
            petService.cadastrarPet(abrigo,dto);
            return ResponseEntity.ok().build();
        } catch (ValidationException exception) {
            return ResponseEntity.notFound().build();
        }
    }

}
