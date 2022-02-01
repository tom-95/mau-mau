package de.htwberlin.regelnService;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.spielerService.Spieler;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Spiel {

    /**
     * ID des Spiels für die Datenbank.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Beinhaltet alle Spieler die am aktuellen Spiel teilnehmen
     */
    @OneToMany(cascade = { CascadeType.MERGE })
    public List<Spieler> spieler;

    /**
     * Beinhaltet alle noch nicht gezogenen oder ausgeteilten Karten.
     */
    @OneToMany(cascade = { CascadeType.MERGE })
    public List<Karte> kartendeck;

    /**
     * Beinhaltet alle Karten welche von den Spielern abelegt wurden.
     */
    @OneToMany(cascade = { CascadeType.MERGE })
    private List<Karte> ablagestapel = new ArrayList<>();

    /**
     * Speichert den Index des Spielers, der am Zug ist.
     */
    private int amZug = 0;

    /**
     * Zählt die Anzahl Karten welche vom nächsten Spieler gezogen werden müssen.
     */
    private int ziehZaehler = 0;

    /**
     * Speichert die gewünschte Farbe welche vom nächsten Spieler gelegt werden muss.
     */
    private String wunschfarbe = null;


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

    public int getAmZug() {
        return amZug;
    }

    public void setAmZug(int amZug) {
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

    public void setWunschfarbe(String wunschfarbe) { this.wunschfarbe = wunschfarbe; }

    public List<Karte> getKartendeck() { return kartendeck; }

    public void setKartendeck(List<Karte> kartendeck) { this.kartendeck = kartendeck; }
}
