package net2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ejer2_Cliente_GET {
	public static void main(String[] args) {
		try(Socket s = new Socket("localhost",6663);
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			Writer w = new OutputStreamWriter(s.getOutputStream());
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))){
					
				String nombre = "";
				System.out.println("Introduce el nombre del Contacto: ");	
				nombre = teclado.readLine();
					
				getTelefono(br,w,nombre);
				
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void getTelefono(BufferedReader br, Writer w, String nombre) throws IOException {
		w.write("GET " + nombre + "\r\n");
		w.flush();
		System.out.println(br.readLine());
	}
}
