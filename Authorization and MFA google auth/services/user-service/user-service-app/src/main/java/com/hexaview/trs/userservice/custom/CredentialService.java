package com.hexaview.trs.userservice.custom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.ScratchCode;
import com.hexaview.trs.userservice.port.persistence.IMfaRepository;
import com.hexaview.trs.userservice.port.persistence.IUserRepository;
import com.hexaview.trs.userservice.util.DataSourceCachingUtil;
import com.warrenstrange.googleauth.ICredentialRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class CredentialService implements ICredentialRepository{

    private final IMfaRepository mfaRepository;

    private final DataSourceCachingUtil dataSourceCachingUtil;

    private final IUserRepository userRepository;

    public CredentialService(IMfaRepository mfaRepository, DataSourceCachingUtil dataSourceCachingUtil, IUserRepository userRepository) {
        this.mfaRepository = mfaRepository;
        this.dataSourceCachingUtil = dataSourceCachingUtil;
        this.userRepository = userRepository;
    }


    /**
     * @param
     * @return
     */
    @Override
    public String getSecretKey(String hashMapOfUuidAndTenantIdJson) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> hashMapOfUuidAndTenantId
                    = mapper.readValue(hashMapOfUuidAndTenantIdJson,
                    Map.class);
            String userUuid = (String)hashMapOfUuidAndTenantId.keySet().toArray()[0];
            String tenantId = hashMapOfUuidAndTenantId.get(userUuid);
            DataSourceInfo dataSourceInfo = dataSourceCachingUtil.getDataSourceInfo(tenantId);
            return mfaRepository.getSecretKey(userUuid, dataSourceInfo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param secretKey      the generated key.
     * @param validationCode the validation code.
     * @param scratchCodes   the list of scratch codes.
     */
    @Override
    public void saveUserCredentials(String hashMapOfUuidAndTenantIdJson, String secretKey, int validationCode, List<Integer> scratchCodes) {
        System.out.println(hashMapOfUuidAndTenantIdJson);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> hashMapOfUuidAndTenantId
                    = mapper.readValue(hashMapOfUuidAndTenantIdJson,
                    Map.class);
            String userUuid = (String)hashMapOfUuidAndTenantId.keySet().toArray()[0];
            String tenantId = (String)hashMapOfUuidAndTenantId.get(userUuid);
            DataSourceInfo dataSourceInfo = dataSourceCachingUtil.getDataSourceInfo(tenantId);
            Long id = mfaRepository.saveUserCredentials(userUuid, secretKey, validationCode, dataSourceInfo);
            ScratchCode scratchCode = new ScratchCode.Builder(id,scratchCodes).build();
            mfaRepository.saveScratchCode(dataSourceInfo,scratchCode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
