package de.htwberlin.regelnService;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.spielerService.Spieler;

public interface VirtuellerSpielerService {

    /**
     * Der KI-Spieler wählt eine Karte aus seiner Hand aus.
     *
     * @param spiel - Das aktuelle Spiel.
     * @param spieler - Der aktuelle Spieler.
     */
    Karte karteWaehlen(Spiel spiel, Spieler spieler);

    /**
     * Der KI-Spieler wählt beim legen eines Buben eine Farbe.
     *
     * @param spiel- Das aktuelle Spiel.
     * @param spieler - Der aktuelle Spieler.
     */
    String farbeWaehlen(Spiel spiel, Spieler spieler);

}
