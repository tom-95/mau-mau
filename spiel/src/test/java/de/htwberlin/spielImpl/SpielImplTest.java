package de.htwberlin.spielImpl;

import de.htwberlin.kartenImpl.KartenImpl;
import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.spielService.Spiel;
import de.htwberlin.spielService.SpielService;
import de.htwberlin.spielerImpl.SpielerImpl;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SpielImplTest {

    private Karte karte;
    private Spiel spiel;
    private SpielService spielService;
    private KartenService kartenService;
    private SpielerService spielerService;

    @Before
    public void setUp() {
//      this.spielMock = Mockito.mock(Spiel.class);
        this.spiel = new Spiel();
        this.spielService = new SpielImpl();
        this.kartenService = new KartenImpl();
        this.spielerService = new SpielerImpl();
    }

    @Test
    public void testSpielStarten() {
        Assert.assertNotNull(spielService.spielStarten(kartenService, spielerService));
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
}