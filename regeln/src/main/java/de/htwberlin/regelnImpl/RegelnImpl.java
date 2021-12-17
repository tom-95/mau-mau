package de.htwberlin.regelnImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;

import java.util.List;

public class RegelnImpl implements RegelnService {

    public boolean checkCard(Spiel spiel, Karte karte) {

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

//        int aktuellerSpieler = spiel.getAmZug();
//
//        if (aktuellerSpieler )
//
//
//
//        if (spiel.getAmZug() == (spiel.getSpieler().size() - 1)) {
//            spiel.setAmZug(1);
//        } else {
//            spiel.setAmZug(spiel.getAmZug() + 1);
//        }

        int aktuellerSpieler = spiel.getAmZug();

        if(aktuellerSpieler + 2 < spiel.getSpieler().size())
            aktuellerSpieler += 2;
        else if(aktuellerSpieler + 2 == spiel.getSpieler().size())
            aktuellerSpieler = 0;
        else if(aktuellerSpieler + 1 == spiel.getSpieler().size())
            aktuellerSpieler = 1;

        spiel.setAmZug(aktuellerSpieler);

    }

    public void handleSieben(Spiel spiel) {

        spiel.setZiehZaehler(spiel.getZiehZaehler()+2);

    }

    public void handleBube(Spiel spiel, String farbe) {

        spiel.setWunschfarbe(farbe);

    }
}
