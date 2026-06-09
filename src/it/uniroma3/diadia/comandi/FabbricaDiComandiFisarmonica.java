package it.uniroma3.diadia.comandi;
import java.util.Scanner;
import it.uniroma3.diadia.IO;
/**
 * Classe che implementa l'interfaccia 
 * Fabbrica di Comandi, la classe  viene
 * utilizzata come gestore dei comandi di input
 * richiamando la classe del comando equivalente
 * a quella richiesta 
 * @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
 * @version 1.0
 */
public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
	@Override
	public Comando costruisciComando(String istruzione,IO io) {
	Scanner scannerDiParole = new Scanner(istruzione);
	String nomeComando = null;
	String parametro = null;
	Comando comando = null;
	if (scannerDiParole.hasNext())
	nomeComando = scannerDiParole.next();// prima parola: nome del comando
	if (scannerDiParole.hasNext())
	parametro = scannerDiParole.next(); // seconda parola: eventuale param.
	if (nomeComando == null)
		comando = new ComandoNonValido();
	else if (nomeComando.equals("vai"))
		comando = new ComandoVai();
	else if (nomeComando.equals("prendi"))
		comando = new ComandoPrendi();
	else if (nomeComando.equals("posa"))
		comando = new ComandoPosa();
	else if (nomeComando.equals("aiuto"))
		comando = new ComandoAiuto();
	else if (nomeComando.equals("fine"))
		comando = new ComandoFine();
	else if (nomeComando.equals("guarda"))
		comando = new ComandoGuarda();
	else comando = new ComandoNonValido();
		comando.setParametro(parametro);
	return comando;
	}
}
