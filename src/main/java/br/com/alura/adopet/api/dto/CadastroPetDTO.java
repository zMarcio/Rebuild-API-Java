package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroPetDTO( @NotNull
                              TipoPet tipo,
                              @NotBlank
                              String nome,
                              @NotBlank
                              String raca,
                              @NotNull
                              Integer idade,
                              @NotBlank
                              String cor,
                              @NotNull
                              Float peso) {

}
