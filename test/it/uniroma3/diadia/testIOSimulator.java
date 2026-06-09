package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;  
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

/**
 * Classe di test funzionali/integrazione per verificare il comportamento del gioco DiaDia
 * simulando l'input dell'utente tramite IOSimulator.
 */
public class testIOSimulator {

    @Test
    public void testPartitaChiusuraImmediata() {
        // Il giocatore digita subito "fine" senza fare nulla
        IOSimulator io = new IOSimulator("fine");
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();

        String interoOutput = io.getOutput().toString().toLowerCase();

        // Verifichiamo che vengano stampati sia il benvenuto iniziale che il messaggio di chiusura
        assertTrue(interoOutput.contains("universita"), "Errore: Manca il messaggio di benvenuto iniziale.");
        assertTrue(interoOutput.contains("giocato") || interoOutput.contains("fine"), "Errore: Manca il messaggio di chiusura del gioco.");
    }

    @Test
    public void testPrendiOggettoEFine() {
        // 1. Creiamo un labirinto ad hoc per il test con l'osso nell'atrio iniziale
        Labirinto labirintoPerTest = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addAttrezzo("osso", 1)
                .addStanzaVincente("Biblioteca")
                .getLabirinto();

        // 2. Usiamo il costruttore a due parametri di DiaDia per iniettare il labirinto appena creato
        IOSimulator io = new IOSimulator("prendi osso", "fine");
        DiaDia gioco = new DiaDia(labirintoPerTest, io); 
        gioco.gioca();

        String interoOutput = io.getOutput().toString().toLowerCase();

        assertTrue(interoOutput.contains("osso"), "Errore: l'azione sull'attrezzo 'osso' non è stata registrata nell'output.");
        assertTrue(interoOutput.contains("giocato"), "Errore: manca il messaggio finale di chiusura.");
    }
    
    @Test
    public void testComandoNonValido() {
        // Inseriamo un comando inesistente ("salta") e poi chiudiamo
        IOSimulator io = new IOSimulator("salta", "fine");
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();

        String interoOutput = io.getOutput().toString().toLowerCase();

        // Verifica che il gioco segnali in qualche modo un comando errato/inesistente
        boolean comandoErratoRiconosciuto = interoOutput.contains("inesistente") 
                                         || interoOutput.contains("non valido") 
                                         || interoOutput.contains("sconosciuto")
                                         || interoOutput.contains("scusa");
                                         
        assertTrue(comandoErratoRiconosciuto, "Errore: Il gioco non ha segnalato che il comando 'salta' non era valido.");
    }
    
    @Test
    public void testVittoriaArrivoInBiblioteca() {
        // 1. Costruiamo un labirinto specifico in cui la Biblioteca è raggiungibile
        Labirinto labirintoPerTest = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addStanza("Corridoio")
                .addStanzaVincente("Biblioteca")
                .addAdiacenza("Atrio", "Corridoio", Direzione.NORD)    // Al primo passo va a Nord
                .addAdiacenza("Corridoio", "Biblioteca", Direzione.EST) // Al secondo passo va a Est
                .getLabirinto();

        // 2. Iniettiamo il labirinto strutturato nel simulatore
        IOSimulator io = new IOSimulator("vai nord", "vai est", "fine");
        DiaDia gioco = new DiaDia(labirintoPerTest, io);
        gioco.gioca();

        String interoOutput = io.getOutput().toString().toLowerCase();
        
        boolean haVintoOEntrato = interoOutput.contains("vint") || interoOutput.contains("biblioteca");
        assertTrue(haVintoOEntrato, "Errore: Il giocatore non è riuscito a raggiungere la Biblioteca o a vincere.");
    }
}