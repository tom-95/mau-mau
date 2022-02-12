package de.htwberlin.guiService;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielerService.Spieler;

public interface SpielfeldService {

    /**
     * Ein neues Spiel wird gestartet.
     */
    void spielStarten();

    /**
     * Das Spielfeld wird angezeigt.
     */
    void spielfeldAnzeigen();

    /**
     * Der Spieler wird nach dem Legen eines Buben aufgefordert, eine Farbe zu wählen.
     *
     * @return String mit der gewählten Farbe.
     */
    String farbeWaehlen();

    /**
     * Die oberste Karte auf dem Ablagestapel wird angepasst.
     *
     * @param karte - Die letzte Karte.
     */
    void letzteKarteAendern(Karte karte);

    /**
     * Die Hand des Spielers wird aktualisiert.
     *
     * @param online - Gibt an ob es sich um ein Online-Spiel handelt.
     */
    void handAktualisieren(boolean online);

    /**
     * Die Karte wird im GUI angezeigt.
     *
     * @param karte - Die anzuzeigende Karte.
     */
    void karteAnzeigen(Karte karte);

    /**
     * Benachrichtigung, sobald ein Spieler gewonnen hat.
     *
     * @param spieler - Der Spieler welcher gewonnen hat.
     */
    void showWinningMessage(Spieler spieler);

    /**
     * Der nächste Spieler wird ausgewählt.
     */
    void naechsterSpieler();

    /**
     * Eine Karte wird gelegt.
     *
     * @param karte - Die zu legende Karte.
     */
    void karteLegen(Karte karte);

    /**
     * Ein neues Spiel wird erstellt.
     */
    void neuesSpiel();

    /**
     * Ein nicht beendetes Spiel wird fortgesetzt.
     *
     * @param spiel - Das fortzusetzende Spiel.
     */
    void spielFortsetzen(Spiel spiel);

    /**
     * Spiel und Spieler werden in der Datenbank gespeichert.
     */
    void speichern();

    /**
     * Eine Karte wird gezogen.
     */
    void ziehen();

    /**
     * Der Zug des KI-Spielers wird durchgeführt, wenn nötig.
     */
    void kiZugDurchfuehren();

    /**
     * Die Anzahl der Karten aller Spieler werden im GUI angezeigt.
     */
    void anzahlKartenSpielerAnzeigen();

}
