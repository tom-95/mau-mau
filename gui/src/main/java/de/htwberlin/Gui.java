package de.htwberlin;

import javax.swing.*;

public class Gui extends JFrame {

    Gui() {

        setSize(1200, 800);
        setResizable(false);

        setTitle("Mau Mau");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new Spielfeld());

        pack();

    }

    public static void main(String[] args) {

        JFrame app = new Gui();
        app.setVisible(true);

    }

}
