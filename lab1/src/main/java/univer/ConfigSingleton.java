package univer;

import java.io.IOException;
import java.util.Properties;

import lombok.Getter;

public class ConfigSingleton {
    
    private static ConfigSingleton instace = new ConfigSingleton();

    @Getter
    private final Properties props;

    private ConfigSingleton()
    {
        props = new Properties();
        var configIS = ClassLoader.getSystemResourceAsStream("\\config.properties");
        if (configIS == null)
        {
            throw new RuntimeException("getSystemResourceAsStream() returned null");
        }
        try {
            props.load(configIS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static ConfigSingleton getInstanse()
    {
        return instace;
    }



}
