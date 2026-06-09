package it.uniroma3.diadia.ambienti;

public enum Direzione {
    NORD, SUD, EST, OVEST;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
