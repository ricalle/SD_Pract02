package net2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ejer2_Cliente_PUT {
	public static void main(String[] args) {
		
		try(Socket s = new Socket("localhost",6663);
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			Writer w = new OutputStreamWriter(s.getOutputStream());
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))){
			
			String nombre = "";
			String tlf = "";
			System.out.println("Introduce el nombre del Contacto: ");	
			nombre = teclado.readLine();
			System.out.println("Introduce el telefono del Contacto: ");	
			tlf = teclado.readLine();
			
			putTelefono(br,w,nombre,tlf);
			
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void putTelefono(BufferedReader br, Writer w, String nombre, String tlf) throws IOException {
		w.write("PUT " + nombre + " " + tlf + "\r\n");
		w.flush();
		System.out.println(br.readLine());
	}
	
}
