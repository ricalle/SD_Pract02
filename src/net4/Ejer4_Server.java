package net4;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejer4_Server {
	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket(5656)){
			while(true) {
				try(Socket clte = server.accept();
					//BufferedReader br = new BufferedReader(new InputStreamReader(clte.getInputStream()))
					DataInputStream dis = new DataInputStream(clte.getInputStream());){
					String leo = dis.readLine(); System.out.println(leo);
					String[] info = leo.split(" ");
					System.out.println(info[0].split("/")[1]);
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(info[0].split("/")[1])));
					String leido = dis.readLine();
					while(leido != null && !leido.equals("He mandado la mitad.\r\n")) {
						bw.write(leido + "\r\n");
						leido = dis.readLine();
					}
					System.out.println(leido); //pinta null
					leido = dis.readLine();
					while(leido != null && !leido.equals("Acabe.\r\n")) {
						bw.write(leido + "\r\n");
						leido = dis.readLine();
					}
					System.out.println(leido);
					bw.close();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
