package com.hexaview.trs.userservice.http.controller;


import com.google.zxing.WriterException;
import com.hexaview.trs.userservice.service.IMfaService;
import constant.UserServiceConstants;
import http.request.ValidateCodeRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(UserServiceConstants.MFA_BASE_URL)
public class MfaApiController {

    private final IMfaService mfaService;

    public MfaApiController(IMfaService mfaService) {
        this.mfaService = mfaService;
    }

    @GetMapping(value = UserServiceConstants.GENERATE_MFA)
    public void generate(
            @PathVariable("username") String uuid,
            HttpServletResponse servletResponse,
            @RequestHeader(name = "tenantId") String tenantId)
            throws IOException, WriterException {
        mfaService.generate(uuid, servletResponse, tenantId);
    }

    @PostMapping(value = UserServiceConstants.VALIDATE_MFA)
    public ResponseEntity<Boolean> validateKey(
            @RequestBody ValidateCodeRequest validateCodeRequest,
            @RequestHeader("tenantId") String tenantId) {
        var response = mfaService.validateKey(validateCodeRequest, tenantId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = UserServiceConstants.GET_SCRATCHES)
    public List<Integer> getScratches(@PathVariable String uuid,@RequestHeader("tenantId") String tenantId) {
        return mfaService.getScratchCodes(uuid,tenantId);
    }

    @PostMapping(value = UserServiceConstants.VALIDATE_SCRATCH_CODE)
    public ResponseEntity<Boolean> validateScratch(@RequestBody ValidateCodeRequest validateCodeRequest,@RequestHeader("tenantId") String tenantId) {
        List<Integer> scratchCodes = mfaService.getScratchCodes(validateCodeRequest.getUserUuid(),tenantId);
        Boolean valid = scratchCodes.contains(validateCodeRequest.getCode());
        mfaService.deleteCode(validateCodeRequest.getCode(), tenantId, validateCodeRequest.getUserUuid());
        return new ResponseEntity<>(valid,HttpStatus.OK);
    }
}
