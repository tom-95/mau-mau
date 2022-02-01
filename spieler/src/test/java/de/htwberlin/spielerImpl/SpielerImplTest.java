package de.htwberlin.spielerImpl;

import de.htwberlin.spielerService.SpielerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpielerImplTest {

    SpielerService spielerService;

    @Before
    public void setUp() {

        spielerService = new SpielerImpl();

    }

    @Test
    public void testSpielerErzeugen() {

        Assert.assertNotNull(spielerService.spielerErzeugen(2, 0));

    }

}