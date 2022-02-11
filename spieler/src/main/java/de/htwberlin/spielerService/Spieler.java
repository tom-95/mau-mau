package de.htwberlin.spielerService;

import de.htwberlin.kartenService.Karte;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Spieler {

    /**
     * ID des Spielers für die Datenbank.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Speichert den Namen des Spielers.
     */
    private String name;

    /**
     * Zeigt an, ob der Spieler ein virtueller Spieler ist.
     */
    private boolean ki;

    /**
     * Zeigt an, ob der Spieler bereits in einem laufenden Online-Spiel verwendet wird.
     */
    private boolean spielerInUse = false;

    /**
     * Speichert, ob der Spieler vor dem legen der nächsten Karte Mau gesagt hat.
     */
    private boolean mauGesagt = false;

    /**
     * Beinhaltet alle Karten welche sich in der Hand des Spielers befinden.
     */
    @ElementCollection
    private List<Karte> hand = new ArrayList<>();

    /**
     * Beinhaltet die Anzahl der gelegten Karten seit dem letzten Mau sagen.
     */
    private Integer zugZaehler = 0;

    /**
     * Konstruktor der Klasse Spieler.
     * @param name Name des Spielers
     */
    public Spieler(String name, boolean ki) {
        this.name = name;
        this.ki = ki;
    }

    protected Spieler() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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
        this.setZugZaehler(0);
    }

    public List<Karte> getHand() {
        return hand;
    }

    public void setHand(List<Karte> hand) {
        this.hand = hand;
    }

    public Integer getZugZaehler() {
        return zugZaehler;
    }

    public void setZugZaehler(Integer zugZaehler) {
        this.zugZaehler = zugZaehler;
    }

    public boolean isKi() { return ki; }

    public void setKi(boolean ki) { this.ki = ki; }

    public boolean isSpielerInUse() { return spielerInUse; }

    public void setSpielerInUse(boolean spielerInUse) { this.spielerInUse = spielerInUse; }
}
