package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.comandi.*;

public class testAbstractComando {
	private static class ComandoFake extends AbstractComando{
		@Override
		public void eseguiSpecifico(Partita partita) {
			
		}
	}
	private AbstractComando comando;
	@BeforeEach
	public void SetUp() {
		this.comando=new ComandoFake();
	}
	
	@Test
	public void testGetNullIniziale() {
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testSet_Get(){
		comando.setParametro("oggettoTest");
		assertEquals("oggettoTest",comando.getParametro());
	}

}
