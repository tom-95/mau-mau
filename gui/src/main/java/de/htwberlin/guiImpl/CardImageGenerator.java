package de.htwberlin.guiImpl;

import de.htwberlin.kartenService.Karte;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

public class CardImageGenerator {
    private static Logger LOGGER = LogManager.getLogger(CardImageGenerator.class);

    private static URL url = CardImageGenerator.class.getClassLoader().getResource("deck.jpg");

    private static final Map<List<String>, List<Integer>> cards = Map.ofEntries(
            entry(List.of("Karo","7"), List.of(0,0,60,89)),
            entry(List.of("Karo","8"), List.of(60,0,60,89)),
            entry(List.of("Karo","9"), List.of(120,0,60,89)),
            entry(List.of("Karo","Dame"), List.of(180,0,60,89)),
            entry(List.of("Karo","Koenig"), List.of(240,0,60,89)),
            entry(List.of("Karo","10"), List.of(300,0,60,89)),
            entry(List.of("Karo","Ass"), List.of(360,0,60,89)),
            entry(List.of("Herz","7"), List.of(420,0,60,89)),
            entry(List.of("Herz","8"), List.of(0,88,60,89)),
            entry(List.of("Herz","9"), List.of(60,88,60,89)),
            entry(List.of("Herz","Dame"), List.of(120,88,60,89)),
            entry(List.of("Herz","Koenig"), List.of(180,88,60,89)),
            entry(List.of("Herz","10"), List.of(240,88,60,89)),
            entry(List.of("Herz","Ass"), List.of(300,88,60,89)),
            entry(List.of("Pik","7"), List.of(360,88,60,89)),
            entry(List.of("Pik","8"), List.of(420,88,60,89)),
            entry(List.of("Pik","9"), List.of(0,177,60,89)),
            entry(List.of("Pik","Dame"), List.of(60,177,60,89)),
            entry(List.of("Pik","Koenig"), List.of(120,177,60,89)),
            entry(List.of("Pik","10"), List.of(180,177,60,89)),
            entry(List.of("Pik","Ass"), List.of(240,177,60,89)),
            entry(List.of("Kreuz","7"), List.of(300,177,60,89)),
            entry(List.of("Kreuz","8"), List.of(360,177,60,89)),
            entry(List.of("Kreuz","9"), List.of(420,177,60,89)),
            entry(List.of("Kreuz","Dame"), List.of(0,265,60,89)),
            entry(List.of("Kreuz","Koenig"), List.of(60,265,60,89)),
            entry(List.of("Kreuz","10"), List.of(120,265,60,89)),
            entry(List.of("Kreuz","Ass"), List.of(180,265,60,89)),
            entry(List.of("Karo","Bube"), List.of(240,265,60,89)),
            entry(List.of("Herz","Bube"), List.of(300,265,60,89)),
            entry(List.of("Pik","Bube"), List.of(360,265,60,89)),
            entry(List.of("Kreuz","Bube"), List.of(420,265,60,89))
    );

    public static BufferedImage generateImage(Karte karte) {

        String farbe = karte.getFarbe();
        String wert = karte.getWert();

        List<Integer> coordinates = cards.get(List.of(farbe, wert));
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(url.toURI())).getSubimage(coordinates.get(0), coordinates.get(1), coordinates.get(2), coordinates.get(3));
        }
        catch(Exception e) {
            LOGGER.error("Kartenbilder konnten nicht geladen werden!");
        }

        return image;
    }


    public static BufferedImage generateBack() {

        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(url.toURI())).getSubimage(0,355,60,89);
        }
        catch(Exception e) {
            LOGGER.error("Kartenrückseite konnte nicht geladen werden!");
        }

        return image;
    }

}
