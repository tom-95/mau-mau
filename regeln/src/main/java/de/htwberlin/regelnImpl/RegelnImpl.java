package de.htwberlin.regelnImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;

import java.util.List;

public class RegelnImpl implements RegelnService {

    public boolean checkCard(Spiel spiel, Karte karte) {

        List<Karte> ablagestapel = spiel.getAblagestapel();
        Karte letzteKarte = ablagestapel.get(ablagestapel.size()-1);

        if ((karte.getWert().equals("Bube")&&!letzteKarte.getWert().equals("Bube")) ||
                (karte.getFarbe().equals(letzteKarte.getFarbe())) ||
                (karte.getWert().equals(letzteKarte.getWert())) ||
                (spiel.getWunschfarbe()!=null&&spiel.getWunschfarbe().equals(karte.getFarbe())))
            return true;
        else
            return false;

    }

    public void handleAss(Spiel spiel) {

        int aktuellerSpieler = spiel.getAmZug();

        int index = aktuellerSpieler;

        if(aktuellerSpieler+2<spiel.getSpieler().size())
            aktuellerSpieler+=2;
        else if(aktuellerSpieler+2==spiel.getSpieler().size())
            aktuellerSpieler=0;
        else if(aktuellerSpieler+1==spiel.getSpieler().size())
            aktuellerSpieler=1;

        spiel.setAmZug(aktuellerSpieler);

    }

    public void handleSieben(Spiel spiel) {

        spiel.setZiehZaehler(spiel.getZiehZaehler()+2);

    }

//    public void handleBube(Spiel spiel) {
//
//        String farbe = spielfeld.farbeWaehlen();
//
//        spiel.setWunschfarbe(farbe);
//
//    }
}
