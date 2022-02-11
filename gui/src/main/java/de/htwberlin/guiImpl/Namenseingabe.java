package de.htwberlin.guiImpl;

import de.htwberlin.guiService.SpielfeldService;
import de.htwberlin.regelnService.Spiel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Namenseingabe {

    SpielfeldService spielfeld;
    JFrame frame;
    JPanel buttonPanel, fieldsPanel;
    JButton ok;

    public Namenseingabe(SpielfeldService spielfeld, int anzahlSpieler, Spiel spiel) throws RuntimeException {
        frame = new JFrame("Namen eingeben");
        buttonPanel = new JPanel();
        fieldsPanel = new JPanel();
        ok = new JButton("OK");

        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setLayout(new FlowLayout());
        List<JTextField> fieldList = new ArrayList<>();

        for (int i = 0; i < anzahlSpieler; i++) {
            JLabel label = new JLabel("Spieler " +(i + 1));
            fieldsPanel.add(label);
            JTextField textField = new JTextField(5);
            textField.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    if (textField.getText().length() > 9 ) {
                        e.consume();
                        JOptionPane.showMessageDialog(null, "Du kannst maximal 10 Zeichen eingeben!");
                    }
                }});
            fieldsPanel.add(textField);
            fieldList.add(textField);
        }

        buttonPanel.add(ok);
        frame.add(fieldsPanel, BorderLayout.PAGE_START);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean namenGueltig = true;
                for (int i = 0; i < anzahlSpieler; i++) {
                    for (int j = 0; j < anzahlSpieler; j++) {
                        if (i!=j && fieldList.get(i).getText().equals(fieldList.get(j).getText()))
                            namenGueltig = false;
                    }
                }
                if (namenGueltig) {
                    for (int i = 0; i < fieldList.size(); i++) {
                        String name = fieldList.get(i).getText();
                        spiel.getSpieler().get(i).setName(name);
                    }
                    frame.setVisible(false);
                    spielfeld.speichern();
                    spielfeld.spielFortsetzen(spiel);
                }
                else
                    JOptionPane.showMessageDialog(null, "Ihr müsst unterschiedliche Namen eingeben!");
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(250, 250);
        frame.setResizable(false);
        frame.setVisible(true);
        this.spielfeld = spielfeld;

    }

}
