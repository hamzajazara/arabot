package com.arabot.technical.exercise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;

	public AuthorizationServerConfig(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
		endpoints.tokenStore(tokenStore());
	}

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("client").secret(passwordEncoder.encode("secret"))
				.authorizedGrantTypes("password", "authorization_code").scopes("user_info").autoApprove(true);
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
}