package de.htwberlin.regelnImpl;

import de.htwberlin.kartenImpl.KartenImpl;
import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielerImpl.SpielerImpl;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;

// @RunWith(MockitoJUnitRunner.class)
public class RegelnImplTest {

    private RegelnService regeln;
    private SpielerService spielerService;
    private Karte karte;
    private Spiel spiel;

//  @InjectMocks
//  private Spiel spiel = new Spiel();

    @Before
    public void setUp() {
//      this.spielMock = Mockito.mock(Spiel.class);
        this.spiel = new Spiel();
        this.regeln = new RegelnImpl();
        this.spielerService = new SpielerImpl();
    }

    @Test
    public void testCheckCard() {
        List<Karte> ablagestapel = new ArrayList<>();
        ablagestapel.add(new Karte("zehn", "herz"));
        spiel.setAblagestapel(ablagestapel);
        Karte karte = new Karte("zehn", "schaufel");
        Assert.assertTrue(regeln.checkCard(spiel, karte));
    }

    @Test
    public void testHandleAss() {
        List<Spieler> spieler = new ArrayList<>();
        karte = new Karte("Ass", "Herz");

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

//    @Test
//    public void testHandleBube() {
//
//        List<Spieler> spieler = new ArrayList<>();
//        karte = new Karte("Bube", "Herz");
//
//        spieler.add(new Spieler("spieler1"));
//        spieler.add(new Spieler("spieler2"));
//        spiel.setSpieler(spieler);
//
//        regeln.handleBube(spiel);
//
//        Assert.assertNotNull(spiel.getWunschfarbe());
//    }
}