package de.htwberlin.kartenService;

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

}
