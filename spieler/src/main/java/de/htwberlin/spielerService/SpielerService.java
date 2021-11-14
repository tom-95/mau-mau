package de.htwberlin.spielerService;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.spielService.Spiel;

public interface SpielerService {

    /**
     * Sofern erlaubt, legt die Methode die vom Spieler gewünschte Karte auf den Ablagestapel und entfernt sie aus der Hand.
     *
     * @param karte - Die vom Spieler gewünschte Karte zur Ablage.
     */
    public void karteLegen(Karte karte, Spieler spieler, Spiel spiel);

    /**
     * Der Spieler sagt "Mau" und kündigt so das Spielen seiner letzten Karte an.
     */
    public void mauSagen(Spieler spieler, Spiel spiel);
}
