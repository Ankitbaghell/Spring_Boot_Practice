package com.hexaview.trs.userservice.service;

import com.google.zxing.WriterException;
import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.warrenstrange.googleauth.ICredentialRepository;
import http.request.ValidateCodeRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

public interface IMfaService{

    void generate(String uuid, HttpServletResponse servletResponse, String tenantId)
            throws IOException, WriterException;

    Boolean validateKey(ValidateCodeRequest validateCodeRequest, String tenantId);

    List<Integer> getScratchCodes(String userUuid, String tenantId);

    Integer deleteCode(Integer code, String tenantId, String userUuid);
}
