package br.com.forumhub.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forumhub.api.dto.DadosAtualizacaoTopico;
import br.com.forumhub.api.dto.DadosCadastroTopico;
import br.com.forumhub.api.dto.DadosListagemTopico;
import br.com.forumhub.core.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

	@Autowired
	private TopicoService service;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroTopico dados) {
		
		service.cadastrar(dados);
		
	}

	@GetMapping
	public Page<DadosListagemTopico> listar(@PageableDefault(size = 10, sort = { "data" }) Pageable paginacao) {
		
		return service.listar(paginacao);
	
	}

	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados) {
		
		service.atualizar(dados);

	}

	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		
		service.excluir(id);
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id) {
		return service.detalhar(id);
		
	}
}
