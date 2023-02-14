package com.readwriteinpropertiesfile.readwriteinpropertiesfile.write;

import java.io.*;
import java.util.Properties;
import org.springframework.stereotype.Component;

@Component
public class PropertieWriter {
    public void writeToProperties() throws IOException {
        FileInputStream reader = null;
        FileOutputStream writer = null;

        File file = new File("C:\\Users\\ankit.baghel\\Downloads\\readwriteinpropertiesfile\\readwriteinpropertiesfile\\src\\main\\resources\\application.properties");

        try {
//            reader = new FileReader(file);
            reader = new FileInputStream(file);
            writer = new FileOutputStream(file, true);

            Properties p = new Properties();
//            p.load(reader);

            p.setProperty("username","ankit456");
            p.store(writer,"write properties file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                writer.close();
        }
    }
}
