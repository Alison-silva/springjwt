package com.java.security.springjwt.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.java.security.springjwt.ApplicationContextLoad;
import com.java.security.springjwt.model.Usuario;
import com.java.security.springjwt.repositories.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JWTTokenAuthenticationService {

	private static final long EXPIRATION_TIME = 172800000;

	private static final String SECRET = "5312a32e-2a3b-409a-8e76-179ee40f6fc1";

	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";

	public void addAuthentication(HttpServletResponse response, String username) throws IOException {

		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

		String token = TOKEN_PREFIX + " " + JWT;

		response.addHeader(HEADER_STRING, token);

		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");

	}

	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {

		String token = request.getHeader(HEADER_STRING);

		if (token != null) {

			String tokenclean = token.replace(TOKEN_PREFIX, "").trim();

			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(tokenclean).getBody().getSubject();
			if (user != null) {

				Usuario usuario = ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class)
						.findUserByLogin(user);

				if (usuario != null) {

					return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha(),
							usuario.getAuthorities());
				}
			}
		}
		return null;
	}
}
