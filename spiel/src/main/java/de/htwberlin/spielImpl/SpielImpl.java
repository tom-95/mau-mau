package de.htwberlin.spielImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielService.SpielService;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;

import java.util.ArrayList;
import java.util.List;

public class SpielImpl implements SpielService {

    private KartenService kartenService;
    private SpielerService spielerService;
    private RegelnService regelnService;

    public Spiel spielStarten(int anzahlSpieler) {

        Spiel spiel = new Spiel();

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
            List<Karte> hand = deck.subList(0,7);
            List<Karte> deckCopy = deck;
            deck.removeIf(x-> deckCopy.indexOf(x)>=0&& deckCopy.indexOf(x)<7);
            spiel.setKartendeck(deck);
            spieler.setHand(hand);
        }

        deck = spiel.getKartendeck();
        List<Karte> ablagestapel = new ArrayList<>();
        ablagestapel.add(deck.get(0));
        List<Karte> deckCopy = deck;
        deck.removeIf(x-> deckCopy.indexOf(x)==0);
        spiel.setKartendeck(deck);
        spiel.setAblagestapel(ablagestapel);

    }

    @Override
    public void ziehen(Spiel spiel) {

        List<Karte> deck = spiel.getKartendeck();
        Karte karte = deck.get(0);
        deck.remove(0);
        spiel.setKartendeck(deck);
        List<Karte> hand = spiel.getSpieler().get(spiel.getAmZug()).getHand();
        hand.add(karte);
        spiel.getSpieler().get(spiel.getAmZug()).setHand(hand);

    }

    @Override
    public void karteLegen(Karte karte, Spiel spiel) {

        if(regelnService.checkCard(spiel, karte)) {
            List<Karte> ablagestapel = spiel.getAblagestapel();
            ablagestapel.add(karte);
            spiel.setAblagestapel(ablagestapel);
            if(karte.getWert().equals("Ass"))
                regelnService.handleAss(spiel);
            if(karte.getWert().equals("Sieben"))
                regelnService.handleSieben(spiel);
            //if(karte.getWert().equals("Bube"))
                //regelnService.handleBube(spiel);
        }

        int aktuellerSpieler = spiel.getAmZug();

        if(!karte.getWert().equals("Ass")) {
            if (aktuellerSpieler + 1 < spiel.getSpieler().size())
                aktuellerSpieler += 1;
            else
                aktuellerSpieler = 0;
            spiel.setAmZug(aktuellerSpieler);
        }
    }

    @Override
    public void mauSagen(Spiel spiel) {

        spiel.getSpieler().get(spiel.getAmZug()).setMauGesagt(true);

    }

    @Override
    public void setKartenService(KartenService kartenService) {

    }

    @Override
    public void setSpielerService(SpielerService spielerService) {

    }

    @Override
    public void setRegelnService(RegelnService regelnService) {
        this.regelnService = regelnService;
    }
}
