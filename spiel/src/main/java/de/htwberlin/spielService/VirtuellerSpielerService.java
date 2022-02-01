package de.htwberlin.spielService;

import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielerService.Spieler;

public interface VirtuellerSpielerService {

    void zugDurchfuehren(Spiel spiel, Spieler spieler);

}
