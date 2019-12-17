package net3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ejer3 {
	public static void main(String[] args) {
		WebClient("www.google.com", "/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
	}
	
	public static void WebClient(String web, String recurso) {
		String [] extension = recurso.split("\\.");
		try(Socket s = new Socket(web,80);
			//DataInputStream dis = new DataInputStream(s.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("archivo." + extension[1])));){
			dos.writeBytes("GET " + recurso + " HTTP/1.0\r\n\r\n");
			dos.flush();String leido = br.readLine();
			System.out.println(leido);
			while(leido != null && !leido.trim().equals("")) {
				leido = br.readLine();
			}
			char[] elemento = new char[1024*4];
			int leidos = br.read(elemento);
			while(leidos != -1) {
				bw.write(elemento, 0, leidos);
				leidos = br.read(elemento);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
