package de.htwberlin;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielService.SpielService;
import de.htwberlin.spielerService.Spieler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

@Component
public class Spielfeld extends JPanel {
    private static Logger LOGGER = LogManager.getLogger(Spielfeld.class);

    private JPanel mySide;
    private JPanel otherSide;
    private JPanel bottom = new JPanel();
    private JLabel lastCardLabel = new JLabel();
    private JLabel opponent = new JLabel();
    private JLabel colourChoose = new JLabel();
    private JLabel player = new JLabel();
    private SpielService spielService;
    private RegelnService regelnService;
    private Spiel spiel;

    @Autowired
    Spielfeld(SpielService spielService, RegelnService regelnService) {
        LOGGER.debug("Spielfeld erzeugt!");

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1200, 800));
        mySide = new JPanel(new FlowLayout());
        mySide.setPreferredSize(new Dimension(1200, 600));
        otherSide = new JPanel(new FlowLayout());
        otherSide.setPreferredSize(new Dimension(1200, 200));
        this.add(mySide, BorderLayout.CENTER);
        this.add(otherSide, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);
        JButton mauButton = new JButton(new String("Mau sagen!"));
        JButton stackButton = new JButton(new ImageIcon(CardImageGenerator.generateBack()));
        otherSide.add(stackButton);
        stackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (spiel.getZiehZaehler() == 0) {
                    spielService.ziehen(spiel);
                } else {
                    for (int i = 0; i < spiel.getZiehZaehler(); i++){
                        spielService.ziehen(spiel);
                    }
                }
                naechsterSpieler();
                handAktualisieren();
                spiel.setZiehZaehler(0);
            }
        });

        otherSide.add(lastCardLabel);
        otherSide.add(opponent);
        bottom.add(player);

        otherSide.add(mauButton);
        mauButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spiel.getSpieler().get(spiel.getAmZug()).setMauGesagt(true);
            }
        });

        otherSide.add(colourChoose);

        this.spielService = spielService;
        this.regelnService = regelnService;

        setVisible(true);

    }

    public void spielStarten() {
        LOGGER.debug("Spiel gestartet!");

        Integer[] options = {2, 3, 4};

        int x = JOptionPane.showOptionDialog(null, "Wie viele Spieler seid ihr?", "Anzahl Spieler", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (x == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }

        spiel = spielService.spielStarten(x+2);

        spielfeldAnzeigen();

    }

    public void spielfeldAnzeigen() {
        LOGGER.debug("Spielfeld wird angezeigt.");

        List<Karte> hand = spiel.getSpieler().get(spiel.getAmZug()).getHand();
        for (Karte karte : hand)
            karteAnzeigen(karte);

        Karte letzteKarteAblage = spiel.getAblagestapel().get(spiel.getAblagestapel().size()-1);
        letzteKarteAendern(letzteKarteAblage);
        handAktualisieren();
        revalidate();
        repaint();
        LOGGER.debug("Spielfeld vollständig erzeugt.");
    }

    public String farbeWaehlen() {

        String[] options = {"Pik", "Karo", "Herz", "Kreuz"};

        int x = JOptionPane.showOptionDialog(null, "Welche Farbe wählst du?", "Farbe wählen", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        return options[x];

    }

    public void gewaehlteFarbe(String farbe) {

        if (farbe.equals("")) {
            colourChoose.setText("");
        } else {
            colourChoose.setText("Dein Gegner wählt " + farbe);
        }
    }

    public void letzteKarteAendern(Karte karte) {

        BufferedImage letzteKarte = CardImageGenerator.generateImage(karte);
        lastCardLabel.setIcon(new ImageIcon(letzteKarte));
        revalidate();
        repaint();

    }

    public void handAktualisieren() {
        LOGGER.debug("Hand wird aktualisiert.");
        mySide.removeAll();

        String spielerName = spiel.getSpieler().get(spiel.getAmZug()).getName();
        player.setText(spielerName);

        List<Karte> hand = spiel.getSpieler().get(spiel.getAmZug()).getHand();
        for (Karte karte : hand)
            karteAnzeigen(karte);

        revalidate();
        repaint();
        LOGGER.debug("Hand wurde aktualisiert.");

    }

    public void karteAnzeigen(Karte karte) {

        BufferedImage image = CardImageGenerator.generateImage(karte);
        JButton button = new JButton(new ImageIcon(image));
        mySide.add(button);
        button.addActionListener(new CardListener(this, karte, spiel, spielService, button, regelnService));
        revalidate();
        repaint();

    }

    public void showWinningMessage(Spieler spieler) {

        JOptionPane.showMessageDialog(null, spieler.getName() + ", du hast gewonnen!", "WIN", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public void naechsterSpieler() {
        LOGGER.debug("Nächster Spieler wird gewählt.");

        if (spiel.getAmZug() == (spiel.getSpieler().size() - 1)) {
            spiel.setAmZug(0);
        } else {
            spiel.setAmZug(spiel.getAmZug() + 1);
        }
    }
}
