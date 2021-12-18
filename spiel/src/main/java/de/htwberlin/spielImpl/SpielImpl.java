package de.htwberlin.spielImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielService.SpielService;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpielImpl implements SpielService {

    private KartenService kartenService;
    private SpielerService spielerService;
    private RegelnService regelnService;
    private Spiel spiel;

    @Autowired
    public SpielImpl(KartenService kartenService, SpielerService spielerService, RegelnService regelnService) {
        this.kartenService = kartenService;
        this.spielerService = spielerService;
        this.regelnService = regelnService;
        //this.spiel = spiel;
    }

    public SpielImpl() {}

    public Spiel spielStarten(int anzahlSpieler) {

        spiel = new Spiel();

        List<Spieler> spieler = spielerService.spielerErzeugen(anzahlSpieler);

        spiel.setSpieler(spieler);
        spiel.setKartendeck(kartenService.deckErzeugen());
        kartenGeben(spiel);

        return spiel;
    }

    public void kartenGeben(Spiel spiel) {

        List<Karte> deck;

        for (Spieler spieler : spiel.getSpieler()) {
            deck = spiel.getKartendeck();
            List<Karte> deckCopy = new ArrayList<>(deck);
            List<Karte> hand = deckCopy.subList(0,7);
            deck.removeIf(x-> deckCopy.indexOf(x)>=0 && deckCopy.indexOf(x)<7);
            spiel.setKartendeck(deck);
            spieler.setHand(hand);
        }

        deck = spiel.getKartendeck();
        List<Karte> ablagestapel = new ArrayList<>();
        ablagestapel.add(deck.get(0));
        deck.remove(0);
        spiel.setKartendeck(deck);
        spiel.setAblagestapel(ablagestapel);
    }

    @Override
    public void ziehen(Spiel spiel) {

        List<Karte> deck = spiel.getKartendeck();

        if (deck.size() == 0) {
            List<Karte> ablagestapel = spiel.getAblagestapel();
            Karte letzteKarte = ablagestapel.get(ablagestapel.size()-1);
            ablagestapel.remove(ablagestapel.size()-1);
            kartenService.mischen(ablagestapel);
            spiel.setKartendeck(ablagestapel);
            spiel.setAblagestapel(List.of(letzteKarte));
        }

        deck = spiel.getKartendeck();
        Karte karte = deck.get(0);
        deck.remove(0);
        spiel.setKartendeck(deck);
        List<Karte> hand = spiel.getSpieler().get(spiel.getAmZug()).getHand();
        hand.add(karte);
        spiel.getSpieler().get(spiel.getAmZug()).setHand(hand);
        spiel.getSpieler().get(spiel.getAmZug()).setZugZaehler(spiel.getSpieler().get(spiel.getAmZug()).getZugZaehler() + 1);

    }

    @Override
    public void karteLegen(Karte karte, Spiel spiel) {

        int aktuellerSpieler = spiel.getAmZug();

        List<Karte> ablagestapel = new ArrayList<>(spiel.getAblagestapel());
        ablagestapel.add(karte);
        spiel.setAblagestapel(ablagestapel);
        if(karte.getWert().equals("Ass"))
            regelnService.handleAss(spiel);
        if(karte.getWert().equals("7"))
            regelnService.handleSieben(spiel);
        List<Karte> hand = spiel.getSpieler().get(aktuellerSpieler).getHand();
        hand.remove(karte);
        spiel.getSpieler().get(aktuellerSpieler).setHand(hand);
        spiel.getSpieler().get(aktuellerSpieler).setZugZaehler(spiel.getSpieler().get(aktuellerSpieler).getZugZaehler() + 1);

        }

    @Override
    public void mauSagen(Spiel spiel) {

        if (spiel.getSpieler().get(spiel.getAmZug()).getHand().size() != 2) {

            return;
        }
        spiel.getSpieler().get(spiel.getAmZug()).setMauGesagt(true);
    }

    @Override
    public void setKartenService(KartenService kartenService) {

        this.kartenService = kartenService;
    }

    @Override
    public void setSpielerService(SpielerService spielerService) {

        this.spielerService = spielerService;
    }

    @Override
    public void setRegelnService(RegelnService regelnService) {

        this.regelnService = regelnService;
    }

}
