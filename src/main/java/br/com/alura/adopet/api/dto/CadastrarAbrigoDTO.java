package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotNull;

public record CadastrarAbrigoDTO(@NotNull String nome, @NotNull String telefone, @NotNull String email) {
}
