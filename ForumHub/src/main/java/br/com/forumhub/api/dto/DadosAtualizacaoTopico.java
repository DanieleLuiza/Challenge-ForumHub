package br.com.forumhub.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
		@NotNull
        Long id,
        String titulo,
        String mensagem,
        String curso
        ) {

}
