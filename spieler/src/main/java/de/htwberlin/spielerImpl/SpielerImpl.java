package de.htwberlin.spielerImpl;

import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;

import java.util.ArrayList;
import java.util.List;

public class SpielerImpl implements SpielerService {

    public List<Spieler> spielerErzeugen(Integer anzahlSpieler) {

        List<Spieler> spielerListe = new ArrayList<>();
        for(int i=0; i<anzahlSpieler; i++) {
            Spieler spieler = new Spieler("Spieler " + (i+1));
            spielerListe.add(spieler);
        }
        return spielerListe;
    }
}