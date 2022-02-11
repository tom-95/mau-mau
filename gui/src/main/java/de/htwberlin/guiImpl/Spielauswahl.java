package de.htwberlin.guiImpl;

import de.htwberlin.exceptions.DatenbankNichtErreichbarException;
import de.htwberlin.guiService.SpielfeldService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.regelnService.SpielRepository;
import de.htwberlin.spielService.SpielService;
import de.htwberlin.spielerService.Spieler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Spielauswahl {
    private static Logger LOGGER = LogManager.getLogger(Spielauswahl.class);

    JFrame frame;
    JPanel buttonPanel, dropdownPanel;
    JLabel text;
    JComboBox spielListe;
    JButton ok, neuesSpiel;
    SpielfeldService spielfeld;
    SpielService spielService;

    public Spielauswahl(SpielfeldService spielfeld, SpielService spielService) throws DatenbankNichtErreichbarException {
        frame = new JFrame("Wähle ein Spiel");
        buttonPanel = new JPanel();
        dropdownPanel = new JPanel();
        text = new JLabel("Wähle ein Spiel aus!");
        spielListe = new JComboBox();
        ok = new JButton("OK");
        neuesSpiel = new JButton("Neues Spiel");

        dropdownPanel.setLayout(new FlowLayout());
        buttonPanel.setLayout(new FlowLayout());

        dropdownPanel.add(text);
        dropdownPanel.add(spielListe);
        buttonPanel.add(ok);
        buttonPanel.add(neuesSpiel);

        List<String> auswahlListe = new ArrayList<>();

        List<Spiel> alleSpiele = spielService.alleSpieleFinden();

        for (Spiel aktuellesSpiel : alleSpiele) {

            String namen = "";
            for (Spieler aktuellerSpieler : aktuellesSpiel.getSpieler())
                namen += aktuellerSpieler.getName()+" ";
            auswahlListe.add(namen);
        }

        auswahlListe.forEach(spieler -> spielListe.addItem(spieler));

        neuesSpiel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                spielfeld.neuesSpiel();
            }
        });

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!auswahlListe.isEmpty()) {
                    frame.setVisible(false);
                    Spiel spiel = alleSpiele.get(spielListe.getSelectedIndex());
                    spielfeld.spielFortsetzen(spiel);
                }
            }
        });

        frame.add(dropdownPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(550, 150);
        frame.setResizable(false);
        frame.setVisible(true);
        this.spielfeld = spielfeld;
        this.spielService = spielService;

    }

}
