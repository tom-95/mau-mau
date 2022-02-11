package de.htwberlin.regelnImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.regelnService.VirtuellerSpielerService;
import de.htwberlin.spielerService.Spieler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VirtuellerSpielerImplTest {

    private Spiel spiel;
    private Spieler spieler;
    @InjectMocks
    private VirtuellerSpielerService virtuellerSpielerService = new VirtuellerSpielerImpl();
    @Mock
    private RegelnService regelnMock;

    @Before
    public void setUp() {

        this.spiel = new Spiel();
        this.spieler = new Spieler("KI", true);

    }

    @Test
    public void testKarteWaehlen1() {

        Karte karte = new Karte("Pik", "10");
        spieler.setHand(List.of(karte));
        when(regelnMock.checkCard(spiel, karte)).thenReturn(true);

        Assert.assertEquals(karte, virtuellerSpielerService.karteWaehlen(spiel, spieler));

    }

    @Test
    public void testKarteWaehlen2() {

        Karte karte = new Karte("Pik", "10");
        spieler.setHand(List.of(karte));
        when(regelnMock.checkCard(spiel, karte)).thenReturn(false);

        Assert.assertNull(virtuellerSpielerService.karteWaehlen(spiel, spieler));

    }

    @Test
    public void testFarbeWaehlen() {

        Karte karte1 = new Karte("Pik", "10");
        Karte karte2 = new Karte("Herz", "9");
        Karte karte3 = new Karte("Pik", "Dame");
        spieler.setHand(List.of(karte1, karte2, karte3));

        Assert.assertEquals("Pik", virtuellerSpielerService.farbeWaehlen(spiel, spieler));

    }
}