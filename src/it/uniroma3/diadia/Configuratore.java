package it.uniroma3.diadia;

import java.io.InputStream;
import java.util.Properties;

public class Configuratore {
    private static final String FILE_PROPERTIES = "diadia.properties";
    private static Properties properties = new Properties();

    static {
        try (InputStream input = Configuratore.class.getClassLoader().getResourceAsStream(FILE_PROPERTIES)) {
            if (input == null) {
                // Fallback di default se il file non è trovato
                properties.setProperty("cfu_iniziali", "20");
                properties.setProperty("peso_max_borsa", "10");
            } else {
                properties.load(input);
            }
        } catch (Exception e) {
            properties.setProperty("cfu_iniziali", "20");
            properties.setProperty("peso_max_borsa", "10");
        }
    }

    public static int getCFUIniziali() {
        return Integer.parseInt(properties.getProperty("cfu_iniziali", "20"));
    }

    public static int getPesoMaxBorsa() {
        return Integer.parseInt(properties.getProperty("peso_max_borsa", "10"));
    }
}