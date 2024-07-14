package br.com.forumhub.core.entity;

import java.time.LocalDateTime;

import br.com.forumhub.api.dto.DadosAtualizacaoTopico;
import br.com.forumhub.api.dto.DadosCadastroTopico;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	private String mensagem;

	private LocalDateTime data;

	private String status;

	private String curso;

	private String autor;

	private boolean ativo;

	public Topico(DadosCadastroTopico dados, LocalDateTime data, String estado) {
		this.ativo = true;
		this.titulo = dados.titulo();
		this.mensagem = dados.mensagem();
		this.curso = dados.curso();
		this.data = data;
		this.status = estado;
		this.autor = dados.autor();
	}

	public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
		if (dados.titulo() != null) {
			this.titulo = dados.titulo();
			this.status = "RESPONDIDO";
		}

		if (dados.mensagem() != null) {
			this.mensagem = dados.mensagem();
		}

		if (dados.curso() != null) {
			this.curso = dados.curso();
		}
	}

	public void excluir() {
		this.ativo = false;
	}

}
