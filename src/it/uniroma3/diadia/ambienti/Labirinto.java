package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;
import it.uniroma3.diadia.personaggi.Cane;
/**
 * Questa classe modella una partita del gioco
 *
 * @author  Di Gasbarro Rocco (628758), Abballe Francesco (632026)
 * @see Stanza, Attrezzo
 * @version 1.4
 */

public class Labirinto {
    private Stanza stanzaIniziale;
    private Stanza stanzaVincente;

    
    public Labirinto() {
    }

    public Stanza getStanzaIniziale() {
        return stanzaIniziale;
    }

    public void setStanzaIniziale(Stanza stanzaIniziale) {
        this.stanzaIniziale = stanzaIniziale;
    }

    public Stanza getStanzaVincente() {
        return stanzaVincente;
    }

    public void setStanzaVincente(Stanza stanzaVincente) {
        this.stanzaVincente = stanzaVincente;
    }

    
    public static LabirintoBuilder newBuilder() {
        return new LabirintoBuilder();
    }

   
    public static class LabirintoBuilder {
        private Labirinto labirinto;
        private Stanza ultimaStanzaAggiunta;
        private Map<String, Stanza> nome2stanza;

        public LabirintoBuilder() {
            this.labirinto = new Labirinto();
            this.nome2stanza = new HashMap<>();
        }

        public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
            Stanza s = new Stanza(stanzaIniziale);
            this.labirinto.setStanzaIniziale(s);
            this.nome2stanza.put(stanzaIniziale, s);
            this.ultimaStanzaAggiunta = s;
            return this;
        }

        public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
            Stanza s = new Stanza(stanzaVincente);
            this.labirinto.setStanzaVincente(s);
            this.nome2stanza.put(stanzaVincente, s);
            this.ultimaStanzaAggiunta = s;
            return this;
        }

        public LabirintoBuilder addStanza(String stanza) {
            Stanza s = new Stanza(stanza);
            this.nome2stanza.put(stanza, s);
            this.ultimaStanzaAggiunta = s;
            return this;
        }

        public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzoPerVedere) {
            Stanza s = new StanzaBuia(nomeStanza, attrezzoPerVedere);
            this.nome2stanza.put(nomeStanza, s);
            this.ultimaStanzaAggiunta = s;
            return this;
        }

        public LabirintoBuilder addStanzaBloccata(String nomeStanza, Direzione direzione, String attrezzoSbloccante) {
            Stanza s = new StanzaBloccata(nomeStanza, direzione, attrezzoSbloccante);
            this.nome2stanza.put(nomeStanza, s);
            this.ultimaStanzaAggiunta = s;
            return this;
        }

        public LabirintoBuilder addStanzaMagica(String nomeStanza, int soglia) {
            Stanza s = new StanzaMagica(nomeStanza, soglia);
            this.nome2stanza.put(nomeStanza, s);
            this.ultimaStanzaAggiunta = s;
            return this;
        }

        public LabirintoBuilder addAttrezzo(String attrezzo, int peso) {
            if (this.ultimaStanzaAggiunta != null) {
                Attrezzo a = new Attrezzo(attrezzo, peso);
                this.ultimaStanzaAggiunta.addAttrezzo(a);
            }
            return this;
        }

        public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiacente, Direzione direzione) {
            Stanza c = this.nome2stanza.get(stanzaCorrente);
            Stanza a = this.nome2stanza.get(stanzaAdiacente);
            if (c != null && a != null) {
                c.impostaStanzaAdiacente(direzione, a);
            }
            return this;
        }

        public LabirintoBuilder addMago(String nome, String presentazione, Attrezzo attrezzo) {
            if (this.ultimaStanzaAggiunta != null) {
                Mago m = new Mago(nome, presentazione, attrezzo);
                this.ultimaStanzaAggiunta.setPersonaggio(m);
            }
            return this;
        }

        public LabirintoBuilder addStrega(String nome, String presentazione) {
            if (this.ultimaStanzaAggiunta != null) {
                Strega s = new Strega(nome, presentazione);
                this.ultimaStanzaAggiunta.setPersonaggio(s);
            }
            return this;
        }

        public LabirintoBuilder addCane(String nome, String presentazione) {
            if (this.ultimaStanzaAggiunta != null) {
                Cane c = new Cane(nome, presentazione);
                this.ultimaStanzaAggiunta.setPersonaggio(c);
            }
            return this;
        }

        public Map<String, Stanza> getNome2stanza() {
            return nome2stanza;
        }

        public Labirinto getLabirinto() {
            return this.labirinto;
        }
    }
}