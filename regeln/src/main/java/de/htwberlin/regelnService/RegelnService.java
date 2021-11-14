package de.htwberlin.regelnService;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.spielService.Spiel;

import java.util.List;

public interface RegelnService {

    /**
     * Prüft ob die gelegte Karte zulässig ist.
     */
    public boolean checkCard(List<Karte> ablagestapel, Karte karte);

    /**
     * Beim Legen eines Asses wird der nÃ¤chste Spieler ausgelassen.
     */
    public void handleAss(Spiel spiel);

    /**
     * Beim Legen einer Sieben muss der nÃ¤chste Spieler zwei Karten ziehen oder ebenfalls eine Sieben legen.
     */
    public void handleSieben(Spiel spiel);

    /**
     * Beim Legen eines Buben muss der nächste Spieler die gewünschte Farbe legen.
     */
    public void handleBube(Spiel spiel);
}
