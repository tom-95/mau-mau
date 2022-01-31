package de.htwberlin;

import de.htwberlin.guiImpl.SpielfeldImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = {"de.htwberlin"})
@ComponentScan("de.htwberlin")
public class Config {
    private static Logger LOGGER = LogManager.getLogger(Config.class);

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("TestDB");

        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    public static void main( String[] args ) {
        LOGGER.debug("Main-Methode gestartet!");

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        SpielfeldImpl spielfeld = context.getBean(SpielfeldImpl.class);

        spielfeld.spielStarten();

    }
}
