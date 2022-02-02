package de.htwberlin.regelnImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.regelnService.VirtuellerSpielerService;
import de.htwberlin.spielerService.Spieler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VirtuellerSpielerImpl implements VirtuellerSpielerService {
    private static Logger LOGGER = LogManager.getLogger(VirtuellerSpielerImpl.class);

    RegelnService regelnService;

    @Autowired
    public VirtuellerSpielerImpl(RegelnService regelnService) {
        this.regelnService = regelnService;
    }

    public Karte karteWaehlen(Spiel spiel, Spieler spieler) {
        LOGGER.debug("KI wählt Karte.");
        List<Karte> hand = spieler.getHand();
        for (Karte karte : hand) {
            if (regelnService.checkCard(spiel, karte)) {

                LOGGER.debug("Karte wird zurück gegeben. Karte = " + karte.getFarbe() + " " + karte.getWert());
                return karte;
            }
        }
        LOGGER.debug("Keine gültige Karte gefunden.");
        return null;
    }

    public String farbeWaehlen(Spiel spiel, Spieler spieler) {

        LOGGER.debug("KI wählt Farbe.");
        Map<String, Integer> handFarben = new HashMap<>();
        for (Karte karte : spieler.getHand()) {
            if (handFarben.containsKey(karte.getFarbe()))
                handFarben.put(karte.getFarbe(), handFarben.get(karte.getFarbe()) + 1);
            else
                handFarben.put(karte.getFarbe(), 1);
        }
        int hoechsteAnzahl = (Collections.max(handFarben.values()));
        String hoechsteAnzahlFarbe = null;
        for (Map.Entry<String, Integer> entry : handFarben.entrySet()) {
            if (entry.getValue() == hoechsteAnzahl) {
                hoechsteAnzahlFarbe = entry.getKey();
            }
        }
        LOGGER.debug("KI wählt " + hoechsteAnzahlFarbe);
        return hoechsteAnzahlFarbe;
    }

}
