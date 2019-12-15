package net4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

public class Ejer4_Cliente {
	public static void main(String[] args) {
		enviador2partes("ejer4/pruebas");
	}
	
	public static void enviador2partes(String nomArchivo) {
		File f = new File(nomArchivo);
		try(Socket server = new Socket("localhost",5656);
			DataInputStream dis = new DataInputStream(new FileInputStream(nomArchivo));
			DataOutputStream dos = new DataOutputStream(server.getOutputStream())){
			dos.writeChars(nomArchivo + " " + f.length());
			dos.flush();
			byte[] datos = new byte[(int)f.length()/2];
			int leidos = dis.read(datos, 0, (int) f.length()/2);
			dos.write(datos, 0, leidos);
			dos.flush();
				System.out.println("He mandado la mitad.");
				dos.writeChars("He mandado la mitad.");
				dos.flush();
			leidos = dis.read(datos);
			dos.write(datos, 0, leidos);
			dos.writeChars("Acabe.");
			dos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
