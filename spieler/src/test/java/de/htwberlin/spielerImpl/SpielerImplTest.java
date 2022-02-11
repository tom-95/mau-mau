package de.htwberlin.spielerImpl;

import de.htwberlin.kartenService.KartenService;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerRepository;
import de.htwberlin.spielerService.SpielerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

public class SpielerImplTest {

    @InjectMocks
    private SpielerService spielerService = new SpielerImpl();


    @Before
    public void setUp() {
        spielerService = new SpielerImpl();
    }

    @Test
    public void testSpielerErzeugen() {

        List<Spieler> spielerListe = spielerService.spielerErzeugen(2, 0);

        Assert.assertEquals("Spieler 2", spielerListe.get(spielerListe.size()-1).getName());

    }

    @Test
    public void testSpielerErzeugenKI() {

        List<Spieler> spielerListe = spielerService.spielerErzeugen(1, 3);

        Assert.assertEquals("KI-Spieler 3", spielerListe.get(spielerListe.size()-1).getName());
    }

}