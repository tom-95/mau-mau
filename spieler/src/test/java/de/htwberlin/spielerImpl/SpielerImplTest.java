package de.htwberlin.spielerImpl;

import de.htwberlin.kartenImpl.KartenImpl;
import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnImpl.RegelnImpl;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.spielImpl.SpielImpl;
import de.htwberlin.spielService.Spiel;
import de.htwberlin.spielService.SpielService;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SpielerImplTest {

    private Karte karte;
    private Spiel spiel;
    private SpielService spielService;
    private SpielerService spielerService;
    private KartenService kartenService;
    private RegelnService regelnServiceMock;

    @Before
    public void setUp() {
        this.spiel = new Spiel();
        this.spielService = new SpielImpl();
        this.kartenService = new KartenImpl();
        this.spielerService = new SpielerImpl();
        this.regelnServiceMock = Mockito.mock(RegelnService.class);
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

        spielerService.karteLegen(spiel.getSpieler().get(0).getHand().get(1), spiel.getSpieler().get(0), spiel, regelnServiceMock);

        Assert.assertEquals(1, spiel.getSpieler().get(0).getHand().size());
        Assert.assertEquals(1, spiel.getAblagestapel().size());
    }

    @Test
    public void testMauSagen() {

        List<Spieler> spieler = new ArrayList<>();
        spieler.add(new Spieler("spieler1"));
        spieler.add(new Spieler("spieler2"));

        spielerService.mauSagen(spieler.get(0), spiel);

        Assert.assertTrue(spieler.get(0).isMauGesagt());
    }
}