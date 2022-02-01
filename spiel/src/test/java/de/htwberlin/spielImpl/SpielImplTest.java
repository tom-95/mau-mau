package de.htwberlin.spielImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielService.SpielService;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpielImplTest {

    private Spiel spiel;
    private Spieler spieler;
    @InjectMocks
    private SpielService spielService = new SpielImpl();
    @Mock
    private KartenService kartenMock;
    @Mock
    private SpielerService spielerMock;

    @Before
    public void setUp() {

        this.spiel = new Spiel();

    }

    @Test
    public void testSpielStarten() {
        Spieler spieler1 = new Spieler("Spieler1", false);
        Spieler spieler2 = new Spieler("Spieler2", false);
        List<Spieler> spieler = new ArrayList<>();
        spieler.add(spieler1);
        spieler.add(spieler2);
        List<Karte> deck = new ArrayList<>();
        for(int i=0; i<32; i++)
            deck.add(new Karte("", ""));

        when(spielerMock.spielerErzeugen(2, 0)).thenReturn(spieler);
        when(kartenMock.deckErzeugen()).thenReturn(deck);

        spielService.setSpielerService(spielerMock);
        spielService.setKartenService(kartenMock);

        Assert.assertNotNull(spielService.spielStarten(2, 0));
    }

    @Test
    public void testKartenGeben() {
        List<Karte> deck = new ArrayList<>();
        for(int i=0; i<32; i++)
            deck.add(new Karte("", ""));
        spiel.setKartendeck(deck);
        List<Spieler> spieler = new ArrayList<>();
        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spiel.setSpieler(spieler);
        spielService.kartenGeben(spiel);

        Assert.assertEquals(7, spiel.getSpieler().get(0).getHand().size());
        Assert.assertEquals(7, spiel.getSpieler().get(1).getHand().size());
    }

    @Test
    public void testZiehen() {
        List<Karte> deck = new ArrayList<>();
        deck.add(new Karte("Sieben", "Herz"));
        deck.add(new Karte("Zehn", "Schaufel"));
        spiel.setKartendeck(deck);
        spieler = new Spieler("testSpieler", false);
        List<Karte> hand = spieler.getHand();
        Integer size = hand.size();
        spiel.setSpieler(List.of(spieler));
        spielService.ziehen(spiel);
        List<Karte> handAfter = spieler.getHand();
        Integer sizeAfter = handAfter.size();
        Assert.assertNotEquals(size, sizeAfter);
    }

    @Test
    public void testKarteLegen() {

        List<Spieler> spieler = new ArrayList<>();
        List<Karte> hand = new ArrayList<>();
        Karte karte1 = new Karte("Sieben", "Herz");
        Karte karte2 = new Karte("Zehn", "Schaufel");
        hand.add(karte1);
        hand.add(karte2);

        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spiel.setSpieler(spieler);
        spiel.getSpieler().get(0).setHand(hand);

        List<Karte> ablagestapel = new ArrayList<>();
        ablagestapel.add(new Karte("Zehn", "Herz"));
        spiel.setAblagestapel(ablagestapel);

        spielService.karteLegen(spiel.getSpieler().get(0).getHand().get(1), spiel);

        Assert.assertEquals(1, spiel.getSpieler().get(0).getHand().size());
        Assert.assertEquals(2, spiel.getAblagestapel().size());
    }

    @Test
    public void testMauSagen() {

        List<Spieler> spieler = new ArrayList<>();
        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spiel.setSpieler(spieler);
        List<Karte> hand = new ArrayList<>();
        Karte karte1 = new Karte("Sieben", "Herz");
        Karte karte2 = new Karte("Zehn", "Schaufel");
        hand.add(karte1);
        hand.add(karte2);
        spieler.get(0).setHand(hand);

        spielService.mauSagen(spiel);

        Assert.assertTrue(spiel.getSpieler().get(0).isMauGesagt());
    }
}