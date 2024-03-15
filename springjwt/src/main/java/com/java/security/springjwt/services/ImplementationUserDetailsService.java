package com.java.security.springjwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.security.springjwt.model.Usuario;
import com.java.security.springjwt.repositories.UsuarioRepository;

@Service
public class ImplementationUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findUserByLogin(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado");
		}

		return new User(usuario.getLogin(), usuario.getSenha(), usuario.getAuthorities());
	}

	public void insertDefaultUser(Long id) {

		String constraint = usuarioRepository.queryConstraint();

		if (constraint != null) {
			jdbcTemplate.execute(" alter table usuarios_role  DROP CONSTRAINT " + constraint);
		}

		usuarioRepository.insertDefaultUser(id);
	}
}
