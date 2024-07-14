package br.com.forumhub.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.forumhub.core.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
	
	UserDetails findByLogin(String login);
	
}
