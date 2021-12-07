package de.htwberlin;

import de.htwberlin.kartenService.Karte;
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

        public CardListener(Spielfeld spielfeld, Karte karte, Spiel spiel, SpielService spielService, JButton button) {
                this.spielfeld = spielfeld;
                this.karte = karte;
                this.spiel = spiel;
                this.spielService = spielService;
                this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            spielService.karteLegen(karte, spiel);

        }



}
