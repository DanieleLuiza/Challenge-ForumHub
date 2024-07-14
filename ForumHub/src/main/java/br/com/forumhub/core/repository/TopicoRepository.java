package br.com.forumhub.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.forumhub.core.entity.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	
	Page<Topico> findAllByAtivoTrue(Pageable paginacao);

    boolean existsByTituloAndMensagem(String titulo, String mensagem);

}
