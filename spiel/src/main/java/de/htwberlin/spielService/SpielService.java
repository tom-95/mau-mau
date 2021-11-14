package de.htwberlin.spielService;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.spielerService.Spieler;

import java.util.List;

public interface SpielService {

    /**
     * Es wird ein neues Spiel gestartet und die Karten ausgeteilt.
     */
    public Spiel spielStarten(List<Karte> karten, List<Spieler> spieler);

    /**
     * Wird von der Methode spielStarten aufgerufen und gibt die Karten bei Spielbeginn an die Spieler aus.
     */
    public void kartenGeben(Spiel spiel);

}
