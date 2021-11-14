package de.htwberlin.kartenService;

import de.htwberlin.spielService.Spiel;
import de.htwberlin.spielerService.Spieler;

import java.util.List;

public interface KartenService {

    /**
     * Erzeugt ein neues Kartendeck bestehend aus 32 Karten.
     *
     * @return Ein neues Kartendeck bestehend aus einer Liste von Karten
     */
    public List<Karte> deckErzeugen();

    /**
     * Sobald das Kartendeck aufgebraucht ist, werden die gelegten Karten neu gemischt.
     *
     * @param karten - Liste von Karten welche gemischt werden sollen.
     */
    public void mischen(List<Karte> karten);

    /**
     * Zieht eine Karte aus dem Kartendeck und fügt sie der Hand hinzu.
     *
     * @param spieler - Spieler welche die Karte zieht.
     * @param spiel - Das aktuelle Spiel.
     *
     * @return Neue Karte aus dem Kartendeck, welche der Hand hinzugefügt wird.
     */
    public Karte ziehen(Spieler spieler, Spiel spiel);
}
