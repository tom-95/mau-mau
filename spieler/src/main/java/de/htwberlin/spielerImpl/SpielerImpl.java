package de.htwberlin.spielerImpl;

import de.htwberlin.exceptions.DatenbankNichtErreichbarException;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerRepository;
import de.htwberlin.spielerService.SpielerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpielerImpl implements SpielerService {
    private static Logger LOGGER = LogManager.getLogger(SpielerImpl.class);

    private SpielerRepository repository;

    @Autowired
    public SpielerImpl(SpielerRepository repository) {
        this.repository = repository;
    }

    public SpielerImpl() {}

    public List<Spieler> spielerErzeugen(int anzahlSpieler, int anzahlVirtuellerSpieler) {
        LOGGER.debug("Spieler werden erzeugt.");

        List<Spieler> spielerListe = new ArrayList<>();
        for(int i=0; i<anzahlSpieler; i++) {
            Spieler spieler = new Spieler("Spieler " + (i+1), false);
            spielerListe.add(spieler);
        }
        for(int i=0; i<anzahlVirtuellerSpieler; i++) {
            Spieler spieler = new Spieler("KI-Spieler " + (i+1), true);
            spielerListe.add(spieler);
        }
        return spielerListe;
    }

    public void spielerSpeichern(Spieler spieler) {

        try {
            repository.save(spieler);
        } catch(RuntimeException e) {
            LOGGER.error("Datenbank nicht erreichbar.");
            throw new DatenbankNichtErreichbarException(e);
        }

    }

}