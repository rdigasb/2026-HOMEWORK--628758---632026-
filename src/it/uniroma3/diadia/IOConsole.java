package it.uniroma3.diadia;
/**
* Classe che implementa l'interfaccia IO 
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version 1.2
*/
import java.util.Scanner;
public class IOConsole implements IO{
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	@Override
	public String leggiRiga() {
	Scanner scannerDiLinee = new Scanner(System.in);
	return scannerDiLinee.nextLine();
	//scannerDiLinee.close();
	}
}