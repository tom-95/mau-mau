package de.htwberlin.spielerService;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.spielService.Spiel;

import java.util.List;

public interface SpielerService {

    /**
     * Sofern erlaubt, legt die Methode die vom Spieler gewünschte Karte auf den Ablagestapel und entfernt sie aus der Hand.
     *
     * @param karte - Die vom Spieler gewünschte Karte zur Ablage.
     * @param spieler - Der Spieler der mau sagt.
     * @param spiel - Das aktuelle Spiel.
     */
    public void karteLegen(Karte karte, Spieler spieler, Spiel spiel, RegelnService regelnService);

    /**
     * Der Spieler sagt "Mau" und kündigt so das Spielen seiner letzten Karte an.
     *
     * @param spieler - Der Spieler der mau sagt.
     * @param spiel - Das aktuelle Spiel.
     */
    public void mauSagen(Spieler spieler, Spiel spiel);

    /**
     * Methode zum Erstellen von Spielern.
     *
     * @param anzahlSpieler - Die Anzahl zu erzeugender Spieler.
     *
     * @return Eine Liste der neu angelegten Spieler.
     */
    public List<Spieler> spielerErzeugen(Integer anzahlSpieler);
}
