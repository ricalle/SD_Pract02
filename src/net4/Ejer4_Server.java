package net4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejer4_Server {
	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket(5656)){
			while(true) {
				try(Socket clte = server.accept();
					DataInputStream dis = new DataInputStream(clte.getInputStream())){
					String[] info = dis.readLine().trim().split(" ");
					System.out.println(info[0] + " " + info[1]);
					DataOutputStream dos = new DataOutputStream(new FileOutputStream(info[0]));
					byte[]datos = new byte[Integer.parseInt(info[1])];
					int leidos = dis.read(datos);
					while(leidos != -1) {
						dos.write(datos, 0, leidos);
						leidos = dis.read(datos);
					}
					dos.close();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
