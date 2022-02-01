package de.htwberlin.spielImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielService.SpielService;
import de.htwberlin.spielService.VirtuellerSpielerService;
import de.htwberlin.spielerService.Spieler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VirtuellerSpielerImpl implements VirtuellerSpielerService {

    SpielService spielService;
    RegelnService regelnService;

    @Autowired
    public VirtuellerSpielerImpl(SpielService spielService, RegelnService regelnService) {
        this.spielService = spielService;
        this.regelnService = regelnService;
    }

    public void zugDurchfuehren(Spiel spiel, Spieler spieler) {
        List<Karte> hand = spieler.getHand();
        for (Karte karte : hand)
            if (regelnService.checkCard(spiel, karte)) {
                spielService.karteLegen(karte, spiel);
                if (karte.getWert().equals("Bube")) {
                    Map<String, Integer> handFarben = new HashMap<>();
                    for (Karte karte2 : hand) {
                        if (handFarben.containsKey(karte2.getFarbe()))
                            handFarben.put(karte2.getFarbe(), handFarben.get(karte2.getFarbe())+1);
                        else
                            handFarben.put(karte2.getFarbe(), 1);
                    }
                    int hoechsteAnzahl=(Collections.max(handFarben.values()));
                    String hoechsteAnzahlFarbe = null;
                    for (Map.Entry<String, Integer> entry : handFarben.entrySet()) {
                        if (entry.getValue()==hoechsteAnzahl) {
                            hoechsteAnzahlFarbe=entry.getKey();
                        }
                    regelnService.handleBube(spiel, hoechsteAnzahlFarbe);
                    }
                }
                return;
            }
        spielService.ziehen(spiel);
    }

}
