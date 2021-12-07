package de.htwberlin;

import javax.swing.*;

public class App extends JFrame {

    App() {

        setSize(1200, 800);
        setResizable(false);

        setTitle("Mau Mau");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new Spielfeld());

        pack();

    }

    public static void main(String[] args) {

        JFrame app = new App();
        app.setVisible(true);

    }

}
