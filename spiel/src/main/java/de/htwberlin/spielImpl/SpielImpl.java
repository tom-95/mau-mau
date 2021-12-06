package de.htwberlin.spielImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielService.SpielService;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;

public class SpielImpl implements SpielService {

    private KartenService kartenService;
    private SpielerService spielerService;
    private RegelnService regelnService;

    public Spiel spielStarten() {

        return null;
    }

    public void kartenGeben(Spiel spiel) {

    }

    @Override
    public Karte ziehen(Spieler spieler, Spiel spiel) {
        return null;
    }

    @Override
    public void karteLegen(Karte karte, Spieler spieler, Spiel spiel) {

    }

    @Override
    public void mauSagen(Spieler spieler, Spiel spiel) {

    }

    @Override
    public void setKartenService(KartenService kartenService) {

    }

    @Override
    public void setSpielerService(SpielerService spielerService) {

    }

    @Override
    public void setRegelnService(RegelnService regelnService) {

    }
}
