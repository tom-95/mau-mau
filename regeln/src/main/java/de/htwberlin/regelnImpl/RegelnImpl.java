package de.htwberlin.regelnImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RegelnImpl implements RegelnService {
    private static Logger LOGGER = LogManager.getLogger(RegelnImpl.class);

    public boolean checkCard(Spiel spiel, Karte karte) {
        LOGGER.debug("Karte wird gecheckt.");

        if (spiel.getSpieler().get(spiel.getAmZug()).getHand().size() == 1) {
            if (!spiel.getSpieler().get(spiel.getAmZug()).isMauGesagt()) {
                return false;
            }
            if (spiel.getSpieler().get(spiel.getAmZug()).getZugZaehler() != 1) {
                return false;
            }
        }

        List<Karte> ablagestapel = spiel.getAblagestapel();
        Karte letzteKarte = ablagestapel.get(ablagestapel.size()-1);

        if (spiel.getZiehZaehler() > 0 && !karte.getWert().equals("7")) {
            return false;
        }
        if ((karte.getWert().equals("Bube") && letzteKarte.getWert().equals("Bube"))) {
            return false;
        }

        if (spiel.getWunschfarbe() != null) {
            if (spiel.getWunschfarbe().equals(karte.getFarbe())) {
                spiel.setWunschfarbe(null);
                return true;
            } else {
                return false;
            }
        }

        if ((karte.getWert().equals("Bube") && !letzteKarte.getWert().equals("Bube")) ||
                (karte.getFarbe().equals(letzteKarte.getFarbe())) ||
                (karte.getWert().equals(letzteKarte.getWert())))
            return true;
        else
            return false;
    }

    public void handleAss(Spiel spiel) {
        LOGGER.debug("Ass handling gestartet.");

        int aktuellerSpieler = spiel.getAmZug();
        LOGGER.info("Aktueller Spieler: " + spiel.getSpieler().get(aktuellerSpieler));

        if(aktuellerSpieler + 2 < spiel.getSpieler().size())
            aktuellerSpieler += 2;
        else if(aktuellerSpieler + 2 == spiel.getSpieler().size())
            aktuellerSpieler = 0;
        else if(aktuellerSpieler + 1 == spiel.getSpieler().size())
            aktuellerSpieler = 1;

        spiel.setAmZug(aktuellerSpieler);

        LOGGER.info("Neuer Spieler: " + spiel.getSpieler().get(aktuellerSpieler));
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
