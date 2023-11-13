package br.com.ekan.planosaude.service;


import br.com.ekan.planosaude.jwt.JwtTokenProvider;
import br.com.ekan.planosaude.repository.UserRepository;
import br.com.ekan.planosaude.vo.v1.security.AccountCredentialsVO;
import br.com.ekan.planosaude.vo.v1.security.TokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserRepository repository;

	public ResponseEntity signin(AccountCredentialsVO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password));
			
			var user = repository.findByUsername(username);
			
			var tokenResponse = new TokenVO();
			if (user != null) {
				tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
			}
			return ResponseEntity.ok(tokenResponse);
		} catch (Exception e) {
			throw new BadCredentialsException("Nome de usuário/senha inválidos!");
		}
	}
	

	public ResponseEntity refreshToken(String username, String refreshToken) {
		var user = repository.findByUsername(username);
		
		var tokenResponse = new TokenVO();
		if (user != null) {
			tokenResponse = tokenProvider.refreshToken(refreshToken);
		} else {
			throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
		}
		return ResponseEntity.ok(tokenResponse);
	}
}
