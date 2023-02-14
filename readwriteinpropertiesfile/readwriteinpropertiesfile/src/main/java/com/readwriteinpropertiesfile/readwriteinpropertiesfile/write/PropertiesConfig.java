package com.readwriteinpropertiesfile.readwriteinpropertiesfile.write;

import org.springframework.stereotype.Component;
import org.springframework.util.DefaultPropertiesPersister;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

@Component
public class PropertiesConfig {
    public void writeToApplicationProperties() {
        try {
            Properties properties = new Properties();
            properties.setProperty("database", "oracle");
            properties.setProperty("hostname", "localhost");
            properties.setProperty("username", "root");
            properties.setProperty("password", "root");

            File file = new File("C:\\Users\\ankit.baghel\\Downloads\\readwriteinpropertiesfile\\readwriteinpropertiesfile\\src\\main\\resources\\application.properties");
            OutputStream outputStream = new FileOutputStream( file, true );
            DefaultPropertiesPersister defaultPropertiesPersister = new DefaultPropertiesPersister();
            defaultPropertiesPersister.store(properties, outputStream, "Comment writing app prop");
        } catch (Exception e ) {
            System.out.println("An error during writing to  application.properties");
            e.printStackTrace();
        }
    }
}
