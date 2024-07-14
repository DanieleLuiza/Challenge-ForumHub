package br.com.forumhub.core.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.forumhub.api.dto.DadosAtualizacaoTopico;
import br.com.forumhub.api.dto.DadosCadastroTopico;
import br.com.forumhub.api.dto.DadosDetalhamentoTopico;
import br.com.forumhub.api.dto.DadosListagemTopico;
import br.com.forumhub.core.entity.Topico;
import br.com.forumhub.core.repository.TopicoRepository;
import jakarta.validation.Valid;

@Service
public class TopicoService {
	
	@Autowired
	private TopicoRepository repository;

	public void cadastrar(DadosCadastroTopico dados) {
		LocalDateTime data = LocalDateTime.now();
		String status = "ABERTO";

		if (repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
			System.out.println("Titulo e Mensagem do Topico Já existe!");
		} else {
			repository.save(new Topico(dados, data, status));
		}
	}

	public Page<DadosListagemTopico> listar(Pageable paginacao) {
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemTopico::new);
	}

	public void atualizar(DadosAtualizacaoTopico dados) {
		var topico = repository.getReferenceById(dados.id());
		if (topico.equals(null) || topico.equals("")) {
			System.out.println("Topico não encontrado!");
		} else {
			topico.atualizarInformacoes(dados);
		}

	}

	public void excluir(Long id) {
		var topico = repository.getReferenceById(id);
		if (topico.equals(null) || topico.equals("")) {
			System.out.println("Topico não encontrado!");
		} else {
			topico.excluir();
		}
	}

	public ResponseEntity detalhar(Long id) {
		var topico = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
	}

}
