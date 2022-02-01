package de.htwberlin.kartenService;

import javax.persistence.*;

@Embeddable
public class Karte {

    /**
     * Speichert die Farbe der Karte.
     */
    private String farbe;

    /**
     * Speichert den Wert der Karte.
     */
    private String wert;

    /**
     * Konstruktor der Klasse Karte.
     * @param wert Wert der Karte
     * @param farbe Farbe der Karte
     */
    public Karte(String farbe, String wert) {
        this.wert = wert;
        this.farbe = farbe;
    }

    protected Karte() {}

    public String getWert() {
        return wert;
    }

    public void setWert(String wert) {
        this.wert = wert;
    }

    public String getFarbe() { return farbe; }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

}
