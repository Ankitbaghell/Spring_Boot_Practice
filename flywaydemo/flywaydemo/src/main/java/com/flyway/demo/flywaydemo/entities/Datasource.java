package com.flyway.demo.flywaydemo.entities;

public class Datasource {

    private String url;
    private String user;
    private String password;
    private String flywayMigrationPath;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public Datasource(String url, String user, String password, String flywayMigrationPath) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.flywayMigrationPath = flywayMigrationPath;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFlywayMigrationPath() {
        return flywayMigrationPath;
    }

    public void setFlywayMigrationPath(String flywayMigrationPath) {
        this.flywayMigrationPath = flywayMigrationPath;
    }
}
