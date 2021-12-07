package de.htwberlin.spielImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielService.SpielService;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class SpielImplTest {

    private Karte karte;
    private Spiel spiel;
    private Spieler spieler;
    private SpielService spielService;
    private KartenService kartenMock;
    private SpielerService spielerMock;
    private RegelnService regelnServiceMock;

    @Before
    public void setUp() {
        this.spielerMock = Mockito.mock(SpielerService.class);
        this.kartenMock = Mockito.mock(KartenService.class);
        this.spiel = new Spiel();
        this.spielService = new SpielImpl();
        this.regelnServiceMock = Mockito.mock(RegelnService.class);
    }

    @Test
    public void testSpielStarten() {
        Spieler spieler1 = new Spieler("Spieler1");
        Spieler spieler2 = new Spieler("Spieler2");
        List<Spieler> spieler = new ArrayList<>();
        spieler.add(spieler1);
        spieler.add(spieler2);
        List<Karte> karten = new ArrayList<>();

        Mockito.when(spielerMock.spielerErzeugen(2)).thenReturn(spieler);
        Mockito.when(kartenMock.deckErzeugen()).thenReturn(karten);

        spielService.setSpielerService(spielerMock);
        spielService.setKartenService(kartenMock);

        Assert.assertNotNull(spielService.spielStarten());
    }

    @Test
    public void testKartenGeben() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1"));
        spieler.add(new Spieler("spieler2"));
        spiel.setSpieler(spieler);
        spielService.kartenGeben(spiel);

        Assert.assertEquals(7, spiel.getSpieler().get(0).getHand().size());
        Assert.assertEquals(7, spiel.getSpieler().get(1).getHand().size());
    }

    @Test
    public void testZiehen() {
        spieler = new Spieler("testSpieler");
        spiel = new Spiel();
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
        hand.add(new Karte("Sieben", "Herz"));
        hand.add(new Karte("Zehn", "Schaufel"));

        spieler.add(new Spieler("spieler1"));
        spieler.add(new Spieler("spieler2"));
        spiel.setSpieler(spieler);
        spiel.getSpieler().get(0).setHand(hand);

        Mockito.when(regelnServiceMock.checkCard(spiel, new Karte("Zehn", "Schaufel"))).thenReturn(true);

        spielService.setRegelnService(regelnServiceMock);

        spielService.karteLegen(spiel.getSpieler().get(0).getHand().get(1), spiel);

        Assert.assertEquals(1, spiel.getSpieler().get(0).getHand().size());
        Assert.assertEquals(1, spiel.getAblagestapel().size());
    }

    @Test
    public void testMauSagen() {

        List<Spieler> spieler = new ArrayList<>();
        spieler.add(new Spieler("spieler1"));
        spieler.add(new Spieler("spieler2"));

        spielService.mauSagen(spieler.get(0), spiel);

        Assert.assertTrue(spieler.get(0).isMauGesagt());
    }
}