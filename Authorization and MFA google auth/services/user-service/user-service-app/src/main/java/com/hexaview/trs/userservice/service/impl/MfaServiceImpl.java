package com.hexaview.trs.userservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.hexaview.trs.userservice.custom.CredentialService;
import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.User;
import com.hexaview.trs.userservice.port.persistence.IMfaRepository;
import com.hexaview.trs.userservice.port.persistence.IUserRepository;
import com.hexaview.trs.userservice.service.IMfaService;
import com.hexaview.trs.userservice.util.DataSourceCachingUtil;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import http.request.ValidateCodeRequest;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MfaServiceImpl implements IMfaService {

    private final GoogleAuthenticator gAuth;

    private final CredentialService credentialService;

    private final DataSourceCachingUtil dataSourceCachingUtil;

    private final IMfaRepository mfaRepository;

    private final IUserRepository userRepository;

    public MfaServiceImpl(GoogleAuthenticator gAuth, CredentialService credentialService, DataSourceCachingUtil dataSourceCachingUtil, IMfaRepository mfaRepository, IUserRepository userRepository) {
        this.gAuth = gAuth;
        this.credentialService = credentialService;
        this.dataSourceCachingUtil = dataSourceCachingUtil;
        this.mfaRepository = mfaRepository;
        this.userRepository = userRepository;
        gAuth.setCredentialRepository(credentialService);
    }

    @Override
    public void generate(String uuid, HttpServletResponse servletResponse, String tenantId)
            throws IOException, WriterException {
        HashMap<String, String> mapOfTenantIdAndUuid= new HashMap<>();

        mapOfTenantIdAndUuid.put(uuid,tenantId);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String hashMapOfUuidAndDataSourceConfigJson
                = mapper.writeValueAsString(mapOfTenantIdAndUuid);


        GoogleAuthenticatorKey key = gAuth.createCredentials(hashMapOfUuidAndDataSourceConfigJson);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        String otpAuthURL = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(tenantId, uuid, key);

        BitMatrix bitMatrix = qrCodeWriter.encode(otpAuthURL, BarcodeFormat.QR_CODE, 200, 200);

        ServletOutputStream outputStream = servletResponse.getOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        outputStream.close();
    }

    @Override
    public Boolean validateKey(
            ValidateCodeRequest validateCodeRequest, String tenantId) {
        HashMap<String, String> mapOfUuidAndTenantId= new HashMap<>();


        mapOfUuidAndTenantId.put(validateCodeRequest.getUserUuid(),tenantId);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String hashMapOfUuidAndTenantIdJson = null;
        try{
            hashMapOfUuidAndTenantIdJson
                    = mapper.writeValueAsString(mapOfUuidAndTenantId);
        }
        catch (Exception exception){
            throw new RuntimeException(exception);
        }
        return gAuth.authorizeUser(hashMapOfUuidAndTenantIdJson, validateCodeRequest.getCode());
    }

    @Override
    public List<Integer> getScratchCodes(String userUuid, String tenantId) {
        DataSourceInfo dataSourceInfo = dataSourceCachingUtil.getDataSourceInfo(tenantId);
        User user = userRepository.getByUuid(userUuid,dataSourceInfo);
        return mfaRepository.getScratchCodes(user.getId(), dataSourceInfo);
    }

    /**
     * @param code
     */
    @Override
    public Integer deleteCode(Integer code, String tenantId, String userUuid) {
        DataSourceInfo dataSourceInfo = dataSourceCachingUtil.getDataSourceInfo(tenantId);
        User user = userRepository.getByUuid(userUuid,dataSourceInfo);
        return mfaRepository.deleteCode(code, dataSourceInfo, user.getId());
    }


}
