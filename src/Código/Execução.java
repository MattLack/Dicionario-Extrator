package C�digo;

import java.util.Scanner;

public class Execu��o {
	
	
	public static void main(String[] args){
		
		Arquivo parser = new Arquivo("");
		
		Scanner teclado = new Scanner(System.in);

		System.out.println("Digite uma letra do alfabeto (caracter maiusculo): ");

		String texto = teclado.nextLine();
		
		parser.getInstance(texto).parserFuncao(texto);
		
	}
	 
}
