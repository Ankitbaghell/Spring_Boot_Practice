package com.hexaview.trs.userservice.config;

import com.hexaview.trs.common.database.connection.Datasource;
import com.hexaview.trs.userservice.adapter.persistence.RdbmsTokenRepository;
import com.hexaview.trs.userservice.adapter.persistence.RdbmsUserCredentialRepository;
import com.hexaview.trs.userservice.adapter.persistence.RdbmsUserRepository;
import com.hexaview.trs.userservice.adapter.persistence.mfa.CredentialRepository;
import com.hexaview.trs.userservice.port.persistence.ITokenRepository;
import com.hexaview.trs.userservice.port.persistence.IUserRepository;
import com.hexaview.trs.userservice.port.persistence.UserCredentialRepository;
import com.hexaview.trs.userservice.service.IAuthenticateService;
import com.hexaview.trs.userservice.service.IAuthenticationService;
import com.hexaview.trs.userservice.service.ITokenService;
import com.hexaview.trs.userservice.service.IUserCredentialService;
import com.hexaview.trs.userservice.service.IUserService;
import com.hexaview.trs.userservice.service.impl.AuthenticateServiceImpl;
import com.hexaview.trs.userservice.service.impl.AuthenticationServiceImpl;
import com.hexaview.trs.userservice.service.impl.TokenServiceImpl;
import com.hexaview.trs.userservice.service.impl.UserCredentialServiceImpl;
import com.hexaview.trs.userservice.service.impl.UserServiceImpl;
import com.hexaview.trs.userservice.util.JwtTokenUtil;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  //  private final CredentialRepository credentialRepository;

  //  public AppConfiguration(CredentialRepository credentialRepository) {
  //    this.credentialRepository = credentialRepository;
  //  }

  @Bean
  public Datasource datasource(
      @Value("${spring.datasource.url}") String jdbcUrl,
      @Value("${spring.datasource.username}") String username,
      @Value("${spring.datasource.password}") String password) {
    return Datasource.getInstance(jdbcUrl, username, password);
  }

  /**
   * + Created a Bean of userService
   *
   * @param userRepository is interface required to service
   * @return
   */
  @Bean
  public IUserService userService(IUserRepository userRepository) {
    return new UserServiceImpl(userRepository);
  }

  /**
   * + Created a Bean of userRepository
   *
   * @param datasource interface required to database connection
   * @return
   */
  @Bean
  public IUserRepository userRepository(Datasource datasource) {
    return new RdbmsUserRepository(datasource);
  }

  @Bean
  public UserCredentialRepository userCredentialRepository(Datasource datasource) {
    return new RdbmsUserCredentialRepository(datasource);
  }

  @Bean
  public IUserCredentialService userCredentialService(
      UserCredentialRepository userCredentialRepository) {
    return new UserCredentialServiceImpl(userCredentialRepository);
  }

  @Bean
  public IAuthenticateService authenticateService() {
    return new AuthenticateServiceImpl();
  }

  @Bean
  public JwtTokenUtil jwtTokenUtil() {
    return new JwtTokenUtil();
  }

  @Bean
  public ITokenRepository tokenRepository(Datasource datasource) {
    return new RdbmsTokenRepository(datasource);
  }

  @Bean
  public ITokenService tokenService(ITokenRepository tokenRepository) {
    return new TokenServiceImpl(tokenRepository);
  }

  @Bean
  public IAuthenticationService authenticationService(
      IUserRepository userRepository,
      IAuthenticateService authenticateService,
      IUserCredentialService userCredentialService,
      JwtTokenUtil jwtTokenUtil) {
    return new AuthenticationServiceImpl(
        userRepository, authenticateService, userCredentialService, jwtTokenUtil);
  }

  @Bean
  public CredentialRepository credentialRepository() {
    return new CredentialRepository();
  }

  @Bean
  public GoogleAuthenticator gAuth() {
    GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
    googleAuthenticator.setCredentialRepository(credentialRepository());
    return googleAuthenticator;
  }
}
