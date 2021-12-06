package de.htwberlin.regelnService;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.spielerService.Spieler;

import java.util.ArrayList;
import java.util.List;

public class Spiel {

    /**
     * Beinhaltet alle Spieler die am aktuellen Spiel teilnehmen
     */
    public List<Spieler> spieler;

    /**
     * Beinhaltet alle Karten welche von den Spielern abelegt wurden.
     */
    private List<Karte> ablagestapel = new ArrayList<>();

    /**
     * Zeigt die Richtung des Spielverlaufs (im Uhrzeigersinn (true) oder gegen den Uhrzeigersinn (false)).
     */
    public boolean richtungUhrzeiger;

    /**
     * Speichert den Spieler, der am Zug ist.
     */
    private Spieler amZug;

    /**
     * Zählt die Anzahl Karten welche vom nächsten Spieler gezogen werden müssen.
     */
    private int ziehZaehler;

    /**
     * Speichert die gewünschte Farbe welche vom nächsten Spieler gelegt werden muss.
     */
    private String wunschfarbe;


    public List<Spieler> getSpieler() {
        return spieler;
    }

    public void setSpieler(List<Spieler> spieler) {
        this.spieler = spieler;
    }

    public List<Karte> getAblagestapel() {
        return ablagestapel;
    }

    public void setAblagestapel(List<Karte> ablagestapel) {
        this.ablagestapel = ablagestapel;
    }

    public boolean isRichtungUhrzeiger() {
        return richtungUhrzeiger;
    }

    public void setRichtungUhrzeiger(boolean richtungUhrzeiger) {
        this.richtungUhrzeiger = richtungUhrzeiger;
    }

    public Spieler getAmZug() {
        return amZug;
    }

    public void setAmZug(Spieler amZug) {
        this.amZug = amZug;
    }

    public int getZiehZaehler() {
        return ziehZaehler;
    }

    public void setZiehZaehler(int ziehZaehler) {
        this.ziehZaehler = ziehZaehler;
    }

    public String getWunschfarbe() {
        return wunschfarbe;
    }

    public void setWunschfarbe(String wunschfarbe) {
        this.wunschfarbe = wunschfarbe;
    }
}
