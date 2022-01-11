package de.htwberlin;

import de.htwberlin.guiImpl.Spielfeld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("de.htwberlin")
public class Config {
    private static Logger LOGGER = LogManager.getLogger(Config.class);

    public static void main( String[] args ) {
        LOGGER.debug("Main-Methode gestartet!");

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Spielfeld spielfeld = context.getBean(Spielfeld.class);

        spielfeld.spielStarten();

    }
}
