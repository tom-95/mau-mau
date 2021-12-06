package de.htwberlin.spielerService;

import de.htwberlin.kartenService.Karte;

import java.util.List;

public interface SpielerService {

    /**
     * Methode zum Erstellen von Spielern.
     *
     * @param anzahlSpieler - Die Anzahl zu erzeugender Spieler.
     *
     * @return Eine Liste der neu angelegten Spieler.
     */
    public List<Spieler> spielerErzeugen(Integer anzahlSpieler);
}
