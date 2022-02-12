package de.htwberlin.guiImpl;

import de.htwberlin.exceptions.DatenbankNichtErreichbarException;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class SpielfeldImpl extends JPanel implements SpielfeldService {
    private static Logger LOGGER = LogManager.getLogger(SpielfeldImpl.class);

    private JPanel mySide;
    private JPanel otherSide;
    private JPanel bottom = new JPanel();
    private JLabel lastCardLabel = new JLabel();
    private JLabel playerOverview = new JLabel();
    private JLabel colourChoose = new JLabel();
    private JButton spielAktualisieren;
    private JLabel player = new JLabel();
    private SpielService spielService;
    private RegelnService regelnService;
    private SpielerService spielerService;
    private KartenService kartenService;
    private VirtuellerSpielerService virtuellerSpielerService;
    protected Spiel spiel;
    protected String eigenerSpieler;

    @Autowired
    SpielfeldImpl(SpielService spielService, RegelnService regelnService, SpielerService spielerService, VirtuellerSpielerService virtuellerSpielerService, KartenService kartenService) {
        LOGGER.debug("Spielfeld erzeugt!");

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1200, 600));
        mySide = new JPanel(new FlowLayout());
        mySide.setPreferredSize(new Dimension(1200, 450));
        otherSide = new JPanel(new FlowLayout());
        otherSide.setPreferredSize(new Dimension(1200, 150));
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

        otherSide.add(mauButton);
        mauButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    spiel.getSpieler().get(spiel.getAmZug()).setMauGesagt(true);
                } catch (RuntimeException ex) {
                    LOGGER.error("MAU sagen fehlgeschlagen!");
                    JOptionPane.showMessageDialog(null, "Es ist ein Fehler aufgetreten! Das Programm wird beendet.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        });

        otherSide.add(playerOverview);
        bottom.add(player);

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

        try {
            new Spielauswahl(this, spielService);
        }
        catch (DatenbankNichtErreichbarException e) {
            JOptionPane.showMessageDialog(null, "Datenbank nicht erreichbar!", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (RuntimeException ex) {
            LOGGER.error("Spielauswahl fehlgeschlagen!");
            JOptionPane.showMessageDialog(null, "Es ist ein Fehler aufgetreten! Das Programm wird beendet.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }

    public void neuesSpiel() {

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

        try {
            spiel = spielService.spielStarten(x + 1, y + 1);

            if (x != 0) {

                String[] options3 = {"lokal", "online"};

                int z = JOptionPane.showOptionDialog(null, "Möchtest du online oder lokal spielen?", "Spieltyp wählen", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]);

                if (z == JOptionPane.CLOSED_OPTION) {
                    System.exit(0);
                }

                if (z==1) {
                    spiel.setOnline(true);
                }
            }

            new Namenseingabe(this, options[x], spiel);
        }
        catch (RuntimeException e) {
            LOGGER.error("Spiel konnte nicht gestartet werden!");
            JOptionPane.showMessageDialog(null, "Es ist ein Fehler aufgetreten! Das Programm wird beendet.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }

    public void spielFortsetzen(Spiel spiel2) {

        this.spiel = spiel2;

        if (spiel.isOnline()) {
            List<Spieler> freieSpieler = new ArrayList<>();
            for (Spieler spieler : spiel.getSpieler()) {
                if (!spieler.isSpielerInUse()) {
                    freieSpieler.add(spieler);
                }
            }
            String[] namenArray = new String[freieSpieler.size()];
            for (int i = 0; i < freieSpieler.size(); i++) {

                namenArray[i] = freieSpieler.get(i).getName();
            }

            eigenerSpieler = (String) JOptionPane.showInputDialog(null, "Welcher Spieler bist du?", "Spieler wählen", JOptionPane.QUESTION_MESSAGE, null, namenArray, namenArray[0]);

            for (Spieler spieler : spiel.getSpieler()) {
                if (spieler.getName().equals(eigenerSpieler)) {
                    spieler.setSpielerInUse(true);
                }
            }

            boolean belegt = true;

            for (Spieler spieler : spiel.getSpieler())
                if (spieler.isSpielerInUse()==false)
                    belegt = false;

            spiel.setSpielBelegt(belegt);

            spielAktualisieren = new JButton(new String("Spiel aktualisieren"));
            spielAktualisieren.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (!spielService.exisitiertSpiel(spiel.getId())) {
                            JOptionPane.showMessageDialog(null, "Dein Gegner hat gewonnen! Das Spiel wird beendet.");
                            System.exit(0);
                        } else
                            spiel = spielService.spielLaden(spiel.getId());
                    } catch (DatenbankNichtErreichbarException ex) {
                        JOptionPane.showMessageDialog(null, "Datenbank nicht erreichbar!", "ERROR", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                    spielfeldAnzeigen();

                }
            });
        } else
            spiel.setSpielBelegt(true);

        speichern();

        spielfeldAnzeigen();

    }

    public void spielfeldAnzeigen() {
        LOGGER.debug("Spielfeld wird angezeigt.");

        if (spiel.getWunschfarbe() != null) {
            colourChoose.setText(spiel.getSpielerWunschfarbe() + " wählt " + spiel.getWunschfarbe());
        } else {
            colourChoose.setText("");
        }

        handAktualisieren(spiel.isOnline());
        Karte letzteKarteAblage = spiel.getAblagestapel().get(spiel.getAblagestapel().size()-1);
        letzteKarteAendern(letzteKarteAblage);
        if ((spiel.isOnline()) && (!spiel.getSpieler().get(spiel.getAmZug()).getName().equals(eigenerSpieler)))
            bottom.add(spielAktualisieren);
        else if ((spiel.isOnline()) && (spiel.getSpieler().get(spiel.getAmZug()).getName().equals(eigenerSpieler)))
            bottom.remove(spielAktualisieren);
        revalidate();
        repaint();
        LOGGER.debug("Spielfeld vollständig erzeugt.");
    }

    public String farbeWaehlen() {

        String[] options = {"Pik", "Karo", "Herz", "Kreuz"};

        int x = JOptionPane.showOptionDialog(null, "Welche Farbe wählst du?", "Farbe wählen", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        return options[x];

    }

    public void letzteKarteAendern(Karte karte) {

        BufferedImage letzteKarte = null;
        try {
            letzteKarte = CardImageGenerator.generateImage(karte);
        }
        catch (RuntimeException e) {
            LOGGER.error("Image konnte nicht geladen werden!");
            JOptionPane.showMessageDialog(null, "Es ist ein Fehler aufgetreten! Das Programm wird beendet.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        lastCardLabel.setIcon(new ImageIcon(letzteKarte));
        revalidate();
        repaint();

    }

    public void handAktualisieren(boolean online) {
        LOGGER.debug("Hand wird aktualisiert.");
        anzahlKartenSpielerAnzeigen();
        List<Karte> hand = null;
        if (online == false) {
            if (spiel.getSpieler().get(spiel.getAmZug()).isKi())
                return;
            mySide.removeAll();

            String playerText = spiel.getSpieler().get(spiel.getAmZug()).getName();
            player.setText(playerText);

            hand = spiel.getSpieler().get(spiel.getAmZug()).getHand();

        } else {

            mySide.removeAll();

            if (spiel.getSpieler().get(spiel.getAmZug()).getName().equals(eigenerSpieler))
                player.setText(eigenerSpieler + " DU BIST AM ZUG");
            else
                player.setText(eigenerSpieler);

            for (Spieler spieler : spiel.getSpieler()) {
                if (spieler.getName().equals(eigenerSpieler))
                    hand = spieler.getHand();
            }
        }
        for (Karte karte : hand)
            karteAnzeigen(karte);

        revalidate();
        repaint();
        LOGGER.debug("Hand wurde aktualisiert.");

    }

    public void anzahlKartenSpielerAnzeigen() {

        int anzahlSpieler = spiel.getSpieler().size();
        String text="<html>";
        for (int i=0; i<anzahlSpieler; i++) {
            String mauGesagt="";
            if(spiel.getSpieler().get(i).isMauGesagt()&&spiel.getSpieler().get(i).getZugZaehler()<=1)
                mauGesagt=" MAU !";
            text+=spiel.getSpieler().get(i).getName() + " hat noch " + spiel.getSpieler().get(i).getHand().size() + " Karten." + mauGesagt+"<br/>";
        }
        playerOverview.setText(text+"</html>");
    }

    public void speichern() {

        try {
            List<Spieler> spieler = spiel.getSpieler();
            spieler.forEach(aktuellerSpieler -> spielerService.spielerSpeichern(aktuellerSpieler));
            spielService.spielSpeichern(spiel);
        }
        catch(DatenbankNichtErreichbarException e) {
            JOptionPane.showMessageDialog(null, "Datenbank nicht erreichbar!", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }

    public void karteAnzeigen(Karte karte) {

        try {
            BufferedImage image = CardImageGenerator.generateImage(karte);
            JButton button = new JButton(new ImageIcon(image));
            mySide.add(button);
            button.addActionListener(new CardListener(this, karte));
            revalidate();
            repaint();
        } catch (RuntimeException e) {
            LOGGER.error("Karte kann nicht angezeigt werden!");
            JOptionPane.showMessageDialog(null, "Es ist ein Fehler aufgetreten! Das Programm wird beendet.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }

    public void showWinningMessage(Spieler spieler) {

        try {
            JOptionPane.showMessageDialog(null, spieler.getName() + " hat gewonnen!", "WIN", JOptionPane.INFORMATION_MESSAGE);
            spielService.spielLoeschen(spiel);
        } catch (DatenbankNichtErreichbarException e) {
            LOGGER.error("Datenbank nicht erreichbar!");
            JOptionPane.showMessageDialog(null, "Datenbank nicht erreichbar!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            System.exit(0);
        }

    }

    public void naechsterSpieler() {
        LOGGER.debug("Nächster Spieler wird gewählt.");
        LOGGER.debug("Alter Spieler: " + spiel.getSpieler().get(spiel.getAmZug()).getName());

        try {
            if (spiel.getAmZug() == (spiel.getSpieler().size() - 1)) {
                spiel.setAmZug(0);
            } else {
                spiel.setAmZug(spiel.getAmZug() + 1);
            }
        } catch (RuntimeException e) {
            LOGGER.error("Nächster Spieler konnte nicht gewählt werden!");
            JOptionPane.showMessageDialog(null, "Es ist ein Fehler aufgetreten! Das Programm wird beendet.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        LOGGER.debug("Neuer Spieler: " + spiel.getSpieler().get(spiel.getAmZug()).getName());

        speichern();
        // spielfeldAnzeigen();
        kiZugDurchfuehren();

    }

    public void kiZugDurchfuehren() {

        if (spiel.getSpieler().get(spiel.getAmZug()).isKi()) {
            Karte karte = null;
            try {
                karte = virtuellerSpielerService.karteWaehlen(spiel, spiel.getSpieler().get(spiel.getAmZug()));
            } catch (RuntimeException e) {
                LOGGER.error("KI Karte konnte nicht gewählt werden!");
                JOptionPane.showMessageDialog(null, "Es ist ein Fehler aufgetreten! Das Programm wird beendet.", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            if (karte != null) {
                if (spiel.getSpieler().get(spiel.getAmZug()).getHand().size()==2) {
                    try {
                        spielService.mauSagen(spiel);
                    } catch (RuntimeException e) {
                        LOGGER.error("KI: MAU sagen fehlgeschlagen!");
                        JOptionPane.showMessageDialog(null, "Es ist ein Fehler aufgetreten! Das Programm wird beendet.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
                karteLegen(karte);
            }
            else {
                ziehen();
            }
        }

    }

    public void karteLegen(Karte karte) {

        try {
            if ((spiel.isOnline()) && (!spiel.getSpieler().get(spiel.getAmZug()).getName().equals(eigenerSpieler))) {
                JOptionPane.showMessageDialog(null, "Du bist nicht am Zug!");
                return;
            }
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
                } else if (karte.getWert().equals("Ass")) {
                    regelnService.handleAss(spiel);
                    speichern();
                    kiZugDurchfuehren();
                }
                letzteKarteAendern(karte);
                if (spiel.getSpieler().get(aktuellerSpieler).getHand().size() == 0) {
                    handAktualisieren(spiel.isOnline());

                        showWinningMessage(spiel.getSpieler().get(aktuellerSpieler));

                }
                if (!karte.getWert().equals("Ass")) {
                    // spielfeldAnzeigen();
                    naechsterSpieler();
                }
            } else if ((spiel.getSpieler().get(spiel.getAmZug()).getHand().size() == 1) && (spiel.getSpieler().get(spiel.getAmZug()).isMauGesagt() == false)) {
                JOptionPane.showMessageDialog(null, "Du hast nicht MAU gesagt!");
            } else {
                JOptionPane.showMessageDialog(null, "Karte ungültig!");
            }

            spielfeldAnzeigen();
        } catch (RuntimeException ex) {
            LOGGER.error("Karte konnte nicht gelegt werden!");
            JOptionPane.showMessageDialog(null, "Es ist ein Fehler aufgetreten! Das Programm wird beendet.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }

    public void ziehen() {

        try {
            if ((spiel.isOnline()) && (!spiel.getSpieler().get(spiel.getAmZug()).getName().equals(eigenerSpieler))) {
                JOptionPane.showMessageDialog(null, "Du bist nicht am Zug!");
                return;
            }
            if (spiel.getZiehZaehler() == 0) {
                spielService.ziehen(spiel);
            } else {
                for (int i = 0; i < spiel.getZiehZaehler(); i++) {
                    spielService.ziehen(spiel);
                }
            }
            spiel.setZiehZaehler(0);
            naechsterSpieler();
            spielfeldAnzeigen();
            // handAktualisieren(spiel.isOnline());
        }catch (RuntimeException e) {
            LOGGER.error("Karte konnte nicht gezogen werden!");
            JOptionPane.showMessageDialog(null, "Es ist ein Fehler aufgetreten! Das Programm wird beendet.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }
}
