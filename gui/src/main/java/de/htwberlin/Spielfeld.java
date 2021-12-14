package de.htwberlin;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.regelnService.Spiel;
import de.htwberlin.spielService.SpielService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Spielfeld extends JPanel {

    private JPanel mySide;
    private JPanel otherSide;
    private JLabel lastCardLabel = new JLabel();
    private JLabel opponent = new JLabel();
    private JLabel colourChoose = new JLabel();
    private SpielService spielService;
    private Spiel spiel;
    private Map<Karte, JButton> buttons = new HashMap<>();

    Spielfeld() {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1200, 800));
        mySide = new JPanel(new FlowLayout());
        mySide.setPreferredSize(new Dimension(1200, 600));
        otherSide = new JPanel(new FlowLayout());
        otherSide.setPreferredSize(new Dimension(1200, 200));
        this.add(mySide, BorderLayout.CENTER);
        this.add(otherSide, BorderLayout.NORTH);
        JButton stackButton = new JButton(new ImageIcon(CardImageGenerator.generateBack()));
        otherSide.add(stackButton);
        stackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spielService.ziehen(spiel);
            }
        });

        otherSide.add(lastCardLabel);

        otherSide.add(opponent);

        otherSide.add(colourChoose);

        setVisible(true);

        spielStarten();

    }

    public void spielStarten() {

        Integer[] options = {2};

        int x = JOptionPane.showOptionDialog(null, "Wie viele Spieler seid ihr?", "Anzahl Spieler", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        spielService.spielStarten(x);

    }

    public String farbeWaehlen() {

        String[] options = {"Pik", "Karo", "Herz", "Kreuz"};

        int x = JOptionPane.showOptionDialog(null, "Welche Farbe waehlst du?", "Farbe waehlen", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        return options[x];

    }

    public void farbeWaehlen(String farbe) {

        colourChoose.setText("Dein Gegner waehlt " + farbe);

    }

    public void letzteKarteAendern(Karte karte) {

        BufferedImage letzteKarte = CardImageGenerator.generateImage(karte);
        lastCardLabel.setIcon(new ImageIcon(letzteKarte));
        revalidate();
        repaint();

    }

    public void karteVonSpielfeldEntfernen(Karte karte) {

        JButton button = buttons.get(karte);
        mySide.remove(button);
        revalidate();
        repaint();

    }

    public void karteHinzufügen(Karte karte) {

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

}
