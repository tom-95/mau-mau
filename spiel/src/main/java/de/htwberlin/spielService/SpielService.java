package de.htwberlin.spielService;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;

import java.util.List;

public interface SpielService {

    /**
     * Es wird ein neues Spiel gestartet und die Karten ausgeteilt.
     *
     * @param kartenService - Service für die Deck-Erzeugung.
     * @param spielerService - Service für die Spieler-Erzeugung.
     */
    public Spiel spielStarten(KartenService kartenService, SpielerService spielerService);

    /**
     * Wird von der Methode spielStarten aufgerufen und gibt die Karten bei Spielbeginn an die Spieler aus.
     *
     * @param spiel - Das aktuelle Spiel.
     */
    public void kartenGeben(Spiel spiel);

}
