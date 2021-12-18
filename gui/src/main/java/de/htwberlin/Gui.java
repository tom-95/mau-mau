package de.htwberlin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;


@Component
public class Gui extends JFrame {

    private static Logger LOGGER = LogManager.getLogger(Gui.class);

    private Spielfeld spielfeld;

    @Autowired
    Gui(Spielfeld spielfeld) {
        LOGGER.debug("Gui started!");

        setSize(1200, 800);
        setResizable(false);

        setTitle("Mau Mau");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(spielfeld);

        pack();

        setVisible(true);

    }
}
