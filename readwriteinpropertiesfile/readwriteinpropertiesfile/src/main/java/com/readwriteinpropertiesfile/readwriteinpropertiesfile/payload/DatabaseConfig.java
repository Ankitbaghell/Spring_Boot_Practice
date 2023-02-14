package com.readwriteinpropertiesfile.readwriteinpropertiesfile.payload;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseConfig {
    private String  url;
    private String  hostname;
    private String  username;
    private String  password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String database) {
        this.url = database;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
