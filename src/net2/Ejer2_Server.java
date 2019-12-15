package net2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejer2_Server {
	
	public static void main(String[] args) {
		Ejer2_Agenda agenda = new Ejer2_Agenda();
		try(ServerSocket server = new ServerSocket(6663);){
			while(true) {
				try(Socket client = server.accept();
					BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
					Writer w = new OutputStreamWriter(client.getOutputStream());){
						String linea = br.readLine();
						String[] linea_trozos = linea.split(" ");
						if(linea_trozos[0].equals("PUT")) {
							agenda.añadeTelefono(linea_trozos[1],linea_trozos[2]);
							w.write("OK");
							w.flush();
						}else {
							if(linea_trozos[0].equals("GET")) {
								if(agenda.getTfno(linea_trozos[1]) != null) {
									w.write(agenda.getTfno(linea_trozos[1]));
									w.flush();
								}else {
									w.write("Desconocido");
									w.flush();
								}
							}else {
								w.write("ERROR\r\n");
								w.flush();
							}
						}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
