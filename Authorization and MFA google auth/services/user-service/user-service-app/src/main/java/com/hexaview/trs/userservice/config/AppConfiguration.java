package com.hexaview.trs.userservice.config;

import com.hexaview.trs.common.database.connection.Datasource;
import com.hexaview.trs.userservice.Managementapi;
import com.hexaview.trs.userservice.adapter.persistence.RdbmsMfaRepository;
import com.hexaview.trs.userservice.adapter.persistence.RdbmsTokenRepository;
import com.hexaview.trs.userservice.adapter.persistence.RdbmsUserCredentialRepository;
import com.hexaview.trs.userservice.adapter.persistence.RdbmsUserRepository;
import com.hexaview.trs.userservice.custom.CredentialService;
import com.hexaview.trs.userservice.port.persistence.IMfaRepository;
import com.hexaview.trs.userservice.port.persistence.ITokenRepository;
import com.hexaview.trs.userservice.port.persistence.IUserRepository;
import com.hexaview.trs.userservice.port.persistence.UserCredentialRepository;
import com.hexaview.trs.userservice.service.IAuthenticateService;
import com.hexaview.trs.userservice.service.IAuthenticationService;
import com.hexaview.trs.userservice.service.IMfaService;
import com.hexaview.trs.userservice.service.ITokenService;
import com.hexaview.trs.userservice.service.IUserCredentialService;
import com.hexaview.trs.userservice.service.IUserService;
import com.hexaview.trs.userservice.service.impl.AuthenticateServiceImpl;
import com.hexaview.trs.userservice.service.impl.AuthenticationServiceImpl;
import com.hexaview.trs.userservice.service.impl.MfaServiceImpl;
import com.hexaview.trs.userservice.service.impl.TokenServiceImpl;
import com.hexaview.trs.userservice.service.impl.UserCredentialServiceImpl;
import com.hexaview.trs.userservice.service.impl.UserServiceImpl;
import com.hexaview.trs.userservice.util.DataSourceCachingUtil;
import com.hexaview.trs.userservice.util.JwtTokenUtil;
import com.hexaview.trs.userservice.util.RoleUtil;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  @Bean
  public Datasource datasource(
      @Value("${spring.datasource.url}") String jdbcUrl,
      @Value("${spring.datasource.username}") String username,
      @Value("${spring.datasource.password}") String password) {
    return Datasource.getInstance(jdbcUrl, username, password);
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
  public RoleUtil roleUtil(){
    return new RoleUtil();
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
  public JwtTokenUtil jwtTokenUtil(IUserRepository userRepository) {
    return new JwtTokenUtil(userRepository);
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
          ITokenService tokenService,
          IUserRepository userRepository,
          IAuthenticateService authenticateService,
          IUserCredentialService userCredentialService,
          JwtTokenUtil jwtTokenUtil, RoleUtil roleUtil) {
    return new AuthenticationServiceImpl(
            tokenService, userRepository, authenticateService, userCredentialService, jwtTokenUtil, roleUtil);
  }

  @Bean
  public GoogleAuthenticator gAuth() {
    GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
    return new GoogleAuthenticator();
  }

  /**
   * + Created a Bean of userService
   *
   * @param
   * @return
   */
  @Bean
  public IUserService userService(JwtTokenUtil jwtTokenUtil, IAuthenticationService authenticationService, ITokenService tokenService, IUserRepository userRepository, DataSourceCachingUtil dataSourceCachingUtil) {
    return new UserServiceImpl(jwtTokenUtil,authenticationService,tokenService,userRepository, dataSourceCachingUtil);
  }

  @Bean
  public IMfaService mfaService(GoogleAuthenticator googleAuthenticator, CredentialService credentialService,DataSourceCachingUtil dataSourceCachingUtil, IMfaRepository mfaRepository, IUserRepository userRepository){
    return new MfaServiceImpl(googleAuthenticator, credentialService, dataSourceCachingUtil, mfaRepository, userRepository);
  }

  @Bean
  public DataSourceCachingUtil dataSourceCachingUtil(Managementapi managementapi){
    return new DataSourceCachingUtil(managementapi);
  }

  @Bean
  public IMfaRepository mfaRepository(){
    return new RdbmsMfaRepository();
  }
}
