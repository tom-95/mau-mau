package de.htwberlin.guiImpl;

import de.htwberlin.guiService.SpielfeldService;
import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnService.RegelnService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielService.SpielService;
import de.htwberlin.regelnService.VirtuellerSpielerService;
import de.htwberlin.spielerService.Spieler;
import de.htwberlin.spielerService.SpielerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

@Service
public class SpielfeldImpl extends JPanel implements SpielfeldService {
    private static Logger LOGGER = LogManager.getLogger(SpielfeldImpl.class);

    private JPanel mySide;
    private JPanel otherSide;
    private JPanel bottom = new JPanel();
    private JLabel lastCardLabel = new JLabel();
    private JLabel opponent = new JLabel();
    private JLabel colourChoose = new JLabel();
    private JLabel player = new JLabel();
    private SpielService spielService;
    private RegelnService regelnService;
    private SpielerService spielerService;
    private KartenService kartenService;
    private VirtuellerSpielerService virtuellerSpielerService;
    private Spiel spiel;

    @Autowired
    SpielfeldImpl(SpielService spielService, RegelnService regelnService, SpielerService spielerService, VirtuellerSpielerService virtuellerSpielerService, KartenService kartenService) {
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

                ziehen();

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
        this.spielerService = spielerService;
        this.virtuellerSpielerService = virtuellerSpielerService;
        this.kartenService = kartenService;

        setVisible(true);

    }

    public void spielStarten() {
        LOGGER.debug("Spiel gestartet!");

        Integer[] options = {1, 2, 3, 4};

        int x = JOptionPane.showOptionDialog(null, "Wie viele Spieler seid ihr?", "Anzahl Spieler", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (x == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }

        int y = -1;

        if (x == 0) {

            Integer[] options2 = {1, 2, 3};

            y = JOptionPane.showOptionDialog(null, "Wie viele KI-Gegner möchtest du?", "Anzahl KI-Gegner", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);

            if (y == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }
        }

        spiel = spielService.spielStarten(x+1, y+1);

        spielfeldAnzeigen();

    }

    public void spielfeldAnzeigen() {
        LOGGER.debug("Spielfeld wird angezeigt.");

        handAktualisieren();
        Karte letzteKarteAblage = spiel.getAblagestapel().get(spiel.getAblagestapel().size()-1);
        letzteKarteAendern(letzteKarteAblage);
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
        if (spiel.getSpieler().get(spiel.getAmZug()).isKi())
            return;
        mySide.removeAll();

        String spielerName = spiel.getSpieler().get(spiel.getAmZug()).getName();
        player.setText(spielerName);

        List<Karte> hand = spiel.getSpieler().get(spiel.getAmZug()).getHand();
        for (Karte karte : hand)
            karteAnzeigen(karte);

        revalidate();
        repaint();
        //speichern();
        LOGGER.debug("Hand wurde aktualisiert.");

    }

    public void speichern() {

        List<Spieler> spieler = spiel.getSpieler();
        spieler.forEach(aktuellerSpieler -> spielerService.spielerSpeichern(aktuellerSpieler));
        spielService.spielSpeichern(spiel);

    }

    public void karteAnzeigen(Karte karte) {

        BufferedImage image = CardImageGenerator.generateImage(karte);
        JButton button = new JButton(new ImageIcon(image));
        mySide.add(button);
        button.addActionListener(new CardListener(this, karte));
        revalidate();
        repaint();

    }

    public void showWinningMessage(Spieler spieler) {

        JOptionPane.showMessageDialog(null, spieler.getName() + ", du hast gewonnen!", "WIN", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public void naechsterSpieler() {
        LOGGER.debug("Nächster Spieler wird gewählt.");
        LOGGER.debug("Alter Spieler: " + spiel.getSpieler().get(spiel.getAmZug()).getName());

        if (spiel.getAmZug() == (spiel.getSpieler().size() - 1)) {
            spiel.setAmZug(0);
        } else {
            spiel.setAmZug(spiel.getAmZug() + 1);
        }

        LOGGER.debug("Neuer Spieler: " + spiel.getSpieler().get(spiel.getAmZug()).getName());

        spielfeldAnzeigen();

        kiZugDurchfuehren();

    }

    public void kiZugDurchfuehren() {

        if (spiel.getSpieler().get(spiel.getAmZug()).isKi()) {
            Karte karte = virtuellerSpielerService.karteWaehlen(spiel, spiel.getSpieler().get(spiel.getAmZug()));
            if (karte != null) {
                if (spiel.getSpieler().get(spiel.getAmZug()).getHand().size()==2)
                    spielService.mauSagen(spiel);
                karteLegen(karte);
            }
            else {
                ziehen();
            }
        }

    }

    public void karteLegen(Karte karte) {

        if (regelnService.checkCard(spiel, karte)) {
            int aktuellerSpieler = spiel.getAmZug();
            spielService.karteLegen(karte, spiel);
            if (karte.getWert().equals("Bube")) {
                String farbe;
                if (!spiel.getSpieler().get(spiel.getAmZug()).isKi())
                    farbe = farbeWaehlen();
                else
                    farbe = virtuellerSpielerService.farbeWaehlen(spiel, spiel.getSpieler().get(spiel.getAmZug()));
                regelnService.handleBube(spiel, farbe);
                gewaehlteFarbe(farbe);
            } else if(karte.getWert().equals("Ass")) {
                regelnService.handleAss(spiel);
                kiZugDurchfuehren();
            }
            else {
                gewaehlteFarbe("");
            }
            letzteKarteAendern(karte);
            if (spiel.getSpieler().get(aktuellerSpieler).getHand().size() == 0) {
                handAktualisieren();
                showWinningMessage(spiel.getSpieler().get(aktuellerSpieler));
            }
            if (!karte.getWert().equals("Ass")) {
                naechsterSpieler();
            }
        }

        spielfeldAnzeigen();

    }

    public void ziehen() {

        if (spiel.getZiehZaehler() == 0) {
            spielService.ziehen(spiel);
        } else {
            for (int i = 0; i < spiel.getZiehZaehler(); i++){
                spielService.ziehen(spiel);
            }
        }
        spiel.setZiehZaehler(0);
        naechsterSpieler();
        handAktualisieren();

    }
}
