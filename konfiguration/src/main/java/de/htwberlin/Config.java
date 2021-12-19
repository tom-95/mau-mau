package de.htwberlin;

import de.htwberlin.kartenImpl.KartenImpl;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnImpl.RegelnImpl;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.spielerImpl.SpielerImpl;
import de.htwberlin.spielerService.SpielerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("de.htwberlin")
public class Config {
    private static Logger LOGGER = LogManager.getLogger(Config.class);

    @Bean
    public KartenService kartenService() {
        return new KartenImpl();
    }

    @Bean
    public RegelnService regelnService() {
        return new RegelnImpl();
    }

    @Bean
    public SpielerService spielerService() {
        return new SpielerImpl();
    }


    public static void main( String[] args ) {
        LOGGER.error("Main-Methode gestartet!");

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Spielfeld spielfeld = context.getBean(Spielfeld.class);

        spielfeld.spielStarten();

    }
}
