package de.htwberlin;

import de.htwberlin.kartenImpl.KartenImpl;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnImpl.RegelnImpl;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielImpl.SpielImpl;
import de.htwberlin.spielService.SpielService;
import de.htwberlin.spielerImpl.SpielerImpl;
import de.htwberlin.spielerService.SpielerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("de.htwberlin")
public class Config {

    @Bean
    public KartenService kartenService() {
        return new KartenImpl();
    }

    @Bean
    public RegelnService regelnService() {
        return new RegelnImpl();
    }

//    @Bean
//    public SpielService spielService() {
//        return new SpielImpl();
//    }

    @Bean
    public SpielerService spielerService() {
        return new SpielerImpl();
    }

    @Bean
    public Spiel spiel() {
        return new Spiel();
    }

    public static void main( String[] args ) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//        KartenService kartenService = context.getBean(KartenImpl.class);
//        RegelnService regelnService = context.getBean(RegelnImpl.class);
//        SpielService spielService = context.getBean(SpielImpl.class);
//        SpielerService spielerService = context.getBean(SpielerImpl.class);
        Spielfeld spielfeld = context.getBean(Spielfeld.class);
        //Gui gui = context.getBean(Gui.class);

        spielfeld.spielStarten();

    }
}
