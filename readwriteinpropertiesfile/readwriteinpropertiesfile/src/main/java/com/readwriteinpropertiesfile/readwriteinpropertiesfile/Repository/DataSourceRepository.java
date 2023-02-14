package com.readwriteinpropertiesfile.readwriteinpropertiesfile.Repository;

import com.jooqDemo.jooqDemo.Tables;
import com.readwriteinpropertiesfile.readwriteinpropertiesfile.payload.DataSource;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DataSourceRepository {
    @Autowired
    private DSLContext dslContext;

    public List<DataSource> getDataSourceInfo(){
       var dataSources = dslContext.selectFrom(Tables.DATASOURCE_INFO).fetchInto(DataSource.class);
       return dataSources;
    }
}
