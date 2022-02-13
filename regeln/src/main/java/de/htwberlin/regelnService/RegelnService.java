package de.htwberlin.regelnService;

import de.htwberlin.kartenService.Karte;

public interface RegelnService {

    /**
     * Prüft, ob die gelegte Karte zulässig ist.
     *
     * @param spiel - Das aktuelle Spiel.
     * @param karte - Die Karte welche vom Spieler gelegt wurde.
     *
     * @return Boolesches Ergebnis, ob die Karte gelegt werden darf.
     */
    public boolean checkCard(Spiel spiel, Karte karte);

    /**
     * Beim Legen eines Asses wird der nächste Spieler ausgelassen.
     *
     * @param spiel- Das aktuelle Spiel.
     */
    public void handleAss(Spiel spiel);

    /**
     * Beim Legen einer Sieben muss der nächste Spieler zwei Karten ziehen oder ebenfalls eine Sieben legen.
     *
     * @param spiel- Das aktuelle Spiel.
     */
    public void handleSieben(Spiel spiel);

    /**
     * Beim Legen eines Buben muss der nächste Spieler die gewünschte Farbe legen.
     *
     * @param spiel- Das aktuelle Spiel.
     */
    public void handleBube(Spiel spiel, String farbe);
}
