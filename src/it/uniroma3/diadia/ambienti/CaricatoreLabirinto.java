package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {
    private static final String STANZE_MARKER = "Stanze:";
    private static final String STANZE_BUIE_MARKER = "StanzeBuie:";
    private static final String STANZE_BLOCCATE_MARKER = "StanzeBloccate:";
    private static final String STANZE_MAGICHE_MARKER = "StanzeMagiche:";
    private static final String STANZA_INIZIALE_MARKER = "Inizio:";
    private static final String STANZA_VINCENTE_MARKER = "Vincente:";
    private static final String ATTREZZI_MARKER = "Attrezzi:";
    private static final String USCITE_MARKER = "Uscite:";
    private static final String MAGHI_MARKER = "Maghi:";
    private static final String STREGHE_MARKER = "Streghe:";
    private static final String CANI_MARKER = "Cani:";

    private BufferedReader reader;
    private Labirinto.LabirintoBuilder builder;

    public CaricatoreLabirinto(Reader reader) {
        this.reader = new BufferedReader(reader);
        this.builder = Labirinto.newBuilder();
    }

    public CaricatoreLabirinto(String nomeFile) throws FormatoFileNonCorrettoException {
        try {
            this.reader = new BufferedReader(new FileReader(nomeFile));
            this.builder = Labirinto.newBuilder();
        } catch (FileNotFoundException e) {
            throw new FormatoFileNonCorrettoException(e.getMessage());
        }
    }

    public Labirinto carica() throws FormatoFileNonCorrettoException {
        try {
            this.caricaStanze();
            this.caricaStanzeBuie();
            this.caricaStanzeBloccate();
            this.caricaStanzeMagiche();
            this.caricaInizialeEVincente();
            this.caricaAttrezzi();
            this.caricaUscite();
            this.caricaMaghi();
            this.caricaStreghe();
            this.caricaCani();
        } catch (IOException e) {
            throw new FormatoFileNonCorrettoException(e.getMessage());
        } finally {
            try {
                this.reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.builder.getLabirinto();
    }

    private String questaRigaTerminataDa(String marker) throws FormatoFileNonCorrettoException, IOException {
        String riga = this.reader.readLine();
        if (riga == null) return null;
        while (riga.trim().isEmpty() || riga.trim().startsWith("#")) {
            riga = this.reader.readLine();
            if (riga == null) return null;
        }
        if (!riga.startsWith(marker)) {
            throw new FormatoFileNonCorrettoException("Formato errato: atteso marcatore " + marker + " ma trovato " + riga);
        }
        return riga.substring(marker.length()).trim();
    }

    private String leggiRigaOpzionale(String marker) throws IOException {
        this.reader.mark(1000);
        String riga = this.reader.readLine();
        if (riga == null) return null;
        while (riga.trim().isEmpty() || riga.trim().startsWith("#")) {
            this.reader.mark(1000);
            riga = this.reader.readLine();
            if (riga == null) return null;
        }
        if (riga.startsWith(marker)) {
            return riga.substring(marker.length()).trim();
        }
        this.reader.reset();
        return null;
    }

    private void caricaStanze() throws FormatoFileNonCorrettoException, IOException {
        String nomiStanze = questaRigaTerminataDa(STANZE_MARKER);
        if (nomiStanze == null) return;
        for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
            this.builder.addStanza(nomeStanza);
        }
    }

    private void caricaStanzeBuie() throws IOException {
        String specifiche = leggiRigaOpzionale(STANZE_BUIE_MARKER);
        if (specifiche == null) return;
        for (String specifica : separaStringheAlleVirgole(specifiche)) {
            try (Scanner sc = new Scanner(specifica)) {
                if (sc.hasNext()) {
                    String nome = sc.next();
                    String attrezzo = sc.hasNext() ? sc.next() : "";
                    this.builder.addStanzaBuia(nome, attrezzo);
                }
            }
        }
    }

    private void caricaStanzeBloccate() throws IOException, FormatoFileNonCorrettoException {
        String specifiche = leggiRigaOpzionale(STANZE_BLOCCATE_MARKER);
        if (specifiche == null) return;
        for (String specifica : separaStringheAlleVirgole(specifiche)) {
            try (Scanner sc = new Scanner(specifica)) {
                if (sc.hasNext()) {
                    String nome = sc.next();
                    String dirStr = sc.hasNext() ? sc.next() : "";
                    String attrezzo = sc.hasNext() ? sc.next() : "";
                    Direzione dir = Direzione.valueOf(dirStr.toUpperCase());
                    this.builder.addStanzaBloccata(nome, dir, attrezzo);
                }
            } catch (IllegalArgumentException e) {
                throw new FormatoFileNonCorrettoException("Direzione non valida in StanzaBloccata");
            }
        }
    }

    private void caricaStanzeMagiche() throws IOException {
        String specifiche = leggiRigaOpzionale(STANZE_MAGICHE_MARKER);
        if (specifiche == null) return;
        for (String specifica : separaStringheAlleVirgole(specifiche)) {
            try (Scanner sc = new Scanner(specifica)) {
                if (sc.hasNext()) {
                    String nome = sc.next();
                    int soglia = sc.hasNextInt() ? sc.nextInt() : 3;
                    this.builder.addStanzaMagica(nome, soglia);
                }
            }
        }
    }

    private void caricaInizialeEVincente() throws FormatoFileNonCorrettoException, IOException {
        String nomeStanzaIniziale = questaRigaTerminataDa(STANZA_INIZIALE_MARKER);
        Stanza iniziale = this.builder.getNome2stanza().get(nomeStanzaIniziale);
        if (iniziale == null) {
            this.builder.addStanzaIniziale(nomeStanzaIniziale);
        } else {
            this.builder.getLabirinto().setStanzaIniziale(iniziale);
        }

        String nomeStanzaVincente = questaRigaTerminataDa(STANZA_VINCENTE_MARKER);
        Stanza vincente = this.builder.getNome2stanza().get(nomeStanzaVincente);
        if (vincente == null) {
            this.builder.addStanzaVincente(nomeStanzaVincente);
        } else {
            this.builder.getLabirinto().setStanzaVincente(vincente);
        }
    }

    private void caricaAttrezzi() throws FormatoFileNonCorrettoException, IOException {
        String specificheAttrezzi = questaRigaTerminataDa(ATTREZZI_MARKER);
        if (specificheAttrezzi == null) return;
        for (String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
            String nomeAttrezzo = null;
            int pesoAttrezzo = 1;
            String nomeStanza = null;
            try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
                if (scannerLinea.hasNext()) nomeAttrezzo = scannerLinea.next();
                if (scannerLinea.hasNext()) pesoAttrezzo = scannerLinea.nextInt();
                if (scannerLinea.hasNext()) nomeStanza = scannerLinea.next();
            }
            Stanza s = this.builder.getNome2stanza().get(nomeStanza);
            if (s != null) {
                this.builder.addStanza(nomeStanza); 
                this.builder.addAttrezzo(nomeAttrezzo, pesoAttrezzo);
            }
        }
    }

    private void caricaUscite() throws FormatoFileNonCorrettoException, IOException {
        String specificheUscite = questaRigaTerminataDa(USCITE_MARKER);
        if (specificheUscite == null) return;
        for (String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
            String stanzaPartenza = null;
            String direzioneStr = null;
            String stanzaDestinazione = null;
            try (Scanner scannerLinea = new Scanner(specificaUscita)) {
                if (scannerLinea.hasNext()) stanzaPartenza = scannerLinea.next();
                if (scannerLinea.hasNext()) direzioneStr = scannerLinea.next();
                if (scannerLinea.hasNext()) stanzaDestinazione = scannerLinea.next();
            }
            try {
                Direzione dir = Direzione.valueOf(direzioneStr.toUpperCase());
                this.builder.addAdiacenza(stanzaPartenza, stanzaDestinazione, dir);
            } catch (IllegalArgumentException e) {
                throw new FormatoFileNonCorrettoException("Direzione errata: " + direzioneStr);
            }
        }
    }

    private void caricaMaghi() throws IOException {
        String specifiche = leggiRigaOpzionale(MAGHI_MARKER);
        if (specifiche == null) return;
        for (String specifica : separaStringheAlleVirgole(specifiche)) {
            try (Scanner sc = new Scanner(specifica)) {
                if (sc.hasNext()) {
                    String nome = sc.next();
                    String pres = sc.next();
                    String attrNome = sc.next();
                    int attrPeso = sc.nextInt();
                    String stanzaNome = sc.next();
                    
                    // Recuperiamo la stanza originale già presente nel builder
                    Stanza s = this.builder.getNome2stanza().get(stanzaNome);
                    if (s != null) {
                        Mago m = new Mago(nome, pres, new Attrezzo(attrNome, attrPeso));
                        s.setPersonaggio(m);
                    }
                }
            }
        }
    }

    private void caricaStreghe() throws IOException {
        String specifiche = leggiRigaOpzionale(STREGHE_MARKER);
        if (specifiche == null) return;
        for (String specifica : separaStringheAlleVirgole(specifiche)) {
            try (Scanner sc = new Scanner(specifica)) {
                if (sc.hasNext()) {
                    String nome = sc.next();
                    String pres = sc.next();
                    String stanzaNome = sc.next();
                    
                    Stanza s = this.builder.getNome2stanza().get(stanzaNome);
                    if (s != null) {
                        Strega strega = new Strega(nome, pres);
                        s.setPersonaggio(strega);
                    }
                }
            }
        }
    }

    private void caricaCani() throws IOException {
        String specifiche = leggiRigaOpzionale(CANI_MARKER);
        if (specifiche == null) return;
        for (String specifica : separaStringheAlleVirgole(specifiche)) {
            try (Scanner sc = new Scanner(specifica)) {
                if (sc.hasNext()) {
                    String nome = sc.next();
                    String pres = sc.next();
                    String stanzaNome = sc.next();
                    
                    Stanza s = this.builder.getNome2stanza().get(stanzaNome);
                    if (s != null) {
                        Cane c = new Cane(nome, pres);
                        s.setPersonaggio(c);
                    }
                }
            }
        }
    }

    private List<String> separaStringheAlleVirgole(String stringa) {
        List<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(stringa);
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            result.add(scanner.next().trim());
        }
        scanner.close();
        return result;
    }
}
