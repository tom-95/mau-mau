package de.htwberlin;

import de.htwberlin.kartenImpl.KartenImpl;
import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielService.SpielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Spielfeld extends JPanel {

    private JPanel mySide;
    private JPanel otherSide;
    private JPanel bottom = new JPanel();
    private JLabel lastCardLabel = new JLabel();
    private JLabel opponent = new JLabel();
    private JLabel colourChoose = new JLabel();
    private JLabel player = new JLabel();
    private SpielService spielService;
    private Spiel spiel;
    private KartenService kartenService;
    private Map<Karte, JButton> buttons = new HashMap<>();

    @Autowired
    Spielfeld(SpielService spielService) {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1200, 800));
        mySide = new JPanel(new FlowLayout());
        mySide.setPreferredSize(new Dimension(1200, 600));
        otherSide = new JPanel(new FlowLayout());
        otherSide.setPreferredSize(new Dimension(1200, 200));
        this.add(mySide, BorderLayout.CENTER);
        this.add(otherSide, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);
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
        otherSide.add(colourChoose);
        bottom.add(player);

        this.spielService = spielService;

        setVisible(true);

    }

    public void spielStarten() {

        Integer[] options = {2};

        int x = JOptionPane.showOptionDialog(null, "Wie viele Spieler seid ihr?", "Anzahl Spieler", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        spiel = spielService.spielStarten(x+2);

        spielController();

    }

    public void spielController() {

        spielfeldAnzeigen();

    }

    public void spielfeldAnzeigen() {

        List<Karte> hand = spiel.getSpieler().get(spiel.getAmZug()).getHand();
        for (Karte karte : hand)
            karteAnzeigen(karte);

        Karte letzteKarteAblage = spiel.getAblagestapel().get(spiel.getAblagestapel().size()-1);
        letzteKarteAendern(letzteKarteAblage);
        revalidate();
        repaint();

    }

    public String farbeWaehlen() {

        String[] options = {"Pik", "Karo", "Herz", "Kreuz"};

        int x = JOptionPane.showOptionDialog(null, "Welche Farbe waehlst du?", "Farbe waehlen", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        return options[x];

    }

    public void gewaehlteFarbe(String farbe) {

        if (farbe.equals("")) {
            colourChoose.setText("");
        } else {
            colourChoose.setText("Dein Gegner waehlt " + farbe);
        }
    }

    public void letzteKarteAendern(Karte karte) {

        BufferedImage letzteKarte = CardImageGenerator.generateImage(karte);
        lastCardLabel.setIcon(new ImageIcon(letzteKarte));
        revalidate();
        repaint();

    }

    public void karteVonSpielfeldEntfernen(Karte karte) {

        JButton button = buttons.get(karte);
        buttons.remove(karte);
        mySide.remove(button);
        revalidate();
        repaint();

    }

    public void handAktualisieren() {

        buttons.clear();
        mySide.removeAll();

        String spielerName = spiel.getSpieler().get(spiel.getAmZug()).getName();
        player.setText(spielerName);

        List<Karte> hand = spiel.getSpieler().get(spiel.getAmZug()).getHand();
        for (Karte karte : hand)
            karteAnzeigen(karte);

        revalidate();
        repaint();

    }

    public void karteAnzeigen(Karte karte) {

        BufferedImage image = CardImageGenerator.generateImage(karte);
        JButton button = new JButton(new ImageIcon(image));
        buttons.put(karte, button);
        mySide.add(button);
        button.addActionListener(new CardListener(this, karte, spiel, spielService, button));
        revalidate();
        repaint();

    }

    public void showWinningMessage(String wer) {

        if (wer.equals("ich"))
            JOptionPane.showMessageDialog(null, "Du hast gewonnen!", "WIN", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "Dein Gegner hat gewonnen!", "Loser", JOptionPane.INFORMATION_MESSAGE);

    }

    public void naechsterSpieler() {

        if (spiel.getAmZug() == (spiel.getSpieler().size() - 1)) {
            spiel.setAmZug(0);
        } else {
            spiel.setAmZug(spiel.getAmZug() + 1);
        }

    }
}
