package it.uniroma3.diadia;

/**
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version base
*/

import java.util.Scanner;
public class IOConsole {
public void mostraMessaggio(String msg) {
System.out.println(msg);
}
public String leggiRiga() {
Scanner scannerDiLinee = new Scanner(System.in);
String riga = scannerDiLinee.nextLine();
//scannerDiLinee.close();
return riga;
}
}