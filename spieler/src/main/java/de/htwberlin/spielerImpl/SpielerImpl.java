package de.htwberlin.spielerImpl;

import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SpielerImpl implements SpielerService {
    private static Logger LOGGER = LogManager.getLogger(SpielerImpl.class);

    public List<Spieler> spielerErzeugen(Integer anzahlSpieler) {
        LOGGER.debug("Spieler werden erzeugt.");

        List<Spieler> spielerListe = new ArrayList<>();
        for(int i=0; i<anzahlSpieler; i++) {
            Spieler spieler = new Spieler("Spieler " + (i+1));
            spielerListe.add(spieler);
        }
        return spielerListe;
    }
}