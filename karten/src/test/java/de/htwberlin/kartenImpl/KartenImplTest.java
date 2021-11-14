package de.htwberlin.kartenImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.spielService.Spiel;
import de.htwberlin.spielerImpl.SpielerImpl;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class KartenImplTest {

    private KartenService service;
    private Spieler spieler;
    private Spiel spiel;

    @Before
    public void setUp() {
        // Aufruf vor jedem Testfall
        service = new KartenImpl();
    }

    @Test
    public void testDeckErzeugen() {
        List<Karte> deck = service.deckErzeugen();
        Assert.assertEquals(32, deck.size());
    }

    @Test
    public void testMischen() {
        List<Karte> kartenDeck = service.deckErzeugen();
        List<Karte> kartenDeckCopy = new ArrayList<Karte>(kartenDeck);
        service.mischen(kartenDeck);
        Assert.assertNotEquals(kartenDeck, kartenDeckCopy);
    }

    @Test
    public void testZiehen() {
        spieler = new Spieler("testSpieler");
        spiel = new Spiel();
        List<Karte> hand = spieler.getHand();
        Integer size = hand.size();
        service.ziehen(spieler, spiel);
        List<Karte> handAfter = spieler.getHand();
        Integer sizeAfter = handAfter.size();
        Assert.assertNotEquals(size, sizeAfter);
    }
}