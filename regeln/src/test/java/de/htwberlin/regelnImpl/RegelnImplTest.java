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
    public void testCheckCard() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1"));
        spieler.add(new Spieler("spieler2"));
        spiel.setSpieler(spieler);

        List<Karte> ablagestapel = new ArrayList<>();
        ablagestapel.add(new Karte("Herz", "10"));
        spiel.setAblagestapel(ablagestapel);
        Karte karte = new Karte("Schaufel", "10");
        Assert.assertTrue(regeln.checkCard(spiel, karte));
    }

    @Test
    public void testHandleAss() {
        List<Spieler> spieler = new ArrayList<>();

        spieler.add(new Spieler("spieler1"));
        spieler.add(new Spieler("spieler2"));
        spieler.add(new Spieler("spieler3"));
        spiel.setSpieler(spieler);

        regeln.handleAss(spiel);
        int aktuellerSpieler = spiel.getAmZug();

        Assert.assertEquals(spieler.get(2), spieler.get(aktuellerSpieler));
    }

    @Test
    public void testHandleSieben() {

        regeln.handleSieben(spiel);

        Assert.assertEquals(2, spiel.getZiehZaehler());
    }

    @Test
    public void testHandleBube() {

        regeln.handleBube(spiel, "Pik");

        Assert.assertNotNull(spiel.getWunschfarbe());
    }

}