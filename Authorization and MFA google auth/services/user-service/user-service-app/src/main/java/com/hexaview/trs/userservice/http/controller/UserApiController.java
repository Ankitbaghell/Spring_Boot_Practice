package com.hexaview.trs.userservice.http.controller;

import com.google.zxing.WriterException;
import com.hexaview.trs.userservice.service.IUserService;
import constant.UserServiceConstants;
import http.request.AuthorizationFormRequest;
import http.request.LoginRequest;
import http.request.ValidateCodeRequest;
import http.response.AuthenicationInfoResponse;
import http.response.GetUserResponse;
import http.response.ValidateTokenResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserServiceConstants.BASE_URL)
public class UserApiController {
  private final IUserService userService;

  public UserApiController(IUserService userService) {
    this.userService = userService;
  }

  /** this method converts user to GetResponse */
  @PostMapping(UserServiceConstants.LOGIN_URL)
  public ResponseEntity<AuthenicationInfoResponse> createAuthenticationToken(
      @RequestHeader(name = "tenantId") String tenantId, @RequestBody LoginRequest loginRequest) {
    var response = userService.createAuthenticationToken(tenantId, loginRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(value = UserServiceConstants.VALIDATE_TOKEN_URL)
  public ResponseEntity<ValidateTokenResponse> validateJwt(
      @RequestParam("token") String token, @RequestHeader(name = "tenantId") String tenantId) {
    var response = userService.validateJwt(token, tenantId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(value = UserServiceConstants.GET_BY_ID_URL)
  ResponseEntity<GetUserResponse> getById(@PathVariable Long id) {
    var result = userService.getById(id);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping(value = UserServiceConstants.GET_BY_UUID_URL)
  ResponseEntity<GetUserResponse> getByUuid(
      @PathVariable String uuid, @RequestHeader("tenantId") String tenantId) {
    var result = userService.getByUuid(uuid, tenantId);
    return new ResponseEntity<>((result), HttpStatus.OK);
  }

  @PostMapping(value = UserServiceConstants.VALIDATE_ROLE)
  private Boolean authorizationRole(
          @RequestBody AuthorizationFormRequest authorizationFormRequest) {
      return userService.authorizaRole(authorizationFormRequest);
  }

  @PostMapping("/home")
  private Boolean testing() {
    System.out.println("checked");
    return true;
  }


}
