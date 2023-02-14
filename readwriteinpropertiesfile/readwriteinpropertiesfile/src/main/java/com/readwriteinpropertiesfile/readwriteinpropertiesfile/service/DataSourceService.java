package com.readwriteinpropertiesfile.readwriteinpropertiesfile.service;

import com.readwriteinpropertiesfile.readwriteinpropertiesfile.Repository.DataSourceRepository;
import com.readwriteinpropertiesfile.readwriteinpropertiesfile.payload.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DataSourceService {
    @Autowired
    private DataSourceRepository dataSourceRepository;

    public List<DataSource> getDataSourceInfo(){
        return  dataSourceRepository.getDataSourceInfo();
    }
}
