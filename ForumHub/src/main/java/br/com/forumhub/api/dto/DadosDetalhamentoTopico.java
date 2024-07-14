package br.com.forumhub.api.dto;

import java.time.LocalDateTime;

import br.com.forumhub.core.entity.Topico;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, LocalDateTime data, String status, String autor, String curso) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getData(), topico.getStatus(), topico.getAutor(), topico.getCurso());

    }
}
