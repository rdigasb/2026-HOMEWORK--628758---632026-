package it.uniroma3.diadia.ambienti; 

public class FormatoFileNonCorrettoException extends Exception {
    private static final long serialVersionUID = 1L;

    public FormatoFileNonCorrettoException(String messaggio) {
        super(messaggio);
    }
}