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
			dos.writeBytes("GET " + recurso + " HTTP/1.1");
			dos.flush();
			System.out.println("hola");
			String leido = br.readLine();
			System.out.println("esto: " + leido);
			while(leido != null && !leido.equals("\r\n")) {
				leido = br.readLine();
				System.out.println(leido);
			}
			while(leido != null) {
				bw.write(leido);
				leido = br.readLine();
			}
			bw.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
