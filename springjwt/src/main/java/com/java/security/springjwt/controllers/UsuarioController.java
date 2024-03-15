package com.java.security.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.security.springjwt.model.Usuario;
import com.java.security.springjwt.services.ImplementationUserDetailsService;
import com.java.security.springjwt.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ImplementationUserDetailsService implementationUserDetailsService;

	@GetMapping(value = "/home")
	public ResponseEntity<String> home() {
		return ResponseEntity.ok("Hello World");
	}

	@PostMapping(value = "/cadastrar")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
		String senhacript = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhacript);
		Usuario user = usuarioService.save(usuario);
		implementationUserDetailsService.insertDefaultUser(user.getId());
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
		
		Usuario userTemp = usuarioService.findById(usuario.getId()).get();

		if (!userTemp.getSenha().equals(usuario.getSenha())) { 
			String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
			usuario.setSenha(senhacriptografada);
		}
		
		Usuario user = usuarioService.edit(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<Usuario>> usuario() {
		List<Usuario> list = usuarioService.list();
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}

}
