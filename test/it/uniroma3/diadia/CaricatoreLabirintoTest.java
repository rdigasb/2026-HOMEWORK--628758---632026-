package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

public class CaricatoreLabirintoTest {

    @Test
    public void testCaricamentoLabirintoBase() throws Exception {
        String configurazione = "Stanze: aulaA, aulaB\n" +
                               "Inizio: aulaA\n" +
                               "Vincente: aulaB\n" +
                               "Attrezzi:\n" +
                               "Uscite: aulaA nord aulaB\n";
        
        StringReader sr = new StringReader(configurazione);
        CaricatoreLabirinto cl = new CaricatoreLabirinto(sr);
        Labirinto lab = cl.carica();
        
        assertEquals("aulaA", lab.getStanzaIniziale().getNome());
        assertEquals("aulaB", lab.getStanzaVincente().getNome());
        assertEquals("aulaB", lab.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getNome());
    }

    @Test
    public void testCaricamentoStanzeSpecialiEPersonaggi() throws Exception {
        String configurazione = "Stanze: stanzaNormale\n" +
                               "StanzeBuie: stanzaOscura lanterna\n" +
                               "Inizio: stanzaNormale\n" +
                               "Vincente: stanzaOscura\n" +
                               "Attrezzi:\n" +
                               "Uscite: stanzaNormale sud stanzaOscura\n" +
                               "Maghi: Merlino Simpatico bacchetta 2 stanzaNormale\n";
                               
        StringReader sr = new StringReader(configurazione);
        CaricatoreLabirinto cl = new CaricatoreLabirinto(sr);
        Labirinto lab = cl.carica();
        
        assertNotNull(lab.getStanzaIniziale());
        assertNotNull(lab.getStanzaIniziale().getPersonaggio());
        assertEquals("Merlino", lab.getStanzaIniziale().getPersonaggio().getNome());
    }
}