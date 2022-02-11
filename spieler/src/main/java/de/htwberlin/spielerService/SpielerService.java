package de.htwberlin.spielerService;

import java.util.List;

public interface SpielerService {

    /**
     * Methode zum Erstellen von Spielern.
     *
     * @param anzahlSpieler - Die Anzahl zu erzeugender Spieler.
     *
     * @return Eine Liste der neu angelegten Spieler.
     */
    public List<Spieler> spielerErzeugen(int anzahlSpieler, int anzahlVirtuellerSpieler);

    /**
     * Der Übergebene Spieler wird in der Datenbank gespeichert.
     *
     * @param spieler - Der zu speichernde Spieler.
     */
    public void spielerSpeichern(Spieler spieler);

}
