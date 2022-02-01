package de.htwberlin.spielService;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielerService.SpielerService;

public interface SpielService {

    /**
     * Es wird ein neues Spiel gestartet und die Karten ausgeteilt.
     *
     */
    public Spiel spielStarten(int anzahlSpieler, int anzahlVirtuellerSpieler);

    /**
     * Wird von der Methode spielStarten aufgerufen und gibt die Karten bei Spielbeginn an die Spieler aus.
     *
     * @param spiel - Das aktuelle Spiel.
     */
    public void kartenGeben(Spiel spiel);

    /**
     * Zieht eine Karte aus dem Kartendeck und fügt sie der Hand hinzu.
     *
     * @param spiel - Das aktuelle Spiel.
     */
    public void ziehen(Spiel spiel);

    /**
     * Sofern erlaubt, legt die Methode die vom Spieler gewünschte Karte auf den Ablagestapel und entfernt sie aus der Hand.
     *
     * @param karte - Die vom Spieler gewünschte Karte zur Ablage.
     * @param spiel - Das aktuelle Spiel.
     */
    public void karteLegen(Karte karte, Spiel spiel);

    /**
     * Der Spieler sagt "Mau" und kündigt so das Spielen seiner letzten Karte an.
     *
     * @param spiel - Das aktuelle Spiel.
     */
    public void mauSagen(Spiel spiel);

    public void spielSpeichern(Spiel spiel);

    public void setKartenService(KartenService kartenService);

    public void setSpielerService(SpielerService spielerService);

    public void setRegelnService(RegelnService regelnService);

}
