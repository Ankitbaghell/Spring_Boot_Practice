package com.hexaview.trs.userservice.adapter.persistence;

import com.hexaview.oms.userservice.Tables;
import com.hexaview.trs.common.database.connection.Datasource;
import com.hexaview.trs.common.util.UUIDUtil;
import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.ScratchCode;
import com.hexaview.trs.userservice.port.persistence.IMfaRepository;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RdbmsMfaRepository implements IMfaRepository {

    private final Function<Record, Integer> jooqRecordToScratchCode =
            record -> {
                return record.get(Tables.SCRATCHCODES.SCRATCH_CODE);
            };
    /**
     * @param secretKey
     * @param validationCode
     * @param dataSourceInfo
     */
    @Override
    public Long saveUserCredentials(String userUuid, String secretKey, int validationCode, DataSourceInfo dataSourceInfo) {
        try (Connection con =
                     Datasource.getInstance(
                                     dataSourceInfo.getDatasourceUrl(),
                                     dataSourceInfo.getDatasourceUserName(),
                                     dataSourceInfo.getDatasourcePassword())
                             .getConnection()) {
            DSLContext dsl = DSL.using(con);
            var result = dsl.update(Tables.TRS_USER)
                    .set(Tables.TRS_USER.MFA_SECRET_KEY, secretKey)
                    .set(Tables.TRS_USER.VALIDATION_CODE, validationCode)
                    .where(Tables.TRS_USER.USER_UUID.eq(userUuid)).returningResult(Tables.TRS_USER.ID).fetchOne().getValue(Tables.TRS_USER.ID);
            return result;
        }catch(Exception exception){
            throw new RuntimeException(exception);
        }
    }

    /**
     * @param
     */
    @Override
    public void saveScratchCode(DataSourceInfo dataSourceInfo, ScratchCode scratchCode) {
        try (Connection con =
                     Datasource.getInstance(
                                     dataSourceInfo.getDatasourceUrl(),
                                     dataSourceInfo.getDatasourceUserName(),
                                     dataSourceInfo.getDatasourcePassword())
                             .getConnection()) {
            DSLContext dsl = DSL.using(con);
            Long userId = scratchCode.getUserIdFk();
            scratchCode.getScratchCode().stream().forEach(
                    scratchcode -> {
                        var result = dsl.insertInto(Tables.SCRATCHCODES)
                                .set(Tables.SCRATCHCODES.SCRATCH_CODE, scratchcode)
                                .set(Tables.SCRATCHCODES.SCRATCH_CODE_UUID, UUIDUtil.randomUUID().toString())
                                .set(Tables.SCRATCHCODES.USER_ID_FK, userId)
                                .returningResult(Tables.SCRATCHCODES.ID)
                                .fetchOne()
                                .into(Long.class);
                    }
            );
        }catch(Exception exception){
            throw new RuntimeException(exception);
        }
    }

    /**
     * @param
     * @param dataSourceInfo
     * @return
     */
    @Override
    public String getSecretKey(String userUuid, DataSourceInfo dataSourceInfo) {
        try (Connection con =
                     Datasource.getInstance(
                                     dataSourceInfo.getDatasourceUrl(),
                                     dataSourceInfo.getDatasourceUserName(),
                                     dataSourceInfo.getDatasourcePassword())
                             .getConnection()) {
            DSLContext dsl = DSL.using(con);
            var result = dsl.select(Tables.TRS_USER.MFA_SECRET_KEY)
                    .from(Tables.TRS_USER)
                    .where(Tables.TRS_USER.USER_UUID.eq(userUuid)).fetchOne();
            return result.into(String.class);
        }catch(Exception exception){
            throw new RuntimeException(exception);
        }
    }

    /**
     * @param dataSourceInfo
     * @return
     */
    @Override
    public List<Integer> getScratchCodes(Long userId, DataSourceInfo dataSourceInfo) {
        List<Integer> scratchCodes;
        try (Connection con =
                     Datasource.getInstance(
                                     dataSourceInfo.getDatasourceUrl(),
                                     dataSourceInfo.getDatasourceUserName(),
                                     dataSourceInfo.getDatasourcePassword())
                             .getConnection()) {
            DSLContext dsl = DSL.using(con);
            var result = dsl.select()
                    .from(Tables.SCRATCHCODES)
                    .where(Tables.SCRATCHCODES.USER_ID_FK.eq(userId)).fetch();
            scratchCodes = result.stream().map(jooqRecordToScratchCode).collect(Collectors.toList());
            return scratchCodes;
        }catch(Exception exception){
            throw new RuntimeException(exception);
        }
    }

    /**
     * @param code
     */
    @Override
    public Integer deleteCode(Integer code, DataSourceInfo dataSourceInfo, Long userId) {
        try (Connection con =
                     Datasource.getInstance(
                                     dataSourceInfo.getDatasourceUrl(),
                                     dataSourceInfo.getDatasourceUserName(),
                                     dataSourceInfo.getDatasourcePassword())
                             .getConnection()) {
            DSLContext dsl = DSL.using(con);
            var result = dsl.delete(Tables.SCRATCHCODES)
                    .where(Tables.SCRATCHCODES.SCRATCH_CODE.eq(code).and(Tables.SCRATCHCODES.USER_ID_FK.eq(userId)))
                    .execute();
            return result;
        }catch(Exception exception){
            throw new RuntimeException(exception);
        }

    }
}
