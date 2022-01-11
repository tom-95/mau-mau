package de.htwberlin.kartenImpl;

import de.htwberlin.kartenService.Karte;
import de.htwberlin.kartenService.KartenService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class KartenImpl implements KartenService {

    public List<Karte> deckErzeugen() {

        List<Karte> deck = new ArrayList<>();
        deck.addAll(List.of(
                new Karte("Karo","7"),
                new Karte("Karo","8"),
                new Karte("Karo","9"),
                new Karte("Karo","Dame"),
                new Karte("Karo","Koenig"),
                new Karte("Karo","10"),
                new Karte("Karo","Ass"),
                new Karte("Herz","7"),
                new Karte("Herz","8"),
                new Karte("Herz","9"),
                new Karte("Herz","Dame"),
                new Karte("Herz","Koenig"),
                new Karte("Herz","10"),
                new Karte("Herz","Ass"),
                new Karte("Pik","7"),
                new Karte("Pik","8"),
                new Karte("Pik","9"),
                new Karte("Pik","Dame"),
                new Karte("Pik","Koenig"),
                new Karte("Pik","10"),
                new Karte("Pik","Ass"),
                new Karte("Kreuz","7"),
                new Karte("Kreuz","8"),
                new Karte("Kreuz","9"),
                new Karte("Kreuz","Dame"),
                new Karte("Kreuz","Koenig"),
                new Karte("Kreuz","10"),
                new Karte("Kreuz","Ass"),
                new Karte("Karo","Bube"),
                new Karte("Herz","Bube"),
                new Karte("Pik","Bube"),
                new Karte("Kreuz","Bube")));

        mischen(deck);

        return deck;
    }

    public void mischen(List<Karte> karten) {

        Collections.shuffle(karten);

    }
}
