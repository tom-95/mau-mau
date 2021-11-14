package de.htwberlin.kartenService;

import java.util.List;

public class Karte {

    /**
     * Speichert den Wert der Karte.
     */
    private String wert;

    /**
     * Speichert die Farbe der Karte.
     */
    private String farbe;

    /**
     * Beinhaltet alle noch nicht gezogenen oder ausgeteilten Karten.
     */
    public List<Karte> kartendeck;

    /**
     * Konstruktor der Klasse Karte.
     * @param wert Wert der Karte
     * @param farbe Farbe der Karte
     */
    public Karte(String wert, String farbe) {
        this.wert = wert;
        this.farbe = farbe;
    }

    public String getWert() {
        return wert;
    }

    public void setWert(String wert) {
        this.wert = wert;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public List<Karte> getKartendeck() {
        return kartendeck;
    }

    public void setKartendeck(List<Karte> kartendeck) {
        this.kartendeck = kartendeck;
    }
}
