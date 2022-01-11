package de.htwberlin.spielImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielService.SpielService;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpielImpl implements SpielService {
    private static Logger LOGGER = LogManager.getLogger(SpielImpl.class);

    private KartenService kartenService;
    private SpielerService spielerService;
    private RegelnService regelnService;

    @Autowired
    public SpielImpl(KartenService kartenService, SpielerService spielerService, RegelnService regelnService) {
        this.kartenService = kartenService;
        this.spielerService = spielerService;
        this.regelnService = regelnService;

    }

    public SpielImpl() {}

    public Spiel spielStarten(int anzahlSpieler) {
        LOGGER.debug("Spiel wird gestartet.");

        Spiel spiel = new Spiel();

        List<Spieler> spieler = spielerService.spielerErzeugen(anzahlSpieler);

        spiel.setSpieler(spieler);
        spiel.setKartendeck(kartenService.deckErzeugen());
        kartenGeben(spiel);

        return spiel;
    }

    public void kartenGeben(Spiel spiel) {
        LOGGER.debug("Karten werden ausgegeben.");

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
        int i = 0;
        while(deck.get(i).getWert().equals("Bube")) {
            i++;
        }
        Karte startKarte = deck.get(i);
        ablagestapel.add(startKarte);
        deck.remove(i);
        spiel.setKartendeck(deck);
        spiel.setAblagestapel(ablagestapel);
    }

    @Override
    public void ziehen(Spiel spiel) {
        LOGGER.debug("Karte wird gezogen");

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

        LOGGER.info("Es befinden sich noch " + deck.size() + " Karten im Deck.");

    }

    @Override
    public void karteLegen(Karte karte, Spiel spiel) {
        LOGGER.debug("Karte wird gelegt.");

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
        LOGGER.debug("Mau sagen aufgerufen.");

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
