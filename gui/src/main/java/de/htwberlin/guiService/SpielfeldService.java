package de.htwberlin.guiService;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.spielerService.Spieler;

public interface SpielfeldService {

    void spielStarten();

    void spielfeldAnzeigen();

    String farbeWaehlen();

    void gewaehlteFarbe(String farbe);

    void letzteKarteAendern(Karte karte);

    void handAktualisieren();

    void karteAnzeigen(Karte karte);

    void showWinningMessage(Spieler spieler);

    void naechsterSpieler();

    void karteLegen(Karte karte);

}
