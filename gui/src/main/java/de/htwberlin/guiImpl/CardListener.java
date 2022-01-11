package de.htwberlin.guiImpl;

import de.htwberlin.kartenService.Karte;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardListener implements ActionListener {

        SpielfeldImpl spielfeld;
        Karte karte;

        public CardListener(SpielfeldImpl spielfeld, Karte karte) {
                this.spielfeld = spielfeld;
                this.karte = karte;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            spielfeld.karteLegen(karte);

        }
}
