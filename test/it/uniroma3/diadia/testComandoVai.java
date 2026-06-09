package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;

public class testComandoVai {
	
	private IO io;
	private LabirintoBuilder builder;
	private AbstractComando vai;
	
	@BeforeEach
	public void SetUp() {
		this.io = new IOSimulator();
		this.builder = new LabirintoBuilder();
		this.vai = new ComandoVai();
	}

	@Test
	public void esegui_null() {
		Labirinto monolocale = builder
				.addStanzaIniziale("atrio")
				.addStanzaVincente("atrio")
				.getLabirnto();
		Partita partita = new Partita(monolocale);
		vai.setParametro(null);
		vai.esegui(partita, io);
		assertEquals("atrio", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void esegui_NonEsistente () {
		Labirinto bilocaleADirezioneUnica = builder
				.addStanzaIniziale("atrio")
				.addStanza("aula")
				.addAdiazenza("atrio", "aula", Direzione.SUD)
				.addStanzaVincente("aula")
				.getLabirnto();
		Partita partita = new Partita(bilocaleADirezioneUnica);
		vai.setParametro("nord");
		vai.esegui(partita, io);
		assertEquals("atrio", partita.getStanzaCorrente().getNome());
	}

	@Test
	public void esegui_Valido() {
		Labirinto bilocaleValido = builder
				.addStanzaIniziale("atrio")
				.addStanzaVincente("aula")
				.addAdiazenza("atrio", "aula", Direzione.NORD)
				.getLabirnto();
		
		Partita partita = new Partita(bilocaleValido);
		vai.setParametro("nord");

		vai.esegui(partita, io);
		assertEquals("aula", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void esegui_SpostamentoConsumaCfu() {
		Labirinto bilocaleValido = builder
				.addStanzaIniziale("atrio")
				.addStanzaVincente("aula")
				.addAdiazenza("atrio", "aula", Direzione.NORD)
				.getLabirnto();
		
		Partita partita = new Partita(bilocaleValido);
		int cfuIniziali = partita.getGiocatore().getCfu();
		vai.setParametro("nord");

		vai.esegui(partita, io);
		assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
	}
}