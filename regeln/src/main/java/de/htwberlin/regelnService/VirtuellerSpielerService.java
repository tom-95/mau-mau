package de.htwberlin.regelnService;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.spielerService.Spieler;

public interface VirtuellerSpielerService {

    Karte karteWaehlen(Spiel spiel, Spieler spieler);

    String farbeWaehlen(Spiel spiel, Spieler spieler);

}
