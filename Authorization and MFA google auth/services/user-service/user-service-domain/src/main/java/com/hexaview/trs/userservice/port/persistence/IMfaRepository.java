package com.hexaview.trs.userservice.port.persistence;

import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.ScratchCode;

import java.util.List;

public interface IMfaRepository {


    Long saveUserCredentials(String uuid, String secretKey, int validationCode, DataSourceInfo dataSourceInfo);

    void saveScratchCode(DataSourceInfo dataSourceInfo, ScratchCode scratchCode);

    String getSecretKey(String userUuid, DataSourceInfo dataSourceInfo);

    List<Integer> getScratchCodes(Long userId, DataSourceInfo dataSourceInfo);

    Integer deleteCode(Integer code, DataSourceInfo dataSourceInfo, Long userId);
}
