package de.htwberlin.spielService;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;

public interface SpielService {

    /**
     * Es wird ein neues Spiel gestartet und die Karten ausgeteilt.
     *
     */
    public Spiel spielStarten();

    /**
     * Wird von der Methode spielStarten aufgerufen und gibt die Karten bei Spielbeginn an die Spieler aus.
     *
     * @param spiel - Das aktuelle Spiel.
     */
    public void kartenGeben(Spiel spiel);

    /**
     * Zieht eine Karte aus dem Kartendeck und fügt sie der Hand hinzu.
     *
     * @param spieler - Spieler welche die Karte zieht.
     * @param spiel - Das aktuelle Spiel.
     *
     * @return Neue Karte aus dem Kartendeck, welche der Hand hinzugefügt wird.
     */
    public Karte ziehen(Spieler spieler, Spiel spiel);

    /**
     * Sofern erlaubt, legt die Methode die vom Spieler gewünschte Karte auf den Ablagestapel und entfernt sie aus der Hand.
     *
     * @param karte - Die vom Spieler gewünschte Karte zur Ablage.
     * @param spieler - Der Spieler der mau sagt.
     * @param spiel - Das aktuelle Spiel.
     */
    public void karteLegen(Karte karte, Spieler spieler, Spiel spiel);

    /**
     * Der Spieler sagt "Mau" und kündigt so das Spielen seiner letzten Karte an.
     *
     * @param spieler - Der Spieler der mau sagt.
     * @param spiel - Das aktuelle Spiel.
     */
    public void mauSagen(Spieler spieler, Spiel spiel);

    public void setKartenService(KartenService kartenService);

    public void setSpielerService(SpielerService spielerService);

    public void setRegelnService(RegelnService regelnService);

}
