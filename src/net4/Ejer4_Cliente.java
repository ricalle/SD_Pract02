package net4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Ejer4_Cliente {
	public static void main(String[] args) {
		enviador2partes("ejer4/prueba.txt");
	}
	
	public static void enviador2partes(String nomArchivo) {
		File f = new File(nomArchivo);
		try(Socket server = new Socket("localhost",5656);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nomArchivo)));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()))){
			bw.write(nomArchivo + " " + f.length());
			bw.flush();
			char[] datos = new char[(int)f.length()/2];
			int leidos = br.read(datos, 0, (int) f.length()/2);
			bw.write(datos, 0, leidos);
			bw.flush();
			bw.write("He mandado la mitad.");
			bw.flush();
			leidos = br.read(datos);
			bw.write(datos, 0, leidos);
			bw.write("Acabe.");
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
