package de.htwberlin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class Gui extends JFrame {

    private Spielfeld spielfeld;

    @Autowired
    Gui(Spielfeld spielfeld) {

        setSize(1200, 800);
        setResizable(false);

        setTitle("Mau Mau");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(spielfeld);

        pack();

        setVisible(true);

    }

//    public static void main(String[] args) {
//
//        JFrame app = new Gui();
//        app.setVisible(true);
//
//    }

}
