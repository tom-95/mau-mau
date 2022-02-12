package de.htwberlin.guiImpl;

import de.htwberlin.spielerService.Spieler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Service
public class Gui extends JFrame {

    private static Logger LOGGER = LogManager.getLogger(Gui.class);

    private SpielfeldImpl spielfeld;

    @Autowired
    Gui(SpielfeldImpl spielfeld) {
        LOGGER.debug("Gui started!");

        setSize(1200, 600);
        setResizable(false);

        setTitle("Mau Mau");
        setLocationRelativeTo(null);

        add(spielfeld);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Spieler spieler : spielfeld.spiel.getSpieler()) {
                    if (spieler.getName().equals(spielfeld.eigenerSpieler)) {
                        spieler.setSpielerInUse(false);
                    }
                }
                spielfeld.spiel.setSpielBelegt(false);
                spielfeld.speichern();
                System.exit(0);
            }
        });

        pack();

        setVisible(true);

    }

}
