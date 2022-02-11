package de.htwberlin.regelnImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielerImpl.SpielerImpl;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RegelnImplTest {

    private RegelnService regeln;
    private SpielerService spielerService;
    private Spiel spiel;

    @Before
    public void setUp() {
        this.spiel = new Spiel();
        this.regeln = new RegelnImpl();
        this.spielerService = new SpielerImpl();
    }

    @Test
    public void testCheckCard1() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spiel.setSpieler(spieler);

        List<Karte> ablagestapel = new ArrayList<>();
        ablagestapel.add(new Karte("Herz", "10"));
        spiel.setAblagestapel(ablagestapel);
        Karte karte = new Karte("Pik", "10");
        Assert.assertTrue(regeln.checkCard(spiel, karte));
    }

    @Test
    public void testCheckCard2() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spiel.setSpieler(spieler);
        Karte karte = new Karte("Pik", "10");
        spiel.getSpieler().get(0).setHand(List.of(karte));

        List<Karte> ablagestapel = new ArrayList<>();
        ablagestapel.add(new Karte("Herz", "10"));
        spiel.setAblagestapel(ablagestapel);
        Assert.assertFalse(regeln.checkCard(spiel, karte));
    }

    @Test
    public void testCheckCard3() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spiel.setSpieler(spieler);
        Karte karte = new Karte("Pik", "10");
        spiel.getSpieler().get(0).setHand(List.of(karte));
        spiel.getSpieler().get(0).setMauGesagt(true);
        spiel.getSpieler().get(0).setZugZaehler(2);

        List<Karte> ablagestapel = new ArrayList<>();
        ablagestapel.add(new Karte("Herz", "10"));
        spiel.setAblagestapel(ablagestapel);
        Assert.assertFalse(regeln.checkCard(spiel, karte));
    }

    @Test
    public void testCheckCard4() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spiel.setSpieler(spieler);
        spiel.setZiehZaehler(2);

        List<Karte> ablagestapel = new ArrayList<>();
        ablagestapel.add(new Karte("Herz", "7"));
        spiel.setAblagestapel(ablagestapel);
        Karte karte = new Karte("Herz", "10");
        Assert.assertFalse(regeln.checkCard(spiel, karte));
    }

    @Test
    public void testCheckCard5() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spiel.setSpieler(spieler);

        List<Karte> ablagestapel = new ArrayList<>();
        ablagestapel.add(new Karte("Herz", "Bube"));
        spiel.setAblagestapel(ablagestapel);
        Karte karte = new Karte("Pik", "Bube");
        Assert.assertFalse(regeln.checkCard(spiel, karte));
    }

    @Test
    public void testCheckCard6() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spiel.setSpieler(spieler);
        spiel.setWunschfarbe("Pik");

        List<Karte> ablagestapel = new ArrayList<>();
        ablagestapel.add(new Karte("Herz", "9"));
        spiel.setAblagestapel(ablagestapel);
        Karte karte = new Karte("Pik", "10");
        Assert.assertTrue(regeln.checkCard(spiel, karte));
    }

    @Test
    public void testCheckCard7() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spiel.setSpieler(spieler);
        spiel.setWunschfarbe("Pik");

        List<Karte> ablagestapel = new ArrayList<>();
        ablagestapel.add(new Karte("Herz", "9"));
        spiel.setAblagestapel(ablagestapel);
        Karte karte = new Karte("Herz", "10");
        Assert.assertFalse(regeln.checkCard(spiel, karte));
    }

    @Test
    public void testCheckCard8() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spiel.setSpieler(spieler);

        List<Karte> ablagestapel = new ArrayList<>();
        ablagestapel.add(new Karte("Herz", "10"));
        spiel.setAblagestapel(ablagestapel);
        Karte karte = new Karte("Pik", "9");
        Assert.assertFalse(regeln.checkCard(spiel, karte));
    }

    @Test
    public void testHandleAss1() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spieler.add(new Spieler("spieler3", false));
        spiel.setSpieler(spieler);

        regeln.handleAss(spiel);
        int aktuellerSpieler = spiel.getAmZug();

        Assert.assertEquals(spieler.get(2), spieler.get(aktuellerSpieler));
    }

    @Test
    public void testHandleAss2() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spieler.add(new Spieler("spieler3", false));
        spiel.setSpieler(spieler);
        spiel.setAmZug(1);

        regeln.handleAss(spiel);
        int aktuellerSpieler = spiel.getAmZug();

        Assert.assertEquals(spieler.get(0), spieler.get(aktuellerSpieler));
    }

    @Test
    public void testHandleAss3() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spieler.add(new Spieler("spieler3", false));
        spiel.setSpieler(spieler);
        spiel.setAmZug(2);

        regeln.handleAss(spiel);
        int aktuellerSpieler = spiel.getAmZug();

        Assert.assertEquals(spieler.get(1), spieler.get(aktuellerSpieler));
    }

    @Test
    public void testHandleSieben() {

        regeln.handleSieben(spiel);

        Assert.assertEquals(2, spiel.getZiehZaehler());
    }

    @Test
    public void testHandleBube() {

        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1", false));
        spieler.add(new Spieler("spieler2", false));
        spiel.setSpieler(spieler);

        regeln.handleBube(spiel, "Pik");

        Assert.assertNotNull(spiel.getWunschfarbe());
    }

}