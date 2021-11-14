package de.htwberlin.kartenService;

import de.htwberlin.spielService.Spiel;
import de.htwberlin.spielerService.Spieler;

import java.util.List;

public interface KartenService {

    /**
     * Erzeugt ein neues Kartendeck bestehend aus 32 Karten.
     */
    public List<Karte> deckErzeugen();

    /**
     * Sobald das Kartendeck aufgebraucht ist, werden die gelegten Karten neu gemischt.
     */
    public void mischen(List<Karte> karten);

    /**
     * Zieht eine Karte aus dem Kartendeck und fÃ¼gt sie der Hand hinzu.
     *
     * @return karte - Neue Karte aus dem Kartendeck, welche der Hand hinzugefÃ¼gt wird.
     */
    public Karte ziehen(Spieler spieler, Spiel spiel);
}
