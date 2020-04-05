package com.demo.common;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JwtAuthConfig
{
	
	@Value("${demo.security.jwt.url:/login}")
	private String	url;
	
	@Value("${demo.security.jwt.header:Authorization}")
	private String	header;
	
	@Value("${demo.security.jwt.prefix:Bearer}")
	private String	prefix;
	
	@Value("${demo.security.jwt.expiration:#{24*60*60}}")
	private int		expiration;
	
	@Value("${demo.security.jwt.secret}")
	private String	secret;
}
