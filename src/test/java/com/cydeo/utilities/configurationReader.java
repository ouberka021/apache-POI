package com.cydeo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configurationReader {
    private static Properties properties = new Properties();
    static {
        try {
            //2. we need to open the file in the java memory
            FileInputStream file = new FileInputStream("configuration.properties");

            properties.load(file);

            file.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getProperty(String keyword) {
        return properties.getProperty(keyword);
    }

}
