package de.htwberlin.spielerImpl;

import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SpielerImplTest {

    private SpielerService spielerService = new SpielerImpl();

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