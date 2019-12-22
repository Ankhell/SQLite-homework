package ru.ankhell.sql_learn.config;

import java.util.Properties;

public class Config {
    final Properties configFile;

    @SuppressWarnings("ConstantConditions")
    public Config(String filename) throws Exception {
        configFile = new java.util.Properties();
        try {
            configFile.load(this.getClass().getClassLoader().
                    getResourceAsStream(filename));
        } catch (Exception eta) {
            System.out.println("Can't load config file!");
            throw eta;
        }
    }

    public String getProperty(String key) {
        return this.configFile.getProperty(key);
    }
}
