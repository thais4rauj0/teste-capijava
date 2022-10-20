package com.capijava.capijava.service;

import java.util.Optional;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.capijava.capijava.model.UsuarioLogin;
import com.capijava.capijava.model.UsuarioModel;
import com.capijava.capijava.repository.UsuarioRepository;

@Service
public class UsuarioService {

		@Autowired
		private UsuarioRepository usuarioRepository;
		
		
		public Optional<UsuarioModel> cadastroUsuario(UsuarioModel usuario){
				
			if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já Existe!", null);
				
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			
			return Optional.of(usuarioRepository.save(usuario));
		
		
		
		}
		
		public Optional<UsuarioModel> atualizarUsuario(UsuarioModel usuario){
		
			if(usuarioRepository.findById(usuario.getId()).isPresent()){
				Optional<UsuarioModel> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
				
				if (buscaUsuario.isPresent()) {
					
					if(buscaUsuario.get().getId() != usuario.getId())
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
				}
				usuario.setSenha(criptografarSenha(usuario.getSenha()));
				return Optional.of(usuarioRepository.save(usuario));
						
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
		}
		
		public Optional<UsuarioLogin> logarUsuario(Optional<UsuarioLogin> usuarioLogin){
			
			Optional<UsuarioModel> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
			
			if (usuario.isPresent()) {
				if (compararSenhas(usuarioLogin.get().getSenha(),
				usuario.get().getSenha())) {
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get().setToken(
				gerarBasicToken(usuarioLogin.get().getUsuario(),
				usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				return usuarioLogin;
				}
				}
				throw new ResponseStatusException(
				HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);

		}
		
		private String criptografarSenha(String senha) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaEncoder = encoder.encode(senha);
			return senhaEncoder;
			}
			private boolean compararSenhas(String senhaDigitada,
			String senhaBanco) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder.matches(senhaDigitada, senhaBanco);
			}
			private String gerarBasicToken(String email, String password) {
			String estrutura = email + ":" + password;

			byte[] estruturaBase64 = Base64.encodeBase64(
					estrutura.getBytes(Charset.forName("US-ASCII")));
					return "Basic " + new String(estruturaBase64);
					}
			
}

		

