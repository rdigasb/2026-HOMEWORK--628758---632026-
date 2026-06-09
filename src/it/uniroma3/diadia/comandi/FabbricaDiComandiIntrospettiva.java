package it.uniroma3.diadia.comandi;

import java.util.Scanner;
import it.uniroma3.diadia.IO;
public class FabbricaDiComandiIntrospettiva implements FabbricaDiComandi{
	
	@Override
	public Comando costruisciComando(String istruzione,IO io) {
		Scanner scannerDiParole=new Scanner(istruzione);
		String nomeComando=null;
		String paramentro=null;
		
		if(scannerDiParole.hasNext()) {
			nomeComando=scannerDiParole.next();
		}
		
		if(scannerDiParole.hasNext()) {
			paramentro=scannerDiParole.next();
		}
		
		//scannerDiParole.close();
		
		if(nomeComando==null) {
			return new ComandoNonValido();
		}
		
		String nomeClasseComando=Character.toUpperCase(nomeComando.charAt(0))+nomeComando.substring(1).toLowerCase();
		Comando comando=null;
		
		try {
			String nomeCompletoClasse="it.uniroma3.diadia.comandi.Comando" + nomeClasseComando;
			comando=(Comando) Class.forName(nomeCompletoClasse).getConstructor().newInstance();
			comando.setParametro(paramentro);
		} catch(ClassNotFoundException e) {
			comando=new ComandoNonValido();
		} catch(Exception e) {
			io.mostraMessaggio("Errore interno nella creazione del comando.");
			comando=new ComandoNonValido();
		}
		return comando;
	}
}
