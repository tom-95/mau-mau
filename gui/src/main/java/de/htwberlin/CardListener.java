package de.htwberlin;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielService.SpielService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardListener implements ActionListener {

        Spielfeld spielfeld;
        Karte karte;
        Spiel spiel;
        SpielService spielService;
        JButton button;
        RegelnService regelnService;

        public CardListener(Spielfeld spielfeld, Karte karte, Spiel spiel, SpielService spielService, JButton button, RegelnService regelnService) {
                this.spielfeld = spielfeld;
                this.karte = karte;
                this.spiel = spiel;
                this.spielService = spielService;
                this.button = button;
                this.regelnService = regelnService;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (regelnService.checkCard(spiel, karte)) {
                    int aktuellerSpieler = spiel.getAmZug();
                    spielService.karteLegen(karte, spiel);
                    if (karte.getWert().equals("Bube")) {
                            String farbe = spielfeld.farbeWaehlen();
                            regelnService.handleBube(spiel, farbe);
                            spielfeld.gewaehlteFarbe(farbe);
                    } else {
                            spielfeld.gewaehlteFarbe("");
                    }
                    spielfeld.letzteKarteAendern(karte);
                    if (spiel.getSpieler().get(aktuellerSpieler).getHand().size() == 0) {
                            spielfeld.handAktualisieren();
                            spielfeld.showWinningMessage(spiel.getSpieler().get(aktuellerSpieler));
                    }
                    if (!karte.getWert().equals("Ass")) {
                            spielfeld.naechsterSpieler();
                    }
            }

            spielfeld.handAktualisieren();

        }
}
