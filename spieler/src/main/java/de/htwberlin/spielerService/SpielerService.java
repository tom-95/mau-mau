package de.htwberlin.spielerService;

import java.util.List;

public interface SpielerService {

    /**
     * Methode zum Erstellen von Spielern.
     *
     * @param anzahlSpieler - Die Anzahl zu erzeugender Spieler.
     * @param anzahlVirtuellerSpieler - Die Anzahl zu erzeugender virtueller Spieler.
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

    /**
     * Die übergebenen Spieler werden aus der Datenbank gelöscht.
     *
     * @param spieler - Die zu löschenden Spieler.
     */
    public void spielerLoeschen(List<Spieler> spieler);

}
