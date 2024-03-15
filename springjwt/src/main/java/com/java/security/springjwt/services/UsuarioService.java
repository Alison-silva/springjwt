package com.java.security.springjwt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.security.springjwt.model.Usuario;
import com.java.security.springjwt.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public List<Usuario> list() {
		return usuarioRepository.findAll();
	}

	public Usuario edit(Usuario usuario) {
		return usuarioRepository.saveAndFlush(usuario);
	}

	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}

}
