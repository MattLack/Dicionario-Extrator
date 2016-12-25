package Código;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {

	private static Arquivo instance = null;
	private BufferedWriter escritor = null;

	Arquivo(String letra) {
		try {
			escritor = new BufferedWriter(new FileWriter(letra + "saida.txt"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static Arquivo getInstance(String letra) {
		if (instance == null) {
			instance = new Arquivo(letra);
		}
		return instance;
	}

	public void parserFuncao(String letra) {

		System.out.println("\n" + letra + " Sendo compilado!\n");

		BufferedReader leitor = null;

		try {

			leitor = new BufferedReader(new FileReader(letra + ".txt"));

			String linha = "";

			while ((linha = leitor.readLine()) != null) {
				if (linha.length() > 0) {

					tratamento(linha, letra);
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				leitor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("\nConcluido!");

	}

	public void tratamento(String texto, String letra) {
		int key = 0;
		StringBuffer aux = new StringBuffer();
		for (int i = 0; i < texto.length(); i++) {

			if ((texto.charAt(i) == '*') && (!texto.contains("^2")) && (!texto.contains("^3"))
					&& (!texto.contains("^4")) && (!texto.contains("^5")) && (!texto.contains("^6"))
					&& (!texto.contains("^7")) && (!texto.contains("^8")) && (!texto.contains("^9"))
					&& (!texto.contains("*H*")) && (!texto.contains("*rúbrica*")) && (!texto.contains("* Prov. trasm."))
					&& (!texto.contains("...b")) && (!texto.contains("...c")) && (!texto.contains("...d"))
					&& (!texto.contains("...j")) && (!texto.contains("...k")) && (!texto.contains("...l"))
					&& (!texto.contains("...n")) && (!texto.contains("...p")) && (!texto.contains("...q"))
					&& (!texto.contains("...r")) && (!texto.contains("...s")) && (!texto.contains("...u"))
					&& (!texto.contains("...v")) && (!texto.contains("...w")) && (!texto.contains("...x"))
					&& (!texto.contains("..y")) && (!texto.contains("..z")) && (!texto.contains("* Nome antigo"))
					&& (!texto.contains("*fazer á guerra;")) && (!texto.contains("* Depósito, que recebe"))
					&& (!texto.contains("(sign.")) && (!texto.contains("(Escrita usual,")) && (!texto.contains("*Y*"))
					&& (!texto.contains("* Diz-se do")) && (!texto.contains("[**typo"))
					&& (!texto.contains("_algibebe_.")) && (!texto.contains("(De _agricola_  _industrial_)"))
					&& (!texto.contains("(não Álanos)")) && (key == 0)) {

				aux.append(texto);
				replaceAll(aux, "*", "");
				replaceAll(aux, "+", "");
				replaceAll(aux, ",", "");
				replaceAll(aux, "^1", "");
				replaceAll(aux, "{", "");
				replaceAll(aux, "}", "");
				replaceAll(aux, "; com zêlo.", "");

				Arquivo.getInstance(letra).arquivoSaida(letra, aux);
				key = 1;

			}
		}

	}

	public void arquivoSaida(String letra, StringBuffer aux) {

		String text = aux.toString();
		int size = text.length();

		try {
			if (new File(letra + "saida.txt").exists() == false) {

				new File(letra + "saida.txt").createNewFile();

			}

			if (text.length() > 1) {
				if (text.charAt(0) == ' ') {
					text = text.substring(1, size);
					/*
					 * if (text.charAt(0) == ' ') text = text.substring(1,
					 * size);
					 */
					if (!(text.charAt(0) == '_')) {
						escritor.append(text + "\r\n");
						System.out.println(text);
						escritor.flush();
					}
				} else {
					if (!(text.charAt(0) == '_')) {
						escritor.append(text + "\r\n");
						System.out.println(text);
						escritor.flush();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void replaceAll(StringBuffer builder, String from, String to) {

		/**
		 * Dada uma string (builder), uma segunda string (form) será buscada
		 * dentro da anterior, e substituida por outra string (to)
		 */

		int index = builder.indexOf(from);
		while (index != -1) {
			builder.replace(index, index + from.length(), to);
			index += to.length();
			index = builder.indexOf(from, index);
		}
	}
}
