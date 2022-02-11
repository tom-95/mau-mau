package de.htwberlin.exceptions;

public class DatenbankNichtErreichbarException extends RuntimeException {

    public DatenbankNichtErreichbarException() {
        super("Die Datenbank ist nicht erreichbar!");
    }

    public DatenbankNichtErreichbarException(Throwable t) {
        super(t);
    }

}
