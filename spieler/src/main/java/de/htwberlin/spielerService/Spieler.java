package de.htwberlin.spielerService;

import de.htwberlin.kartenService.Karte;

import java.util.ArrayList;
import java.util.List;

public class Spieler {

    /**
     * Speichert den Namen des Spielers.
     */
    private String name;

    /**
     * Speichert, ob der Spieler vor dem legen der nächsten Karte Mau gesagt hat.
     */
    private boolean mauGesagt = false;

    /**
     * Beinhaltet alle Karten welche sich in der Hand des Spielers befinden.
     */
    private List<Karte> hand = new ArrayList<Karte>();

    /**
     * Beinhaltet die Anzahl der gelegten Karten seit dem letzten Mau sagen.
     */
    private Integer kartenZaehler = 0;

    /**
     * Konstruktor der Klasse Spieler.
     * @param name Name des Spielers
     */
    public Spieler(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMauGesagt() {
        return mauGesagt;
    }

    public void setMauGesagt(boolean mauGesagt) {
        this.mauGesagt = mauGesagt;
        this.setKartenZaehler(0);
    }

    public List<Karte> getHand() {
        return hand;
    }

    public void setHand(List<Karte> hand) {
        this.hand = hand;
    }

    public Integer getKartenZaehler() {
        return kartenZaehler;
    }

    public void setKartenZaehler(Integer kartenZaehler) {
        this.kartenZaehler = kartenZaehler;
    }
}
