package com.readwriteinpropertiesfile.readwriteinpropertiesfile.controllers;

import com.readwriteinpropertiesfile.readwriteinpropertiesfile.payload.DataSource;
import com.readwriteinpropertiesfile.readwriteinpropertiesfile.payload.DatabaseConfig;
import com.readwriteinpropertiesfile.readwriteinpropertiesfile.service.DataSourceService;
import com.readwriteinpropertiesfile.readwriteinpropertiesfile.write.PropertieWriter;
import com.readwriteinpropertiesfile.readwriteinpropertiesfile.write.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class APIController {     // READING VALUES FROM APPLICATION.PROPERTIES

    @Autowired
    private Environment environment;  //read

    @Autowired
    private DatabaseConfig databaseConfig;   //read

    @Autowired
    private PropertieWriter propertieWriter;  //write

    @Autowired
    private PropertiesConfig propertiesConfig;  //write

    @Autowired
    private DataSourceService dataSourceService;

//    @Value("${password}")
//    private String password;

    //read
    @GetMapping("/api/helloworld")
    public String sayHelloWorld(){
        System.out.println("database:"+ environment.getProperty("database"));
        System.out.println("hostname:"+ environment.getProperty("hostname"));
//        System.out.println("password: "+ password);

        System.out.println("---------results by databaseconfig------------");
        System.out.println("database:"+ databaseConfig.getUrl());
        System.out.println("hostname:"+ databaseConfig.getHostname());
        System.out.println("password: "+ databaseConfig.getPassword());
        System.out.println("username: "+ databaseConfig.getUsername());

        return "Hello World";
    }

    //write
    @GetMapping("/api/write")
    public String write() throws IOException {
        propertieWriter.writeToProperties();
        return "written";
    }

    @GetMapping("/api/write2")
    public String write2(){
        propertiesConfig.writeToApplicationProperties();
        return "written";
    }


    @GetMapping("/api/getAll")
    public List<DataSource> getDataSourceInfo(){
        return dataSourceService.getDataSourceInfo();
    }
}