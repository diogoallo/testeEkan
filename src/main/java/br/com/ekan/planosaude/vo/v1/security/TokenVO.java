package br.com.ekan.planosaude.vo.v1.security;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TokenVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private Boolean authenticated;
	private Date created;
	private Date expiration;
	private String accessToken;
	private String refreshToken;
	
	public TokenVO() {}
	
	public TokenVO(
			String username,
			Boolean authenticated,
			Date created,
			Date expiration,
			String accessToken,
			String refreshToken) {
		this.username = username;
		this.authenticated = authenticated;
		this.created = created;
		this.expiration = expiration;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

}