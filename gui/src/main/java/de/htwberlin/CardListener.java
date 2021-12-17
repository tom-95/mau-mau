package de.htwberlin;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnImpl.RegelnImpl;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielService.SpielService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CardListener implements ActionListener {

        Spielfeld spielfeld;
        Karte karte;
        Spiel spiel;
        SpielService spielService;
        JButton button;
        RegelnService regelnService = new RegelnImpl();

        public CardListener(Spielfeld spielfeld, Karte karte, Spiel spiel, SpielService spielService, JButton button) {
                this.spielfeld = spielfeld;
                this.karte = karte;
                this.spiel = spiel;
                this.spielService = spielService;
                this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (regelnService.checkCard(spiel, karte)) {
                    spielService.karteLegen(karte, spiel);
                    if (karte.getWert().equals("Bube")) {
                            String farbe = spielfeld.farbeWaehlen();
                            regelnService.handleBube(spiel, farbe);
                            spielfeld.gewaehlteFarbe(farbe);
                    } else {
                            spielfeld.gewaehlteFarbe("");
                    }
                    spielfeld.letzteKarteAendern(karte);
                    if (!karte.getWert().equals("Ass")) {
                            spielfeld.naechsterSpieler();
                    }
                    spielfeld.handAktualisieren();
            } else {
                    spielfeld.handAktualisieren();
            }
        }
}
