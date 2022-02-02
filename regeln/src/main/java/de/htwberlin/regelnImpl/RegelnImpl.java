package de.htwberlin.regelnImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegelnImpl implements RegelnService {
    private static Logger LOGGER = LogManager.getLogger(RegelnImpl.class);

    public boolean checkCard(Spiel spiel, Karte karte) {
        LOGGER.debug("Karte wird gecheckt. Karte = " + karte.getFarbe() + " " + karte.getWert());

        if (spiel.getSpieler().get(spiel.getAmZug()).getHand().size() == 1) {
            if (!spiel.getSpieler().get(spiel.getAmZug()).isMauGesagt()) {
                LOGGER.debug("Karte ungültig weil kein MAU gesagt");
                return false;
            }
            if (spiel.getSpieler().get(spiel.getAmZug()).getZugZaehler() != 1) {
                LOGGER.debug("Karte ungültig weil kein MAU gesagt");
                return false;
            }
        }

        List<Karte> ablagestapel = spiel.getAblagestapel();
        Karte letzteKarte = ablagestapel.get(ablagestapel.size()-1);

        if (spiel.getZiehZaehler() > 0 && !karte.getWert().equals("7")) {
            LOGGER.debug("Karte ungültig weil nur 7 gültig");
            return false;
        }
        if ((karte.getWert().equals("Bube") && letzteKarte.getWert().equals("Bube"))) {
            LOGGER.debug("Karte ungültig weil Bube auf Bube");
            return false;
        }

        if (spiel.getWunschfarbe() != null) {
            if (spiel.getWunschfarbe().equals(karte.getFarbe())) {
                LOGGER.debug("Karte gültig");
                return true;
            } else {
                LOGGER.debug("Karte ungültig weil Wunschfarbe nicht erfüllt");
                return false;
            }
        }

        if ((karte.getWert().equals("Bube") && !letzteKarte.getWert().equals("Bube")) ||
                (karte.getFarbe().equals(letzteKarte.getFarbe())) ||
                (karte.getWert().equals(letzteKarte.getWert()))) {
            LOGGER.debug("Karte gültig");
            return true;
        }
        else {
            LOGGER.debug("Karte ungültig weil kein Bube und Farbe oder Wert nicht gleich");
            return false;
        }
    }

    public void handleAss(Spiel spiel) {
        LOGGER.debug("Ass handling gestartet.");

        int aktuellerSpieler = spiel.getAmZug();
        LOGGER.info("Aktueller Spieler: " + spiel.getSpieler().get(aktuellerSpieler).getName());

        if(aktuellerSpieler + 2 < spiel.getSpieler().size())
            aktuellerSpieler += 2;
        else if(aktuellerSpieler + 2 == spiel.getSpieler().size())
            aktuellerSpieler = 0;
        else if(aktuellerSpieler + 1 == spiel.getSpieler().size())
            aktuellerSpieler = 1;

        spiel.setAmZug(aktuellerSpieler);

        LOGGER.info("Neuer Spieler: " + spiel.getSpieler().get(aktuellerSpieler).getName());
        LOGGER.debug("Ass handling beendet.");

    }

    public void handleSieben(Spiel spiel) {
        LOGGER.debug("Sieben handling gestartet.");

        spiel.setZiehZaehler(spiel.getZiehZaehler()+2);

    }

    public void handleBube(Spiel spiel, String farbe) {
        LOGGER.debug("Bube handling gestartet.");

        spiel.setWunschfarbe(farbe);

    }
}
