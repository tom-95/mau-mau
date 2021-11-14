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
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SpielImplTest {

    private Karte karte;
    private Spiel spiel;
    private SpielService spielService;
    private KartenService kartenMock;
    private SpielerService spielerMock;

    @Before
    public void setUp() {
        this.spielerMock = Mockito.mock(SpielerService.class);
        this.kartenMock = Mockito.mock(KartenService.class);
        this.spiel = new Spiel();
        this.spielService = new SpielImpl();
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

        Assert.assertNotNull(spielService.spielStarten(kartenMock, spielerMock));
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