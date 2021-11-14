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

    public Spieler(String name) {
        this.name = name;
    }

    private List<Karte> hand = new ArrayList<Karte>();

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
    }

    public List<Karte> getHand() {
        return hand;
    }

    public void setHand(List<Karte> hand) {
        this.hand = hand;
    }
}
